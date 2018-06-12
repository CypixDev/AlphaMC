package de.Cypix.Survival.Main;

import de.Cypix.Survival.CMD.*;
import de.Cypix.Survival.Events.PlayTimeChangeEvent;
import de.Cypix.Survival.FileManager.ConfigFile;
import de.Cypix.Survival.FileManager.MessagesFile;
import de.Cypix.Survival.Listener.AutoRespawnListener;
import de.Cypix.Survival.Listener.JoinListener;
import de.Cypix.Survival.Listener.TeleportListener;
import de.Cypix.Survival.Manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;


    @Override
    public void onEnable() {
        this.instance = this;
        createFiles();
        registerCMD();
        registerListener();
        startScheduler();

        for(Player p : Bukkit.getOnlinePlayers()){
            for(Player pp : Bukkit.getOnlinePlayers()){
                pp.showPlayer(p);
                p.showPlayer(pp);
            }
        }
    }

    public void startScheduler(){
        Manager.startPlayedTime();
    }

    public static main getInstance(){
        return instance;
    }

    public void createFiles(){
        MessagesFile.createDefaults();
        ConfigFile.createDefaults();
    }

    public void registerCMD(){

        this.getCommand("survival").setExecutor(new CMDSurvival());
        this.getCommand("spawn").setExecutor(new CMDSpawn());
        this.getCommand("gm").setExecutor(new CMDGm());
        this.getCommand("vanish").setExecutor(new CMDVanish());
        this.getCommand("home").setExecutor(new CMDHome());
        this.getCommand("homes").setExecutor(new CMDHomes());
        this.getCommand("sethome").setExecutor(new CMDSetHome());
    }


    public void registerListener(){

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new TeleportListener(), this);
        pm.registerEvents(new AutoRespawnListener(), this);


    }
}
