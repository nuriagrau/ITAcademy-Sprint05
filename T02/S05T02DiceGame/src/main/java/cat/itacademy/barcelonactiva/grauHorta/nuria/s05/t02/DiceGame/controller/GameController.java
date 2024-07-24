package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players/")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Add a new game to a player")
    @PostMapping(path="{id}/games/")
    public ResponseEntity<GameDTO> createGame(@PathVariable int id) {

        return new ResponseEntity<>(gameService.createGame(id), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete all games of a player")
    @DeleteMapping("{id}/games")
    public ResponseEntity<String> deleteAllGamesOfAPlayer(@PathVariable int id){
        gameService.deleteAllGamesByPlayerId(id);

        return new ResponseEntity<>(("All games deleted successfully for player id:" + id), HttpStatus.OK);
    }


    @Operation(summary = "Get All Games of a Player")
    @GetMapping("{id}/games")
    public ResponseEntity<List<GameDTO>> getAllGamesByPlayerId(@PathVariable int id) {

        return new ResponseEntity<>(gameService.getAllGamesByPlayer(id), HttpStatus.OK);
    }
}
