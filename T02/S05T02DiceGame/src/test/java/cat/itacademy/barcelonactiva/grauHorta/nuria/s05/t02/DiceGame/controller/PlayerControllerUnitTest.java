package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(value = {SpringExtension.class})
@WebMvcTest(controllers =  PlayerController.class)
public class PlayerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlayerService playerService;
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

    PlayerDTO player1;
    PlayerDTO player2;

    @BeforeEach
    @WithMockUser(roles = "ADMIN")
    void setUp() {
        //Player player1 = Player.builder().playerId(1000).playerName("player1").build();
        //Player player2 = Player.builder().playerId(2000).playerName("player2").build();
    }

    @AfterEach
    void tearDown() {
        player1 = player2 = null;
    }

    @DisplayName("PlayerControllerTest - Test return players list")
    @Test
    @WithMockUser(roles = "")
    void should_return_players_list() throws Exception {
        PlayerDTO player1 = new PlayerDTO(1000, "PlayerOne");
        PlayerDTO player2 = new PlayerDTO(2000, "PlayerTwo");
        when(playerService.getAllPlayersOrderByWinRateDesc()).thenReturn(Arrays.asList(player1, player2));
        mockMvc.perform(get("/players/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$").isArray());
    }

    @DisplayName("PlayerControllerTest - Test insert new player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_add_new_player() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("admin", "admin",
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );

        when(playerService.createPlayer(any(PlayerDTO.class))).thenReturn(player1);
        mockMvc.perform(post("/players")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(player1))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.playerId", is(player1.getPlayerId())))
                .andExpect(jsonPath("$.playerName", is(player1.getPlayerName())))
                .andExpect((jsonPath("$").isNotEmpty()));
    }

    @DisplayName("PlayerControllerTest - Test for update player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_update_existing_player() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("admin", "admin",
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
        when(playerService.updatePlayer(any(PlayerDTO.class)))
                .thenReturn(PlayerDTO.builder().playerId(2000).playerName("renamedPlayer2").build());
       mockMvc.perform(put("/players")
                       .with(user("admin").roles("ADMIN"))
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(Player.builder().playerName("renamedPlayer2").build()))
       )
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.playerId", is(player2.getPlayerId())))
               .andExpect(jsonPath("$.playerName", is("renamedPlayer2")))
               .andExpect(jsonPath("$").isNotEmpty());

    }

    @DisplayName("PlayerControllerTest - Test for delete player by id")
    @Test
    @WithMockUser(roles = "ADMIN")
    void should_remove_player() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("admin", "admin",
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
        mockMvc.perform(delete("/players/delete/1000")
                .with(user("admin").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
