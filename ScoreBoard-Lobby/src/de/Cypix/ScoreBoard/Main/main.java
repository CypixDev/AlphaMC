package de.Cypix.ScoreBoard.Main;

import de.Cypix.ScoreBoard.Listener.JoinListener;
import de.Cypix.ScoreBoard.Manager.ScoreBoardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        this.instance = this;


        ScoreBoardManager.startScoreBoard();
        registerCMD();
        registerListener();
    }

    private void registerCMD(){

    }

    private void registerListener(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);


    }

    @Override
    public void onDisable() {



    }

    public static main getInstance(){
        return instance;
    }
}
