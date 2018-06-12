package de.Cypix.CityBuild.Manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitManager {

    public static void setDefaultinv(Player p){
        p.getInventory().addItem(Item(Material.IRON_PICKAXE, 1));
        p.getInventory().addItem(Item(Material.IRON_AXE, 1));
        p.getInventory().addItem(Item(Material.COOKED_BEEF, 32));
        p.getInventory().setChestplate(Item(Material.CHAINMAIL_CHESTPLATE, 1));

    }

    private static ItemStack Item(Material mat, int amount){
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }

}
