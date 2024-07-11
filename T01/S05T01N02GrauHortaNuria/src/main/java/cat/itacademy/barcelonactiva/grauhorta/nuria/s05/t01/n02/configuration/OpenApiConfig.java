package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.configuration;

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
                        .title("Flor API")
                        .version("1.0")
                        .contact(new Contact().name("Nuria").email("nuria@protonmail.com"))
                        .description("API for managing Flors"));
    }
}
