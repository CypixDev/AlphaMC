package de.cypix.buildffa.inventory;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.buildffa.main.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class VillagerListener implements Listener {

    String invname = "Inv Settings";

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER)){
            e.setCancelled(true);
            Player p = e.getPlayer();
            Inventory inv = Bukkit.createInventory(null, 9, invname);

            ArrayList<Integer> list = AlphaAPI.getInstance().getStatsPlayer(p).getBuildFFAInv().getInventory();
            for(int i = 0;i<9;i++){
                if(!list.isEmpty()){
                    inv.setItem(i, Items.valueOfId(list.get(i)).getItemStack());
                }else inv.setItem(i, Items.valueOfId(i).getItemStack());
            }
            p.openInventory(inv);
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onInteractVillager(PlayerInteractEntityEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().getTitle().equalsIgnoreCase(invname)){
            boolean ok = true;
            for(int i = 0;i<9;i++){
                if(e.getInventory().getItem(i) == null){
                    ok =false;
                }
            }
            if(ok){
                ArrayList<Integer> einsliste = new ArrayList<>();
                for(int i = 0;i<9;i++){
                    Items item = Items.valueOfDisplayName(e.getInventory().getItem(i).getItemMeta().getDisplayName());
                    einsliste.add(item.getId());
                }
                AlphaAPI.getInstance().getStatsPlayer((Player) e.getPlayer()).getBuildFFAInv().saveInv(einsliste);
                e.getPlayer().sendMessage("Die einstellungen wurden gespeichert !");

                final Player p = (Player)e.getPlayer();
                p.getInventory().clear();
                Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Integer> list = AlphaAPI.getInstance().getStatsPlayer((Player) e.getPlayer()).getBuildFFAInv().getInventory();
                        for(int i = 0;i<9;i++){
                            p.getInventory().setItem(i, Items.valueOfId(list.get(i)).getItemStack());
                        }
                    }
                }, 10);
            }else{
                e.getPlayer().sendMessage("error");
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory().getTitle().equalsIgnoreCase(invname)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){

    }


}
