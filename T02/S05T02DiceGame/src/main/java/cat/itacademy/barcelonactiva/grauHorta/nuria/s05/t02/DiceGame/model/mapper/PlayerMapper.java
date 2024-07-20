package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlayerMapper {

    UserRepository userRepository;


    public PlayerDTO toDto(Player player) {

        int playerId = player.getPlayerId();

        String playerName = player.getPlayerName();

        double winRate = player.getWinRate();

        Date creationDate = player.getCreationDate();

        //int userId = player.getUser().getUserId();

        return new PlayerDTO(playerId, playerName, winRate, creationDate);
    }

    public Player toEntity(PlayerDTO playerDto) {
        //String playerName = playerDto.getPlayerName();
        //playerName = (playerName.equalsIgnoreCase("") ? playerDto.getPlayerName() : "ANONYM");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("Current user cannot be found"));

        return new Player(playerDto.getPlayerName(), user);
    }



    public List<PlayerDTO> toDTOList(List<Player> playersList) {
        List<PlayerDTO> playersDtoList = playersList.stream()
                .map(player -> toDto(player))
                .collect(Collectors.toList());
        return playersDtoList;
    }
}
