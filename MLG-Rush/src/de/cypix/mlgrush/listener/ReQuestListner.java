package de.cypix.mlgrush.listener;

import de.cypix.mlgrush.manager.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ReQuestListner implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            GameManager.onHit((Player)e.getDamager(), (Player)e.getEntity());
        }
    }

}
