package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameDTO {

    private String id;

    private int dice1;

    private int dice2;

    private boolean wins;

    private int playerId;

    private double winRate;

    private Date timestamp;

    public GameDTO(String id, int dice1, int dice2, boolean wins, int playerId, double winRate) {
        this.id = id;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.wins = wins;
        this.playerId = playerId;
        this.winRate = winRate;
    }

    public String getId() {
        return id;
    }
}
