package de.Cypix.FFA.Manager;

import de.Cypix.FFA.Main.main;
import de.Cypix.FFA.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopManager {

    public static Inventory getShopInv(){
        Inventory inv = Bukkit.createInventory(null, 3*9, main.getShopInvName);



        return inv;
    }

    public static void openShopInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 3*9, main.getShopInvName);

        inv.setItem(12, ShopItem.BOOST.getItemStack());
        inv.setItem(13, ShopItem.GRANATE.getItemStack());
        inv.setItem(14, ShopItem.KOBWEB.getItemStack());

        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 10, 10);
    }

    public static void setShopItem(Player p){
        ItemStack i = new ItemStack(Material.CHEST);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(main.getshopitemname);
        i.setItemMeta(im);
        p.getInventory().setItem(8, i);
    }

}
