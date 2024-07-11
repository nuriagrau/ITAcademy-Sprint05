package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ApiService {

    private final WebClient webClientApi9001;
    private final WebClient webClientApi9002;

    @Autowired
    public ApiService(WebClient webClientApi9001, WebClient webClientApi9002) {
        this.webClientApi9001 = webClientApi9001;
        this.webClientApi9002 = webClientApi9002;
    }

    public Mono<String> getFromApi9001(String endpoint) {
        return webClientApi9001.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getFromApi9002(String endpoint) {
        return webClientApi9002.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class);
    }

}
