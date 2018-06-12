package de.Cypix.SGFFA.Main;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.Cypix.SGFFA.CMD.CMDForceMap;
import de.Cypix.SGFFA.CMD.CMDNextMap;
import de.Cypix.SGFFA.CMD.CMDSgffa;
import de.Cypix.SGFFA.CMD.CMDStats;
import de.Cypix.SGFFA.Listener.*;
import de.Cypix.SGFFA.MYSQL.MYSQL;
import de.Cypix.SGFFA.Manager.MapManager;
import de.Cypix.SGFFA.Manager.ScoreBoardManager;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        this.instance = this;

        MYSQL my = new MYSQL("localhost", 3306, "Stats", "Stats", "NAklfz324");
        my.connect();
        my.createTable();

        ScoreBoardManager.startScoreBoard();
        registerEvents();
        MapManager.startMaps();
        this.getCommand("sgffa").setExecutor(new CMDSgffa());
        this.getCommand("forcemap").setExecutor(new CMDForceMap());
        this.getCommand("nextmap").setExecutor(new CMDNextMap());
        this.getCommand("Stats").setExecutor(new CMDStats());

    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new StatsListener(), this);
        pm.registerEvents(new ElseListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new ChestListener(), this);
        pm.registerEvents(new InvListener(), this);

    }

    public static main getinstance(){
        return instance;
    }





}
