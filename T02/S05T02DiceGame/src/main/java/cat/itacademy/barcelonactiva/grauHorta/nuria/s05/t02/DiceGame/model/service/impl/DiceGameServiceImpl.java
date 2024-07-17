package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.impl;

import cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service.DiceGameService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceGameServiceImpl implements DiceGameService {

    private final Random random;

    public DiceGameServiceImpl() {
        this.random = new Random();
    }

    @Override
    public int rollDice() {
        return random.nextInt(6) + 1;
    }

    @Override
    public boolean gameWins(int dice1, int dice2) {

        return (dice1 + dice2 == 7);
    }
}
