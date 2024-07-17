package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.configuration.MappersConfig;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("DiceGame API")
                        .version("1.0")
                        .contact(new Contact().name("Nuria").email("nuriagrau@protonmail.com"))
                        .description("API for managing DiceGame"));

    }
}
