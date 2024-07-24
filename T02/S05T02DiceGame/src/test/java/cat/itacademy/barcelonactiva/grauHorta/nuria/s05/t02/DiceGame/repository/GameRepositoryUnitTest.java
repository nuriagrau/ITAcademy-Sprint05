package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.repository;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain.Game;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.GameRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
public class GameRepositoryUnitTest {

    @Autowired
    private GameRepository gameRepository;
    Game savedGame;
    Game returnedGame;

    //fer test en h2

    @DisplayName("GameRepositoryUnitTest - Test return game list")
    @Test
    void findByPlayerId_should_return_game_list() {
        Optional<List<Game>> optionalGameList = gameRepository.findByPlayerId(1);
        if(optionalGameList.isPresent()) {
            List<Game> gameList = optionalGameList.get();
            assertEquals(gameList.size(), 16);

        } else {
            fail("Expected 16 games for playerId 1, but found none");
        }
    }


    @DisplayName("GameRepositoryUnitTest - Test for insert new game")
    @Test
    void save_should_insert_new_game() {
        savedGame = new Game( 1, 4, true, 16, 0);
        returnedGame = gameRepository.save(savedGame);

        assertNotNull(returnedGame);
        assertEquals(16, returnedGame.getPlayerId());
        assertEquals(savedGame.getDice1(), returnedGame.getDice1());
    }


    @DisplayName("GameRepositoryUnitTest - Test delete all games of a player")
    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAllByPlayerId_should_delete_all_games_of_a_player() {
        gameRepository.deleteAllByPlayerId(16);
        Optional<List<Game>> optionalGameList = gameRepository.findByPlayerId(16);
        if(optionalGameList.isPresent()) {
            List<Game> gameList = optionalGameList.get();
            assertEquals(gameList.size(), 0);

        }
    }
}
