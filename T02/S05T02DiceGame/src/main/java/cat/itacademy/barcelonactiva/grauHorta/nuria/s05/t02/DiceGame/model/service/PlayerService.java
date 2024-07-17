package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.exceptions.EmptyGamesListException;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    PlayerDTO createPlayer(PlayerDTO playerDto);

    PlayerDTO updatePlayer(PlayerDTO playerDto);

    List<PlayerDTO> getAllPlayersOrderByWinRateDesc();

    Double getWinRateAverage() throws EmptyGamesListException;

    PlayerDTO getLoser();

    PlayerDTO getWinner();

}
