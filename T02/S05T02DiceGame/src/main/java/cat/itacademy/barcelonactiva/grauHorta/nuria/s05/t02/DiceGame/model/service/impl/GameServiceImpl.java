package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Game;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.EmptyGamesListException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.DiceGameService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.round;

@Service
public class GameServiceImpl implements GameService {


    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    private final GameMapper gameMapper;

    private final DiceGameService diceGameService;


    @Autowired
    public GameServiceImpl(GameRepository gameRepository, PlayerRepository playerRepository, GameMapper gameMapper, DiceGameService diceGameService) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameMapper = gameMapper;
        this.diceGameService = diceGameService;
    }

    public GameDTO createGame(int playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("There is no player with id : " + playerId));

        int newGameDice1 = diceGameService.rollDice();
        int newGameDice2 = diceGameService.rollDice();
        boolean newGameWins = diceGameService.gameWins(newGameDice1, newGameDice2);
        double newWinRate;
        if (gameRepository.findAll().stream().filter(g -> g.getPlayerId() == playerId).count() == 0) {
            newWinRate = (newGameWins)? 1/1 * 100 : 0/1 * 100;

        } else {
            newWinRate = calculateNewWinRateByPlayer(playerId, newGameWins);
        }

        Game newGame = gameRepository.save(new Game(newGameDice1, newGameDice2, newGameWins, playerId, newWinRate));

        player.setWinRate(newWinRate);
        playerRepository.save(player);

        return gameMapper.toDto(newGame);
    }


    @Override
    public double calculateNewWinRateByPlayer(int playerId, boolean newGameWins) {
        playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("There is no player with id : " + playerId));

        int countOfPlayerGames = (int) gameRepository.findAll().stream()
                .filter(g -> g.getPlayerId() == playerId)
                .count();

        int countOfPlayerWins = (int) gameRepository.findAll().stream()
                .filter(g -> g.getPlayerId() == playerId)
                .filter(g -> g.isWins() == true)
                .count();

        return countOfPlayerWins * 100 / countOfPlayerGames;

    }

    public Integer deleteAllGamesByPlayerId(int playerId) {
       playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("There is no player with id : " + playerId));

       gameRepository.findByPlayerId(playerId).stream()
               .forEach(game -> gameRepository.deleteAllByPlayerId(playerId));

       return playerId;
    }


    public List<GameDTO> getAllGamesByPlayer(int playerId) {
        playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("There is no player with id : " + playerId));

         List<Game> games = gameRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new EmptyGamesListException("There are no games for player id " + playerId));

         return gameMapper.toDTOList(games);
    }

}
