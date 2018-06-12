package de.cypix.flash.manager;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TrollManager {

    private static ArrayList<Player> list = new ArrayList<>();


    public static boolean isTrolling(Player p){
        if(list.contains(p)) return true;
        return false;
    }
    public static void setTrolling(Player p){
        remove(p);
        list.add(p);
    }
    public static void remove(Player p){
        if(isTrolling(p))list.remove(p);
    }
    public static void setItem(Player p){
        ItemStack i = new ItemStack(Material.BOOK);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("TrollMenue");
        i.setItemMeta(im);
        p.getInventory().setItem(0, i);
    }

}
