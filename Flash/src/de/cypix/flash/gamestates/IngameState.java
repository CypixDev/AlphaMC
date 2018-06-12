package de.cypix.flash.gamestates;

import de.cypix.flash.main.Main;
import org.bukkit.Bukkit;

public class IngameState extends GameState {


    @Override
    public void start() {
        Bukkit.broadcastMessage(Main.pr+"Der Countdown ist beendet !");
        Bukkit.broadcastMessage("                      ");
        Bukkit.broadcastMessage(Main.pr+"Last dass fröhliche springen beginnen !");
    }

    @Override
    public void stop() {

    }
}

