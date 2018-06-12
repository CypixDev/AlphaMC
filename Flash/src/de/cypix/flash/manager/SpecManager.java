package de.cypix.flash.manager;

import de.cypix.flash.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SpecManager {
    private static ArrayList<Player> spec = new ArrayList<>();

    public static String invName = "§bPlayers";

    public static Inventory getPlayersInv(){
        int i = 0;
        Inventory inv = Bukkit.createInventory(null, 3*9, invName);
        for(Player p : Main.getInstace().getPlayers()){
            inv.setItem(i, createItemHead(p.getName(), null, 1));
            i++;
        }
        return inv;
    }

    public static ArrayList<Player> getSpec() {
        return spec;
    }

    public static ItemStack createItemHead(String name, List<String> lore, int amount) {

        @SuppressWarnings("deprecation")
        ItemStack item = new ItemStack(Material.getMaterial(397), 1, (short)3);
        SkullMeta mitem = (SkullMeta) item.getItemMeta();
        mitem.setDisplayName(name);
        mitem.setLore(lore);

        mitem.setOwner(name);
        item.setItemMeta(mitem);
        item.setAmount(amount);


        return item;
    }

}
