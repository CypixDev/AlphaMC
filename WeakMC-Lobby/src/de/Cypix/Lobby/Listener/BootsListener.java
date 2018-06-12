package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.inventar.Extras.Boots;
import de.Cypix.Lobby.Manager.OptionManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BootsListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getInventory().getBoots() != null){
            p.playEffect(p.getLocation(), Boots.valueof(p.getInventory().getBoots().getItemMeta().getDisplayName()).getEffect(), 10);
            for(Player pp : Bukkit.getOnlinePlayers()) {
                if(OptionManager.isAllowEffect(pp)){
                    if(pp != p)pp.playEffect(p.getLocation(), Boots.valueof(p.getInventory().getBoots().getItemMeta().getDisplayName()).getEffect(), 10);
                }
            }
        }
    }


}
