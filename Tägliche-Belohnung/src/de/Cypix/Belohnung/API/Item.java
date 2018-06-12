package de.Cypix.Belohnung.API;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Item {

    public static ItemStack getItem(Material material, String displayname, List<String> lore, int amount){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        im.setLore(lore);
        i.setItemMeta(im);

        i.setAmount(amount);
        return i;
    }



}
