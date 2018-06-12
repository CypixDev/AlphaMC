package de.Cypix.BungeeSystem.communication;

import de.Cypix.BungeeSystem.MYSQL.MYSQL;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedPlayerLoginEvent;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedPlayerLogoutEvent;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Coins implements Listener {

    public static HashMap<CloudPlayer, Integer> list = new HashMap<>();

    @EventHandler
    public void onJoin(ProxiedPlayerLoginEvent e){
        ResultSet rs = MYSQL.getResult("SELECT * FROM Coins WHERE UUID='"+e.getCloudPlayer().getUniqueId());
        try {
            list.put(e.getCloudPlayer(), rs.getInt("Coins"));
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @EventHandler
    public void onQuit(ProxiedPlayerLogoutEvent e){
        CloudPlayer cp = e.getCloudPlayer();
        MYSQL.update("UPDATE Coins SET Coins='"+list.get(cp)+"' WHERE UUID='"+cp.getUniqueId());
        list.remove(cp);
    }

}
