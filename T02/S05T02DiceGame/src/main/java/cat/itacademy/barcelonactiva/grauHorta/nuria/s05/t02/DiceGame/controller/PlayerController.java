package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller;


import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Operation(summary = "Add new Player")
    @PostMapping(path="")
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDto) {

        return new ResponseEntity<>(playerService.createPlayer(playerDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Update one Player")
    @PutMapping(path="")
    public ResponseEntity<PlayerDTO> updatePlayer(@RequestBody PlayerDTO playerDto) {

        return new ResponseEntity<>(playerService.updatePlayer(playerDto), HttpStatus.OK);
    }

    @Operation(summary="Delete Player with id")
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Integer> deletePlayer(@PathVariable int id) {

        return new ResponseEntity<>(playerService.deletePlayer(id), HttpStatus.OK);
    }


    @Operation(summary = "Get All Players")
    @GetMapping(path="/")
    public ResponseEntity<List<PlayerDTO>> getAllPlayersOrderByWinRateDesc() {

        return new ResponseEntity<>(playerService.getAllPlayersOrderByWinRateDesc(), HttpStatus.OK);
    }

    @Operation(summary = "Get WinRate Average")
    @GetMapping(path="/ranking")
    public ResponseEntity<Double> getAllPlayersWinRateAverage() {

        return new ResponseEntity<>(playerService.getWinRateAverage(), HttpStatus.OK);
    }

    @Operation(summary = "Get Loser Player")
    @GetMapping(path="/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser() {

        return new ResponseEntity<>(playerService.getLoser(), HttpStatus.OK);
    }

    @Operation(summary = "Get Winner Player")
    @GetMapping(path="/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner() {

        return new ResponseEntity<>(playerService.getWinner(), HttpStatus.OK);
    }


}
