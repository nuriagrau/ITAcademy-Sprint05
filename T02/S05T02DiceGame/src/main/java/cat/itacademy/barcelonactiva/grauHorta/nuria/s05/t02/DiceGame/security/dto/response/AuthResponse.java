package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;


    // for testing purposes only
    public void setToken(String token) {
        this.token = token;
    }
}
