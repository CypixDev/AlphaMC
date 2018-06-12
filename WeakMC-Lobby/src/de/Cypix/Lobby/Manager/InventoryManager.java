package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.Files.Var;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class InventoryManager {

    public static Inventory getDefaultInv(Player p){
        Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER, "Inv");
        inv.setItem(4, Item(Material.DIAMOND, Var.Navigator, null));
        inv.setItem(6, Item(Material.COMMAND, Var.Settings, null));
        inv.setItem(7, Item(Material.CHEST, Var.Inventar, null));
        inv.setItem(8, Item(Material.BARRIER, Var.Barrier, null));

        return inv;
    }

    private static ItemStack Item(Material material, String name, List<String> lore){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        if(lore != null) im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
}
