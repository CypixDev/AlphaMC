package de.Cypix.FFA.Main;

import de.Cypix.FFA.CMD.*;
import de.Cypix.FFA.Listener.*;
import de.Cypix.FFA.MYSQL.MYSQL;
import de.Cypix.FFA.MYSQL.PlayerStats;
import de.Cypix.FFA.Manager.KillStreakManager;
import de.Cypix.FFA.Manager.MapManager;
import de.Cypix.FFA.Manager.ScoreBoardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;
    public static String getshopitemname = "§8➤ §b§lShop", getShopInvName = "§6§lShop";

    @Override
    public void onEnable() {
        instance = this;

        MYSQL mysql = new MYSQL("localhost", 3306, "Stats", "Stats", "NAklfz324");
        mysql.connect();
        mysql.createTable();

        registerCMD();
        registerListener();


        PlayerStats.loadallplayersStats();
        KillStreakManager.addAllPlayer();


        MapManager.startMaps();
        ScoreBoardManager.startScoreBoard();
    }

    @Override
    public void onDisable() {
        PlayerStats.saveallplayersStats();

    }


    public void registerListener(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InvListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new StatsListener(), this);
        pm.registerEvents(new DamageListener(), this);
        pm.registerEvents(new ElseListener(), this);
        pm.registerEvents(new ShopListener(), this);
    }

    public void registerCMD(){
        this.getCommand("ffa").setExecutor(new CMDFfa());
        this.getCommand("forcemap").setExecutor(new CMDForceMap());
        this.getCommand("stats").setExecutor(new CMDStats());
        //this.getCommand("team").setExecutor(new CMDTeam());
        this.getCommand("build").setExecutor(new CMDBuild());
        this.getCommand("nextmap").setExecutor(new CMDNextMap());


    }

    public static main getinstance(){
        return instance;
    }

}
