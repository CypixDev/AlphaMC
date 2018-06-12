package de.Cypix.Belohnung.Listener;

import de.Cypix.Belohnung.Manager.BelohnungManager;
import de.Cypix.CoinsAPI.Coins.Coins;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        try {
            if (e.getClickedInventory().getName().equalsIgnoreCase("§aTägliche beloohnung")) {
                e.setCancelled(true);
                Player p = (Player)e.getWhoClicked();
                if(e.getCurrentItem().getType().equals(Material.PAPER)){
                    if(BelohnungManager.canTake(p)){
                        ItemStack i = e.getClickedInventory().getItem(13);
                        ItemMeta im = i.getItemMeta();
                        im.setDisplayName("§cGenommen !");
                        i.setItemMeta(im);
                        e.getClickedInventory().setItem(13, i);
                        p.updateInventory();
                        BelohnungManager.setGet(p);
                        if(Bukkit.getPluginManager().getPlugin("CoinsAPI") != null) {
                            Coins.addCoins(p, 100);
                            p.sendMessage("§aDu hast 100 Coins dazu bekommen !");
                        }else{
                            p.sendMessage("§cBitte die CoinsAPI hinzufügen um 100 Coins zu erhalten !");
                        }
                    }else{
                        p.sendMessage("Du must noch "+BelohnungManager.getRemainingTime(p)+ " warten !");
                    }
                }
            }
        }catch(NullPointerException e1){

        }
    }
}
