package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.controller.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    private String email;

    @jakarta.validation.constraints.NotBlank
    private String password;
}
