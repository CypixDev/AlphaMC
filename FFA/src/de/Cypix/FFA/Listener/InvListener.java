package de.Cypix.FFA.Listener;

import de.Cypix.FFA.Manager.BuildManager;
import de.Cypix.FFA.Manager.MapManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class InvListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        try{
            e.setCancelled(true);
            if(e.getClickedInventory().getName().equalsIgnoreCase("§b§lMaps")){
                MapManager.forceMap = e.getCurrentItem().getItemMeta().getDisplayName();
                Player p = (Player)e.getWhoClicked();
                p.sendMessage("§8➤ §7Die nächste Map ist §b"+MapManager.forceMap);
                p.playSound(p.getLocation(), Sound.BAT_HURT, 12, 12);
                p.closeInventory();
            }
        }catch(NullPointerException e1){

        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent e){
        if(BuildManager.isBuilding((Player)e.getWhoClicked()))return;
        e.setCancelled(true);
    }
}
