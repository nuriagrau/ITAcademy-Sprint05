package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.services.FlorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flor")
public class FlorController {

    @Autowired
    private final FlorService florService;

    public FlorController(FlorService florService) {
        this.florService = florService;
    }


    @PostMapping(path="/add")
    public ResponseEntity<FlorDTO> createFlor(@RequestBody FlorDTO florDto) {

        return new ResponseEntity<>(florService.createFlor(florDto), HttpStatus.CREATED);
    }


    @PutMapping(path="/update/{id}")
    public ResponseEntity<FlorDTO> updateFlor(@PathVariable(value="Flor ID", required = true) int id, @RequestBody FlorDTO florDTO) {

        return new ResponseEntity<>(florService.updateFlor(id, florDTO), HttpStatus.OK);
    }


    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Integer> deleteFlor(@PathVariable (value="Flor ID", required = true) int id) {

        return new ResponseEntity<>(florService.deleteFlor(id), HttpStatus.OK);
    }


    @GetMapping(path="/getOne/{id}")
    public ResponseEntity<FlorDTO> getOneFlor(@PathVariable (value="Flor ID", required = true) int id) {

        return new ResponseEntity<>(florService.getOneFlor(id), HttpStatus.OK);
    }


    @GetMapping(path="/getAll")
    public ResponseEntity<List<FlorDTO>> getAllFlors() {

        return new ResponseEntity<>(florService.getAllFlors(), HttpStatus.OK);
    }
}
