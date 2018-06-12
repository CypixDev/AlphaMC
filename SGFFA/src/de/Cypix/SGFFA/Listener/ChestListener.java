package de.Cypix.SGFFA.Listener;

import de.Cypix.SGFFA.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Random;

public class ChestListener implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        try {
            if (e.getClickedBlock().getType() == Material.CHEST) {
                e.setCancelled(true);
                p.getInventory().addItem(getRandomItem());
                e.getClickedBlock().setType(Material.AIR);
                final Block b = e.getClickedBlock();
                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getinstance(), new Runnable() {
                    @Override
                    public void run() {
                        b.setType(Material.CHEST);
                    }
                }, 60 * 20);
            }
        }catch(NullPointerException e1){}
    }

    private static ItemStack getRandomItem(){
        Random r = new Random();
        int itemnum = r.nextInt(16);
        if(itemnum == 0)return Item(Material.IRON_HELMET, "Eisen-Helm");
        if(itemnum == 1)return Item(Material.IRON_CHESTPLATE, "Eisen-Brust");
        if(itemnum == 2)return Item(Material.IRON_LEGGINGS, "Eisen-Hose");
        if(itemnum == 3)return Item(Material.IRON_BOOTS, "Eisen-Schuhe");
        if(itemnum == 4)return Item(Material.STONE_SWORD, "Stein-Schwert");
        if(itemnum == 5)return Item(Material.BOW, "Bogen");
        if(itemnum == 6)return Item(Material.ARROW, "Pfeil", 2);
        if(itemnum == 7)return Item(Material.COOKED_BEEF, "Fleisch", 2);
        if(itemnum == 8)return Item(Material.GOLD_HELMET, "Gold-Helm");
        if(itemnum == 9)return Item(Material.GOLD_CHESTPLATE, "Gold-Brust");
        if(itemnum == 10)return Item(Material.GOLD_LEGGINGS, "Gold-Hose");
        if(itemnum == 11)return Item(Material.GOLD_BOOTS, "Gold-Schuhe");
        if(itemnum == 12)return Item(Material.DIAMOND_PICKAXE, "Dia-Picke");
        if(itemnum == 13)return Item(Material.BREAD, "Brot", 3);
        if(itemnum == 14)return Item(Material.IRON_AXE, "Eisen-Axt");
        if(itemnum == 15)return Item(Material.IRON_SWORD, "Eisen-Schwert");
        return null;
    }

    private static ItemStack Item(Material material, String displayname){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        i.setItemMeta(im);
        return i;
    }
    private static ItemStack Item(Material material, String displayname, int amount){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }

}
