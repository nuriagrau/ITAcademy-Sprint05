package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Game;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class GameMapper {

    public GameDTO toDto(Game game) {

        String id = game.getId();

        int dice1 = game.getDice1();

        int dice2 = game.getDice2();

        boolean wins = game.isWins();

        int playerId = game.getPlayerId();

        double winRate = game.getWinRate();;

        Date timestamp = game.getTimestamp();

        return new GameDTO(id, dice1, dice2, wins, playerId, winRate, timestamp);

    }

    public Game toEntity(GameDTO gameDto) {

        return new Game(gameDto.getDice1(), gameDto.getDice2(), gameDto.isWins(), gameDto.getPlayerId(), gameDto.getWinRate());
    }

    public List<GameDTO> toDTOList(List<Game> gamesList) {
        List<GameDTO> gamesDTOList = gamesList.stream()
                .map(game -> toDto(game))
                .collect(Collectors.toList());
        return gamesDTOList;
    }

}
