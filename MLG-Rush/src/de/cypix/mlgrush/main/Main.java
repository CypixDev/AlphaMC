package de.cypix.mlgrush.main;

import de.cypix.mlgrush.cmd.CMDMR;
import de.cypix.mlgrush.listener.HidPlayerListener;
import de.cypix.mlgrush.listener.JoinListener;
import de.cypix.mlgrush.listener.ReQuestListner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        init();
    }

    @Override
    public void onDisable() {
        //save stats ?!

    }
    private void init(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new ReQuestListner(), this);
        pm.registerEvents(new HidPlayerListener(), this);


        getCommand("mr").setExecutor(new CMDMR());
    }


    public static Main getInstance() {
        return instance;
    }
}
