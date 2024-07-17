package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.User;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    //UserRepository userRepository;

    /*public PlayerMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

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
        //User user = userRepository.getReferenceById(playerDto.getUserId());

        return new Player(playerDto.getPlayerName());
    }



    public List<PlayerDTO> toDTOList(List<Player> playersList) {
        List<PlayerDTO> playersDtoList = playersList.stream()
                .map(player -> toDto(player))
                .collect(Collectors.toList());
        return playersDtoList;
    }
}
