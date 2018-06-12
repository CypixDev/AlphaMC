package de.Cypix.CityBuild.Listener;

import de.Cypix.CityBuild.Manager.WarpManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpListener implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(WarpManager.Inv_Name)){
                e.setCancelled(true);
                String name = e.getCurrentItem().getItemMeta().getDisplayName();
                name = name.replace(" ", "_");
                name = name.replace("§", "&");
                e.getWhoClicked().teleport(WarpManager.getWarpNameperDisplay(name));
            }
        }catch(NullPointerException e1){

        }
    }

}
