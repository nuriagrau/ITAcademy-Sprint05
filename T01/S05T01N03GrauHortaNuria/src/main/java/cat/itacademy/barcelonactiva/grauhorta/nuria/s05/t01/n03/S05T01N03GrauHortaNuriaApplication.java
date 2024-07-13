package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class S05T01N03GrauHortaNuriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03GrauHortaNuriaApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

}
