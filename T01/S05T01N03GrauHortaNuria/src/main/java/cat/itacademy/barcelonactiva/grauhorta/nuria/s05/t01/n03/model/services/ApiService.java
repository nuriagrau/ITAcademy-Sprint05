package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.services;

import cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.dto.FlorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class ApiService {

    private final WebClient webClientApi9001;

    @Autowired
    public ApiService(WebClient webClientApi9001) {
        this.webClientApi9001 = webClientApi9001;
    }



    public Mono<FlorDTO> addFlorDTOFromApi9001(String endpoint, FlorDTO florDto) {
        return webClientApi9001.post()
                .uri(endpoint)
                .bodyValue(florDto)
                .retrieve()
                .bodyToMono(FlorDTO.class);
    }


    public Mono<FlorDTO> updateOneFlorDTOFromApi9001(String endpoint, FlorDTO florDto) {
        return webClientApi9001.put()
                .uri(endpoint)
                .bodyValue(florDto)
                .retrieve()
                .bodyToMono(FlorDTO.class);
    }


    public Mono<Integer> deleteOneFlorDTOFromApi9001(String endpoint, int id) {
        return webClientApi9001.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .build(id))
                .retrieve()
                .bodyToMono(Integer.class);
    }


    public Mono<FlorDTO> getOneFlorDTOFromApi9001(String endpoint, int id) {
        return webClientApi9001.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .build(id))
                .retrieve()
                .bodyToMono(FlorDTO.class);
    }



    public Mono<List<FlorDTO>> getListFlorDTOFromApi9001(String endpoint) {
        return webClientApi9001.get()
                .uri(endpoint)
                .retrieve()
                .bodyToFlux(FlorDTO.class)
                .collectList();
    }

}
