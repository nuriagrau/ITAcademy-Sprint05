package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.service;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Player;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceUnitTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private PlayerMapper playerMapper;
    @InjectMocks
    private PlayerServiceImpl playerService;
    private PlayerDTO player1;
    private PlayerDTO player2;

    private Player player;

    private Player otherPlayer;

    @BeforeEach
    void setUp() {
        player1 = new PlayerDTO(1000, "player1", 100);
        player2 = new PlayerDTO(2000, "player2", 0);
        player = new Player(1000, "player1");
        otherPlayer = new Player(1000, "player1");
    }

    @AfterEach
    void tearDown() {
        player1 = player2 = null;
        player = null;
    }

    @DisplayName("PlayerServiceUnitTest - Test for insert new player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void createPlayer_should_insert_player() {
        when(playerMapper.toEntity(any(PlayerDTO.class))).thenReturn(player);
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        playerService.createPlayer(player1);

        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(playerArgumentCaptor.capture());
        Player playerCreated = playerArgumentCaptor.getValue();
        assertNotNull(playerCreated.getPlayerId());
        assertEquals("player1", playerCreated.getPlayerName());
    }

    @DisplayName("PlayerServiceUnitTest - Test for update Player")
    @Test
    void save_should_update_existing_player() {
        when(playerMapper.toDto(any(Player.class))).thenReturn(player1);
        when(playerRepository.findById(1000)).thenReturn(java.util.Optional.of(player));
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        player1.setPlayerName("updatedPlayer1");
        PlayerDTO updatedPlayer = playerService.updatePlayer(player1);
        assertEquals(updatedPlayer.getPlayerName(), "updatedPlayer1");
    }

}
