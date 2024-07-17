package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.configuration.MappersConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {

    @Bean
    public Random random() {

        return new Random();
    }


}
