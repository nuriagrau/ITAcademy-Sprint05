package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "win_rate")
    private double winRate;

    @Column(name="creation_date", updatable = false)
    private Date creationDate;

   // @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "user_id")
   // private User user;

    public Player(String playerName) {
        this.playerName = playerName;
        this.winRate = 0;
        this.creationDate = new Date();
       // this.user = user;
    }

}
