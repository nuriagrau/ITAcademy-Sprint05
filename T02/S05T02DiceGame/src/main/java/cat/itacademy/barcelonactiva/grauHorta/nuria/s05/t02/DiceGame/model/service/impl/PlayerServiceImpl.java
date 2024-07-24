package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.EmptyGamesListException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.PlayerService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.impl.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private final GameRepository gameRepository;

    private final UserRepository userRepository;

    private final SecurityService securityService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, GameRepository gameRepository, UserRepository userRepository, SecurityService securityService) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDto) {
        Player newPlayer = playerMapper.toEntity(playerDto);
        if (newPlayer.getPlayerName() != null && !(newPlayer.getPlayerName().isBlank())) { // something is wrong and if is null checks if exists
            playerRepository.findByPlayerNameIgnoreCase(newPlayer.getPlayerName())
                    .ifPresent(p -> {
                        throw new PlayerAlreadyExistsException("The player " + p.getPlayerName() + " already exists.");
                    });
        }
        if (newPlayer.getPlayerName() == null || newPlayer.getPlayerName().isBlank()) {
            newPlayer.setPlayerName("ANONYM");
        }
        return playerMapper.toDto(playerRepository.save(newPlayer));
    }

    @Override
    @PreAuthorize("@securityService.isPlayerOwner(#playerDto.getPlayerId()) || hasRole('ADMIN')")
    public PlayerDTO updatePlayer(PlayerDTO playerDto) {
        Player existingPlayer = playerRepository.findById(playerDto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + playerDto.getPlayerId() + " not found."));
        if (playerDto.getPlayerName() != null) {
            existingPlayer.setPlayerName(playerDto.getPlayerName());
        }

        return playerMapper.toDto(playerRepository.save(existingPlayer));
    }

    @Override
    public List<PlayerDTO> getAllPlayersOrderByWinRateDesc() {
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDTOList(players);
    }

    @Override
    public Double getWinRateAverage() throws EmptyGamesListException{
        OptionalDouble winRateAverage = playerRepository.findAll().stream()

                .mapToDouble(Player::getWinRate)
                .average();

        return winRateAverage.orElseThrow(() -> new EmptyGamesListException("No win rates available to calculate average"));
    }

    @Override
    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDto(players.getLast());
    }

    @Override
    public PlayerDTO getWinner(){
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDto(players.getFirst());
    }


    @Override
    @PreAuthorize("@securityService.isPlayerOwner(#playerId) || hasRole('ADMIN')")
    public int deletePlayer(int playerId) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + " not found"));

        User user = existingPlayer.getUser();
        if (user != null) {
            user.getPlayers().remove(existingPlayer);
            userRepository.save(user);
        }
        gameRepository.deleteAllByPlayerId(playerId);
        playerRepository.delete(existingPlayer);

        return playerId;
    }
}
