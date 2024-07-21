package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.security.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SecurityService {

    @Autowired
    private PlayerRepository playerRepository;


    public boolean isPlayerOwner(int playerId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUserName = authentication.getName();

        return playerRepository.findById(playerId)
                .map(player -> player.getUser().getEmail().equals(currentUserName))
                .orElse(false);
    }

}
