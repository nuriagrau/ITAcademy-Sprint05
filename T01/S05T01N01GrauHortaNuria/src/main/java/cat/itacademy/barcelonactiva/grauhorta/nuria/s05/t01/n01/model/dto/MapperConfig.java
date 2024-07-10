package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.model.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public Mapper mapperBean() {
        return new Mapper();
    }
}
