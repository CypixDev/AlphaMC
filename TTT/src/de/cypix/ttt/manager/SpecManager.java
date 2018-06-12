package de.cypix.ttt.manager;

import de.cypix.ttt.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SpecManager {

    public static final String SPECITEMNAME = "§aSpieler", SPECINVNAME = "§cSpieler";


    public static void setSpectator(Player p){
        for(Player pp : Main.getInstance().getPlayers()){
            pp.hidePlayer(p);
        }
        for(Player pp : Main.getInstance().getSpecs()){
            pp.showPlayer(p);
        }
        ItemStack i = new ItemStack(Material.COMPASS);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(SPECITEMNAME);
        i.setItemMeta(im);
        p.getInventory().setHeldItemSlot(0);
        p.getInventory().setItem(0, i);
        p.setAllowFlight(true);
    }

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(null, 2*9, SPECINVNAME);
        for(Player p : Main.getInstance().getPlayers()){
            inv.addItem(createItemHead(p.getName(), "§7"+p.getName()));
        }
        return inv;
    }



    public static ItemStack createItemHead(String name, String displayname) {

        ItemStack item = new ItemStack(Material.getMaterial(397), 1, (short)3);
        SkullMeta mitem = (SkullMeta) item.getItemMeta();
        mitem.setDisplayName(displayname);
        mitem.setOwner(name);
        item.setItemMeta(mitem);
        return item;


    }

}
