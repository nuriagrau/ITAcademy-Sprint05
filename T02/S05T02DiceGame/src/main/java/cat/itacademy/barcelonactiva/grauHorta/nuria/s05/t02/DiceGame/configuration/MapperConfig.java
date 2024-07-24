package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.configuration;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.GameMapper;
import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.mapper.PlayerMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public GameMapper GameMapperBean() {

        return new GameMapper();
    }

    @Bean
    public PlayerMapper playerMapperBean() {

        return new PlayerMapper();
    }
}
