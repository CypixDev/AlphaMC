package de.cypix.gungame.forcemap;

import de.cypix.gungame.main.GunGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ForceMapListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if(e.getClickedInventory().getTitle().equalsIgnoreCase("Maps")){
            e.setCancelled(true);
            if(e.getClickedInventory() != null){
                if(e.getCurrentItem() != null){
                    GunGame.getInstance().getGameManager().setPreferredMap(e.getCurrentItem().getItemMeta().getDisplayName());
                    e.getWhoClicked().closeInventory();
                    e.getWhoClicked().sendMessage("§b§lDu hast die nächste map gewählt ! ["+e.getCurrentItem().getItemMeta().getDisplayName()+"]");
                }
            }
        }
    }


}
