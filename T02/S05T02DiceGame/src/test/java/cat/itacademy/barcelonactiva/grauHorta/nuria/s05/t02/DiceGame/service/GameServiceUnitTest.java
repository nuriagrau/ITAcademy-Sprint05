package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.service;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Game;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl.GameServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GameServiceUnitTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private PlayerMapper playerMapper;
    @Mock
    private GameMapper gameMapper;


    Player player1;
    PlayerDTO player2;

    GameDTO game1;
    GameDTO game2;
    GameDTO game3;

    @BeforeEach
    @WithMockUser(roles = "ADMIN")
    void setUp() {
        player1 = new Player(1000, "Player1");
        player2 = new PlayerDTO(2000, "Player2", 0);
        game1 = new GameDTO("game1", 3, 4, true, 1000, 100);
        game2 = new GameDTO("game2", 4, 4, false, 2000, 0);
        game3 = new GameDTO("game3", 5, 2, true, 1000, 100);
    }



    //GameDTO createGame(int playerId);

    @DisplayName("GameServiceUnitTest - Test for insert new game")
    @Test
    void save_should_insert_new_game() {
        //Game newGame = gameMapper.toEntity(game1);
        GameDTO game = gameService.createGame(1000);
        verify(gameRepository, times(1)).save(gameMapper.toEntity(game));
        ArgumentCaptor<Game> GameArgumentCaptor = ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).save(GameArgumentCaptor.capture());
        Game gameCreated = GameArgumentCaptor.getValue();
        assertNotNull(gameCreated.getPlayerId());
        assertEquals(1000, gameCreated.getPlayerId());
    }


    //Integer deleteAllGamesByPlayerId(int playerId);
    @DisplayName("GameServiceUnitTest - Test for delete games by player id")
    @Test
    void deleteAllGamesByPlayerId_should_delete_all_games_of_a_player() {
        gameService.deleteAllGamesByPlayerId(1);
        verify(gameRepository, times(1)).deleteAllByPlayerId(1000);
        ArgumentCaptor<Integer> gameArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(gameRepository).deleteAllByPlayerId(gameArgumentCaptor.capture());
        Integer playerIdDeleted = gameArgumentCaptor.getValue();
        assertNotNull(playerIdDeleted);
        assertEquals(1000, playerIdDeleted);
    }


    //List<GameDTO> getAllGamesByPlayer(int playerId);
    @DisplayName("GamesServiceUnitTest - Test return games list")
    @Test
    void findAllGamesByPlayer_should_return_games_list() {
        List<GameDTO> gameList = gameService.getAllGamesByPlayer(1000);
        assertEquals(1, gameList.size());
        verify(gameRepository).findByPlayerId(1000);
    }


    //double calculateNewWinRateByPlayer(int playerId, boolean newGameWins);






}
