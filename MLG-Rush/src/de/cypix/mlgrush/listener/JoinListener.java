package de.cypix.mlgrush.listener;

import de.cypix.mlgrush.manager.LobbyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        LobbyManager.teleportToSpawn(p);
        LobbyManager.setItems(p);
        e.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
    }

}
