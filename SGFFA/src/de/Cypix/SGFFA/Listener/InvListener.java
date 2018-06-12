package de.Cypix.SGFFA.Listener;

import de.Cypix.SGFFA.Manager.MapManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvListener implements Listener{

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

}
