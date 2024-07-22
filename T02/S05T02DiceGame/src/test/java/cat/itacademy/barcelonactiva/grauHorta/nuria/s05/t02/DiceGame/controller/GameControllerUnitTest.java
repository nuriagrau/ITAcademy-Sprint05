package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.GameService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.PlayerService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.UserService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.JwtService;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.impl.JwtServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(value = {SpringExtension.class})
@WebMvcTest(controllers =  GameController.class)
public class GameControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
    @MockBean
    private GameService gameService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private JwtServiceImpl jwtServiceImpl;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PlayerMapper playerMapper;
    @MockBean
    private GameMapper gameMapper;

    PlayerDTO player1;
    PlayerDTO player2;

    GameDTO game1;
    GameDTO game2;
    GameDTO game3;

    @BeforeEach
    @WithMockUser(roles = "ADMIN")
    void setUp() {
        player1 = new PlayerDTO(1000, "Player1", 100);
        player2 = new PlayerDTO(2000, "Player2", 0);
        game1 = new GameDTO("game1", 3, 4, true, 1000, 100);
        game2 = new GameDTO("game2", 4, 4, false, 2000, 0);
        game3 = new GameDTO("game3", 5, 2, true, 1000, 100);
    }

    @AfterEach
    void tearDown() {
        player1 = player2 = null;
        game1 = game2 = game3 = null;
    }

    @DisplayName("GameControllerTest - Test insert new game")
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_add_new_game_to_a_player() throws Exception {
        when(gameService.createGame(any(int.class))).thenReturn(game1);
        mockMvc.perform(MockMvcRequestBuilders.post("/players/1000/games/")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game1))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(game1.getId())))
                .andExpect(jsonPath("$.playerId", is(game1.getPlayerId())))
                .andExpect((jsonPath("$").isNotEmpty()));
    }


    @DisplayName("PlayerControllerTest - Test for delete all games of a player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_remove_all_games_of_a_player() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/players/1000/games")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @DisplayName("PlayerControllerTest - Test return games list of a player")
    @Test
    @WithMockUser(roles = "")
    void should_return_games_list_of_a_player() throws Exception {
        when(gameService.getAllGamesByPlayer(1000)).thenReturn(Arrays.asList(game1, game3));
        mockMvc.perform(get("/players/1000/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$").isArray());
    }

}
