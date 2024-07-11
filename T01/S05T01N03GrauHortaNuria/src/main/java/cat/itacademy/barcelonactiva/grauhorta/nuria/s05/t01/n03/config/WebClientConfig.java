package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClientApi9001() {
        return WebClient.builder()
                .baseUrl("http://localhost:9001")
                .build();
    }

    @Bean
    public WebClient webClientApi9002() {
        return WebClient.builder()
                .baseUrl("http://localhost:9002")
                .build();
    }
}
