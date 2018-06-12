package de.cypix.flash.listener;

import de.cypix.flash.manager.CheckPointManager;
import de.cypix.flash.manager.MapManager;
import de.cypix.flash.mysql.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MapListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        int y = (int) p.getLocation().getY();
        int lasty = MapManager.getRespawn(MapManager.currentmap);
        if(y < lasty){
            PlayerStats stats = new PlayerStats(p);
            stats.addDeath();
            p.teleport(CheckPointManager.getLastCheckpoint(p));
        }
    }

}
