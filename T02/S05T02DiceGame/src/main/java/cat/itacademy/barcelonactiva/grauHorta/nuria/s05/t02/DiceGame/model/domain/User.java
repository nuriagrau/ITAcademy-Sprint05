package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    @NotEmpty(message = "User name may not be empty")
    private String userName;

    @Column
    @NotEmpty(message = "User email may not be empty")
    private String email;

    @Column
    @NotEmpty(message = "User password may not be empty")
    private String password;

    @Column(name="registration_date", updatable = false)
    private Date registrationDate;

    @Column
    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "User role may not be empty")
    private Role role;

    //@OneToMany(mappedBy = "user")
   // private List<Player> players;


    public User(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
        this.role = role;
        //this.players = new ArrayList<Player>();
    }
}
