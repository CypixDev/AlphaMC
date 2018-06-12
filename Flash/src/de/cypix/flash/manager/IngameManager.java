package de.cypix.flash.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IngameManager {

    public static String InstantDeathItemname = "§czurück zum Checkpoint !";

    public static void setInv(Player p){
        ItemStack i = new ItemStack(Material.RED_ROSE);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(InstantDeathItemname);
        i.setItemMeta(im);
        p.getInventory().setItem(4, i);
    }

}
