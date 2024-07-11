package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.model.services.FlorService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary="Add new Flor")
    @PostMapping(path="/add")
    public ResponseEntity<FlorDTO> createFlor(@RequestBody FlorDTO florDto) {

        return new ResponseEntity<>(florService.createFlor(florDto), HttpStatus.CREATED);
    }


    @Operation(summary="Update Flor")
    @PutMapping(path="/update/")
    public ResponseEntity<FlorDTO> updateFlor(@RequestBody FlorDTO florDTO) {

        return new ResponseEntity<>(florService.updateFlor(florDTO), HttpStatus.OK);
    }


    @Operation(summary="Delete Flor with id")
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Integer> deleteFlor(@PathVariable int id) {

        return new ResponseEntity<>(florService.deleteFlor(id), HttpStatus.OK);
    }


    @Operation(summary="Get One Flor by Id")
    @GetMapping(path="/getOne/{id}")
    public ResponseEntity<FlorDTO> getOneFlor(@PathVariable int id) {

        return new ResponseEntity<>(florService.getOneFlor(id), HttpStatus.OK);
    }


    @Operation(summary="Get All Flor")
    @GetMapping(path="/getAll")
    public ResponseEntity<List<FlorDTO>> getAllFlors() {

        return new ResponseEntity<>(florService.getAllFlors(), HttpStatus.OK);
    }
}
