package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String userName;

    private String email;

    private String password;

    private Role role;
}
