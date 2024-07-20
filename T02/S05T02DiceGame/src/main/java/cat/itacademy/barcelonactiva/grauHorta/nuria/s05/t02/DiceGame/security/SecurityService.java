package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class SecurityService {


    @Autowired
    private PlayerRepository playerRepository;

    public boolean isPlayerOwner(int playerId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        return playerRepository.findById(playerId)
                .map(player -> player.getUser().getUsername().equals(currentUserName))
                .orElse(false);
    }

}
