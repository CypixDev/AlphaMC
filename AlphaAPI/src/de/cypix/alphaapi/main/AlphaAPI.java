package de.cypix.alphaapi.main;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.CMDStats;
import de.cypix.alphaapi.stats.JoinListener;
import de.cypix.alphaapi.stats.StatsPlayer;
import de.cypix.alphaapi.uuidfatcher.CMDUUID;
import de.cypix.alphaapi.uuidfatcher.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlphaAPI extends JavaPlugin {

    private static AlphaAPI instance;
    private UUID uuid;
    private HashMap<Player, StatsPlayer> statsPlayerList;

    @Override
    public void onEnable() {
        instance = this;
        Mysql mysql = new Mysql("localhost", 3306, "AlphaAPI", "AlphaAPI", "x2gabdac");
        mysql.createTable();
        statsPlayerList = new HashMap<>();
        init();
        //TODO: BuildFFA Update teil richtig machen ! xD
    }

    @Override
    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()){
            p.kickPlayer("§b§lDer Server wird neu gestartet !");
        }
    }

    private void init(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);

        getCommand("stats").setExecutor(new CMDStats());
        getCommand("uuid").setExecutor(new CMDUUID());
    }

    public static AlphaAPI getInstance() {
        return instance;
    }

    public StatsPlayer getStatsPlayer(Player p){
        return statsPlayerList.get(p);
    }

    public UUID getUuid() {
        return uuid;
    }

    public HashMap<Player, StatsPlayer> getStatsPlayerList() {
        return statsPlayerList;
    }
}
