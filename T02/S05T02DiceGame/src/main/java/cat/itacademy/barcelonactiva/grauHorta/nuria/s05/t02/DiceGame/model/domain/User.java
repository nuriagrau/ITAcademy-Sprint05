package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.domain;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NamedEntityGraph
@Data
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotEmpty(message = "User name may not be empty")
    private String name;

    @Column
    @NotEmpty(message = "User email may not be empty")
    private String email;

    @Column
    @NotEmpty(message = "User password may not be empty")
    private String password;

    @Column
    @NotEmpty(message = "User role may not be empty")
    private Role role;


}
