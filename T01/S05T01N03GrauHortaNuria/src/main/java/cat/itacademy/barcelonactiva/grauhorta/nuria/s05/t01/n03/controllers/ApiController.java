package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.controllers;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {

    private final ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api1")
    public Mono<String> callApi9001(@RequestParam String endpoint) {
        return apiService.getFromApi9001(endpoint);
    }

    @GetMapping("/api2")
    public Mono<String> callApi9002(@RequestParam String endpoint) {
        return apiService.getFromApi9002(endpoint);
    }

}
