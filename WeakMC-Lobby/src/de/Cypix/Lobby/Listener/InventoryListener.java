package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Manager.*;
import de.Cypix.Lobby.inventar.InventarManager;
import de.Cypix.Lobby.navigator.NaviManager;
import de.Cypix.Lobby.settings.SettingsManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;


public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        try {
            if (!BuildManager.list.contains(e.getPlayer())) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Var.Navigator)) {
                        //navigator
                        NaviManager.openInv(e.getPlayer());
                        return;
                    }
                    if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Var.Book)){
                        //Buch öffnen
                        InfoBookManager.openBook(e.getPlayer());
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 10, 10);
                        return;
                    }
                    if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Var.Inventar)){
                        //Inventar öffnen
                        InventarManager.openInv(e.getPlayer());
                        return;
                    }
                    if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Var.Settings)){
                        SettingsManager.openInv(e.getPlayer());
                        return;
                    }
                }
            }
        }catch(NullPointerException e1){

        }
    }
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        try {
            Player p = e.getPlayer();
            ItemStack i = p.getItemInHand();
            if (!BuildManager.list.contains(e.getPlayer())) {
                if (i.getItemMeta().getDisplayName().equalsIgnoreCase(Var.Navigator)) {
                    //navigator
                    NaviManager.openInv(e.getPlayer());
                    return;
                }
                if(i.getItemMeta().getDisplayName().equalsIgnoreCase(Var.Book)){
                    //Buch öffnen
                    InfoBookManager.openBook(e.getPlayer());
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FIREWORK_LAUNCH, 10, 10);
                    return;
                }
                if(i.getItemMeta().getDisplayName().equalsIgnoreCase(Var.Inventar)){
                    //Inventar öffnen
                    if(p.hasPermission("Lobby.Inventar")){
                        InventarManager.openInv(e.getPlayer());
                    }else{
                        p.sendMessage("§cDiese Funktion ist noch in der Beta-Phase und nur vor erst nur für Premium Spieler verfügbar !");
                    }
                    return;
                }
            }
        }catch(NullPointerException e1){

        }
    }

    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent e){
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CLICK, 10, 10);
    }

    @EventHandler
    public void onClickInv(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(Var.Profiel_inv)){
                e.setCancelled(true);
                return;
            }
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(Var.Navigator)){
                Player p = (Player)e.getWhoClicked();
                p.updateInventory();

                e.setCancelled(true);
                return;
            }
        }catch (NullPointerException e1){

        }
    }
    @EventHandler
    public void onInteract(InventoryClickEvent e){
        if(!e.getInventory().getType().equals(InventoryType.PLAYER)){
            e.setCancelled(true);
        }
    }


}
