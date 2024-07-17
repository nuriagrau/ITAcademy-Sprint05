package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;

import java.util.List;

public interface GameService {

    GameDTO createGame(int playerId);

    Integer deleteAllGamesByPlayerId(int playerId);

    List<GameDTO> getAllGamesByPlayer(int playerId);

    double calculateNewWinRateByPlayer(int playerId, boolean newGameWins);

}
