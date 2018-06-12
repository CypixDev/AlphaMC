package de.Cypix.BungeeSystem.alphaapi;

import de.Cypix.BungeeSystem.MYSQL.AlphaMysql;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(LoginEvent e){
        AlphaMysql.update("INSERT INTO ");
    }
}
