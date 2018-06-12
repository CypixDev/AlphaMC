package de.Cypix.CoinsAPI.Main;

import de.Cypix.CoinsAPI.listener.ChannelListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.Cypix.CoinsAPI.CMD.CMDCoins;
import de.Cypix.CoinsAPI.MYSQL.MYSQL;
import de.Cypix.CoinsAPI.MYSQL.Manager;

public class main extends JavaPlugin implements Listener {

    private static main instance;

    @Override
    public void onEnable() {
        this.instance = this;
        MYSQL mysql = new MYSQL("localhost", 3306, "Coins", "x2gabdac", "Coins");
        mysql.connect();
        mysql.createTable();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "info", new ChannelListener());

        for(Player p : Bukkit.getOnlinePlayers()){
            Manager.coins.put(p, new Manager(p).getCoins());
        }

        Bukkit.getPluginManager().registerEvents(this, this);
        this.getCommand("coins").setExecutor(new CMDCoins());
    }

    @Override
    public void onDisable() {



    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Manager m = new Manager(e.getPlayer());
        m.addPlayer();
    }

    public static main getInstance() {
        return instance;
    }
}
