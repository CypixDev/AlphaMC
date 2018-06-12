package de.Cypix.FFA.Listener;

import de.Cypix.FFA.Manager.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof  Player){
            if(TeamManager.getList().containsKey(e.getEntity())){
                if(TeamManager.getList().get(e.getEntity()) == e.getDamager()){
                    e.setCancelled(true);
                    e.getDamager().sendMessage("Der ist in deinem Team !");
                }
            }
        }
    }
}
