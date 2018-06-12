package de.Cypix.BungeeSystem.Main;

import de.Cypix.BungeeSystem.CMD.*;
import de.Cypix.BungeeSystem.Listener.ChannelListener;
import de.Cypix.BungeeSystem.Listener.ChatListener;
import de.Cypix.BungeeSystem.Listener.LoginListener;
import de.Cypix.BungeeSystem.MYSQL.CoinsMYSQL;
import de.Cypix.BungeeSystem.MYSQL.MYSQL;
import de.Cypix.BungeeSystem.alphaapi.JoinListener;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class main extends Plugin {

    private static main instance;

    @Override
    public void onEnable() {
        instance = this;

        getProxy().getLogger().info("§cBungeeSystem wurde geladen !");

        MYSQL my = new MYSQL("localhost", 3306, "Ban", "Ban", "NAklfz324");
        my.connect();
        my.createTable();

        CoinsMYSQL mysql = new CoinsMYSQL("localhost", 3306, "Coins", "NAklfz324", "Coins");
        mysql.connect();
        mysql.createTable();

        getProxy().getPluginManager().registerCommand(this, new CMDReport());
        getProxy().getPluginManager().registerCommand(this, new CMDBan());
        getProxy().getPluginManager().registerCommand(this, new CMDCheck());
        getProxy().getPluginManager().registerCommand(this, new CMDHelp());
        getProxy().getPluginManager().registerCommand(this, new CMDTempBan());
        getProxy().getPluginManager().registerCommand(this, new CMDUnBan());
        getProxy().getPluginManager().registerCommand(this, new CMDUnBanId());
        getProxy().getPluginManager().registerCommand(this, new CMDUnMute());
        getProxy().getPluginManager().registerCommand(this, new CMDKick());
        getProxy().getPluginManager().registerCommand(this, new CMDBroadCast());
        getProxy().getPluginManager().registerCommand(this, new CMDBugReport());
        getProxy().getPluginManager().registerCommand(this, new CMDPing());
        getProxy().getPluginManager().registerCommand(this, new CMDTc());
        getProxy().getPluginManager().registerCommand(this, new CMDInfo());

        BungeeCord.getInstance().registerChannel("info");

        getProxy().getPluginManager().registerListener(this, new LoginListener());
        getProxy().getPluginManager().registerListener(this, new ChatListener());
        getProxy().getPluginManager().registerListener(this, new ChannelListener());
        getProxy().getPluginManager().registerListener(this, new JoinListener());

    }

    public static main getInstance(){
        return instance;
    }

}
