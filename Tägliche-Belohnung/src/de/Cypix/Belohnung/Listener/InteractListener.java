package de.Cypix.Belohnung.Listener;

import de.Cypix.Belohnung.Manager.BelohnungManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        if(BelohnungManager.list.contains(e.getPlayer())) {
            if(e.getRightClicked().getCustomName().equalsIgnoreCase("§aTägliche Belohnung !")) {
                e.setCancelled(true);
                e.getRightClicked().remove();
                e.getPlayer().sendMessage("Der Villager wurde entfernt !");
                BelohnungManager.list.remove(e.getPlayer());
            }else{
                e.getPlayer().sendMessage("Dass ist ein Falscher Villager !");
            }
            return;
        }else if(e.getRightClicked().getCustomName().equalsIgnoreCase("§aTägliche Belohnung !")) {
            e.getPlayer().openInventory(BelohnungManager.getInventory(e.getPlayer()));
            e.setCancelled(true);
            return;
        }
    }


    @EventHandler
    public void onInteraact(PlayerInteractEntityEvent e){
        if(e.getRightClicked().getCustomName().equalsIgnoreCase("§aTägliche Belohnung !")) {
            e.getPlayer().openInventory(BelohnungManager.getInventory(e.getPlayer()));
            e.setCancelled(true);
        }
    }
}
