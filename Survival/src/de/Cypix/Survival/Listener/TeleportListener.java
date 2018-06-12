package de.Cypix.Survival.Listener;

import de.Cypix.Survival.Manager.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(SpawnManager.map.containsKey(e.getPlayer())){
            Player p = e.getPlayer();
            Location loc = p.getLocation();
            Location loc1 = SpawnManager.map.get(p);
            if(loc.getX() == loc1.getX() && loc.getZ() == loc1.getZ()) {
                //nix !
            }else{
                Bukkit.getScheduler().cancelTask(SpawnManager.idelID.get(p));
                p.sendMessage("§cDa du dich bewegt hast, wurde der Teleport abgebrochen !");
                SpawnManager.map.remove(p);
                SpawnManager.idelID.remove(p);
            }
        }
    }
}
