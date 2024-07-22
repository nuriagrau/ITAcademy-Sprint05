package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@WithMockUser
public class PlayerControllerIntegrationTest {

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("PlayerControllerIntegrationTest - Test return players list")
    @Test
    void whenListPlayers_thenReturnStatusOkAndPlayersList() throws Exception {
        mvc.perform(get("/players/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for insert new player")
    @Test
    void whenCreatePlayer_thenReturnStatusCreatedAndPlayer() throws Exception {
        Player newPlayer = Player.builder().playerName("newPlayer").build();
        mvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("newPlayer"));
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for insert new player return PlayerAlreadyExistsException")
    @Test
    void givenPlayerExists_whenCreatePlayer_thenReturnStatusCreatedAndPlayer() throws Exception {
        Player newPlayer = Player.builder().playerName("newPlayer").build();
        mvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for update player")
    @Test
    void whenUpdatePlayer_thenReturnStatusOkAndUpdatedPlayer() throws Exception {
        Player updatedPlayer = Player.builder().playerId(2000).playerName("updatedNewPlayer").build();
        mvc.perform(MockMvcRequestBuilders.put("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(2000))
                .andExpect(jsonPath("$.name").value("updatedNewPlayer"));
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for update player return PlayerNotFoundException")
    @Test
    void givenPlayerNotExist_whenUpdatePlayer_thenReturnStatusOkAndUpdatedPlayer() throws Exception {
        Player updatedPlayer = Player.builder().playerId(4000).playerName("nonExistingPlayer").build();
        mvc.perform(MockMvcRequestBuilders.put("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPlayer))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for delete player by Id")
    @Test
    void whenDeletePlayer_thenReturnStatusOk_andPlayerIsDeleted() throws Exception {
        mvc.perform(delete("/players/delete/{id}", 3000)
                .with(csrf()))
                .andDo(print())
                .andExpect((status().isOk()));
    }

    @DisplayName("PlayerControllerIntegrationTest - Test for delete player return PlayerNotFoundException")
    @Test
    void givenPlayerNotExist_whenDeletePlayer_thenReturnPlayerNotFoundException() throws Exception {
        mvc.perform(delete("/players/delete/{id}", 4000)
                        .with(csrf()))
                .andDo(print())
                .andExpect((status().isOk()));
    }


}
