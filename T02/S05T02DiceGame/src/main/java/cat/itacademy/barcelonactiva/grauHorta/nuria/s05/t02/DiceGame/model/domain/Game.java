package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain;


import jakarta.persistence.Id;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "games")
public class Game {

    @Id
    private String id;

    private int dice1;

    private int dice2;

    private boolean wins;

    private int playerId;

    private double winRate;

    private Date timestamp;


    public Game(int dice1, int dice2, boolean wins, int playerId, double winRate) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.wins = wins;
        this.playerId = playerId;
        this.winRate = winRate;
        this.timestamp = new Date();
    }

}
