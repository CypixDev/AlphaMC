package de.cypix.ttt.listener;

import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.SpecManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpectatorListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(Main.getInstance().getSpecs().contains(e.getPlayer())) {
            if(e.getItem() != null) {
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(SpecManager.SPECITEMNAME)) {
                    e.setCancelled(true);
                    e.getPlayer().openInventory(SpecManager.getInventory());
                }
            }
        }
    }

}
