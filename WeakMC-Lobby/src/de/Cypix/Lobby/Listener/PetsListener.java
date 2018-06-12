package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.Manager.PetsManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PetsListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if (PetsManager.list.containsKey(e.getPlayer())) {
            new PetsManager().followPlayer((Creature) PetsManager.list.get(e.getPlayer()), e.getPlayer(), 1.6);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        e.setCancelled(true);
        /*Entity entity = (Entity)e.getEntity();
        if(PetsManager.list.containsValue(entity)){
            e.setCancelled(true);
        }
        if(e.getDamager() != null){
            Entity damager = (Entity)e.getDamager();
            if(PetsManager.list.containsValue(damager)){
                e.setCancelled(true);
            }
        }*/
    }

}
