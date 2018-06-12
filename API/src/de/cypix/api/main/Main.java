package de.cypix.api.main;

import de.cypix.api.cmd.CMDStats;
import de.cypix.api.communication.ChannelListener;
import de.cypix.api.listener.JoinListener;
import de.cypix.api.mysql.Mysql;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private ChannelListener channelListener;

    @Override
    public void onEnable() {
        channelListener = new ChannelListener();
        Mysql my = new Mysql("localhost", 3306, "Stats", "Stats", "NAklfz324");
        Mysql.connect();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);

        this.getCommand("stats").setExecutor(new CMDStats());

        Bukkit.getMessenger().registerIncomingPluginChannel(this, "info", channelListener);

    }

    @Override
    public void onDisable() {

    }
}
