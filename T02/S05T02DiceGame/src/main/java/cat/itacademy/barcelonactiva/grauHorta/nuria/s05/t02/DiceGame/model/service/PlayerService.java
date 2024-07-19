package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.EmptyGamesListException;

import java.util.List;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDto);

    PlayerDTO updatePlayer(PlayerDTO playerDto);

    List<PlayerDTO> getAllPlayersOrderByWinRateDesc();

    Double getWinRateAverage() throws EmptyGamesListException;

    PlayerDTO getLoser();

    PlayerDTO getWinner();

    Integer deletePlayer(int id);
}
