package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.repository;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerRepositoryUnitTest {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserRepository userRepository;

    private Player savedPlayer;

    private Player returnedPlayer;

     private int savedPlayerId;


    @DisplayName("PlayerRepositoryUnitTest - Test return a player by playerName")
    @Test
    void findByPlayerNameIgnoreCase_should_return_player() {
        Optional<Player> player = playerRepository.findByPlayerNameIgnoreCase("lilo");
        assertTrue(player.isPresent());
    }


    @DisplayName("PlayerRepositoryUnitTest - Test find all players ordered By WinRate Desc")
    @Test
    void findBy_all_players_ordered_by_winrate_desc_should_return_players_list_ordered(){
        List<Player> playersList = playerRepository.findByOrderByWinRateDesc();
        assertEquals(playersList.size(), playerRepository.findAll().size());
        assertEquals(playersList.getLast().getWinRate(), 0);
    }


    @DisplayName("PlayerRepositoryUnitTest - Test for insert new player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void save_should_insert_new_player() {
        Player savedPlayer = Player.builder().build();
        savedPlayer.setPlayerId(1000);
        savedPlayer.setPlayerName("TestPlayer");
        Player returnedPlayer = playerRepository.save(savedPlayer);
        Optional<Integer> optionalPlayerId = playerRepository.findByPlayerNameIgnoreCase("TestPlayer")
                .map(player -> player.getPlayerId());
        savedPlayerId = optionalPlayerId.orElseThrow(() -> new PlayerNotFoundException("This player does not exists"));

        assertNotNull(returnedPlayer);
        assertEquals(savedPlayerId, returnedPlayer.getPlayerId());
        assertEquals(savedPlayer.getPlayerName(), returnedPlayer.getPlayerName());
        }

    @DisplayName("PlayerRepositoryUnitTest - Test for delete player by id")
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteById_should_delete_player() {
        Optional<Integer> optionalPlayerId = playerRepository.findByPlayerNameIgnoreCase("TestPlayer")
                .map(player -> player.getPlayerId());
        savedPlayerId = optionalPlayerId.orElseThrow(() -> new PlayerNotFoundException("This player does not exists"));
        playerRepository.deleteById(savedPlayerId);
            Exception exception = assertThrows(NoSuchElementException.class, () -> {
                playerRepository.findById(savedPlayerId).get();
            });
            assertNotNull(exception);
            assertEquals(exception.getClass(), NoSuchElementException.class);
            assertEquals(exception.getMessage(), "No value present");
        }

}
