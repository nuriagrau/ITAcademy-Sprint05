package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
@Builder
public class PlayerDTO {

    @Id
    private int playerId;

    private String playerName;

    private double winRate;

    private Date creationDate;

    private int userId;


    public PlayerDTO(String playerName) {
        this.playerName = playerName;
    }

    public PlayerDTO() {
        this.playerName = "ANONYM";
    }
}
