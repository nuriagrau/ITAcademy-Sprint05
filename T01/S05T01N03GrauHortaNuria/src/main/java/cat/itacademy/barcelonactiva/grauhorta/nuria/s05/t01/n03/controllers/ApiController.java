package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.services.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }


    @Operation(summary="Add new Flor")
    @PostMapping("/flor/clientFlorsAdd")
    public Mono<FlorDTO> addOneFlor(@RequestBody FlorDTO florDto) {

        return apiService.addFlorDTOFromApi9001("flor/add", florDto);
    }


    @Operation(summary="Update Flor")
    @PutMapping("/flor/clientFlorsUpdate")
    public Mono<FlorDTO> updateOneFlor(@RequestBody FlorDTO florDto) {

        return apiService.updateOneFlorDTOFromApi9001("flor/update/", florDto);
    }


    @Operation(summary="Delete Flor with id")
    @DeleteMapping("/flor/clientFlorsDelete/{id}")
    public Mono<Integer> deleteFlorById(@PathVariable int id) {

        return apiService.deleteOneFlorDTOFromApi9001("flor/delete/{id}", id);
    }


    @Operation(summary="Get One Flor by Id")
    @GetMapping("/flor/clientFlorsGetOne/{id}")
    public Mono<FlorDTO> getOneFlor(@PathVariable int id) {

        return apiService.getOneFlorDTOFromApi9001("flor/getOne/{id}", id);
    }


    @Operation(summary="Get All Flor")
    @GetMapping("/flor/clientFlorsAll")
    public Mono<List<FlorDTO>> getAllFlors() {

        return apiService.getListFlorDTOFromApi9001("flor/getAll");
    }

}
