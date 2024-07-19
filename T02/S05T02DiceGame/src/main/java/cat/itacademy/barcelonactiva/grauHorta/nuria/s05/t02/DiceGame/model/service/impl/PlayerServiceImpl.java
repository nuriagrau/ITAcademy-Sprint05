package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.EmptyGamesListException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.OptionalDouble;



@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final GameRepository gameRepository;


    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.gameRepository = gameRepository;
    }


    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDto) {
        Player newPlayer = playerMapper.toEntity(playerDto);
        if (newPlayer.getPlayerName() != null && !(newPlayer.getPlayerName().isBlank())) { // something is wrong and if is null checks if exists
            playerRepository.findByPlayerNameIgnoreCase(newPlayer.getPlayerName())
                    .ifPresent(foundPlayer -> {
                        throw new PlayerAlreadyExistsException("The player " + playerDto.getPlayerName() + " already exists.");
                    });
        }
        if (newPlayer.getPlayerName() == null || newPlayer.getPlayerName().isBlank()) {
            newPlayer.setPlayerName("ANONYM");
        }
        return playerMapper.toDto(playerRepository.save(newPlayer));
    }

    public PlayerDTO updatePlayer(PlayerDTO playerDto) {
        Player existingPlayer = playerRepository.findById(playerDto.getPlayerId())
                .orElseThrow(() -> new PlayerNotFoundException("Player with id: " + playerDto.getPlayerId() + " not found."));
        if (playerDto.getPlayerName() != null) {
            existingPlayer.setPlayerName(playerDto.getPlayerName());
        }

        return playerMapper.toDto(playerRepository.save(existingPlayer));
    }

    public List<PlayerDTO> getAllPlayersOrderByWinRateDesc() {
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDTOList(players);
    }

    public Double getWinRateAverage() throws EmptyGamesListException{
        OptionalDouble winRateAverage = playerRepository.findAll().stream()

                .mapToDouble(Player::getWinRate)
                .average();

        return winRateAverage.orElseThrow(() -> new EmptyGamesListException("No win rates available to calculate average"));
    }

    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDto(players.getLast());
    }

    public PlayerDTO getWinner(){
        List<Player> players = playerRepository.findByOrderByWinRateDesc();

        return playerMapper.toDto(players.getFirst());
    }

    @Override
    public Integer deletePlayer(int playerId) {
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + " not found"));

        gameRepository.deleteAllByPlayerId(playerId);
        playerRepository.delete(existingPlayer);
        return playerId;
    }
}
