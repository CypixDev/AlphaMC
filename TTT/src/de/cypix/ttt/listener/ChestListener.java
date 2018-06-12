package de.cypix.ttt.listener;

import de.cypix.ttt.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ChestListener implements Listener {

    @EventHandler
    public void onChestInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getClickedBlock().getType().equals(Material.CHEST)) {
            addItem(p, e);
            e.setCancelled(true);
        }
        if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)){
            if(Main.getInstance().isHittingAllowed()){
                if(!contains(p.getInventory(), Material.IRON_SWORD)){
                    p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                }
                e.getClickedBlock().setType(Material.AIR);
            }else{
                p.sendMessage("Du must die SchutzZeit noch abwarten !");
            }
            e.setCancelled(true);
        }
    }

    public boolean contains(Inventory inv, Material type){
        for(ItemStack is : inv.getContents()) {
            if (is != null) {
                if(is.getType().equals(type)){
                    return true;
                }
            }
        }
        return false;
    }

    private void addItem(Player p, PlayerInteractEvent e){
        if(!contains(p.getInventory(), Material.WOOD_SWORD) || !contains(p.getInventory(), Material.STONE_SWORD) || !contains(p.getInventory(), Material.BOW)){
            Random ry = new Random();
            int random = ry.nextInt(3);
            if(random == 0){
                if(!contains(p.getInventory(), Material.WOOD_SWORD)){
                    p.getInventory().addItem(new ItemStack(Material.WOOD_SWORD));

                    e.getClickedBlock().setType(Material.AIR);
                }else{
                    addItem(p, e);
                }
            }
            if(random == 1){
                if(!contains(p.getInventory(), Material.STONE_SWORD)){
                    p.getInventory().addItem(new ItemStack(Material.STONE_SWORD));

                    e.getClickedBlock().setType(Material.AIR);
                }else{
                    addItem(p, e);
                }
            }
            if (random == 2){
                if(!contains(p.getInventory(), Material.BOW)){
                    p.getInventory().addItem(new ItemStack(Material.BOW));
                    ItemStack i = new ItemStack(Material.ARROW);
                    i.setAmount(32);
                    p.getInventory().addItem(i);

                    e.getClickedBlock().setType(Material.AIR);
                }else{
                    addItem(p, e);
                }
            }
        }
    }

}
