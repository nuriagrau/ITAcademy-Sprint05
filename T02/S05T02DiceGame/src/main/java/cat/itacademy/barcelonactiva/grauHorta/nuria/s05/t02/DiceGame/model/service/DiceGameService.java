package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.service;

import java.util.Random;

public interface DiceGameService {

    int rollDice();

    boolean gameWins(int dice1, int dice2);


}
