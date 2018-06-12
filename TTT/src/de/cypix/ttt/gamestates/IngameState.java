package de.cypix.ttt.gamestates;

import de.cypix.ttt.Var;
import de.cypix.ttt.countdown.InGameCountDown;
import org.bukkit.Bukkit;

public class IngameState extends GameState {

    private InGameCountDown inGameCountDown;

    @Override
    public void start() {
        inGameCountDown = new InGameCountDown();
        inGameCountDown.run();
    }

    @Override
    public void stop() {

    }

    public InGameCountDown getInGameCountDown() {
        return inGameCountDown;
    }
}
