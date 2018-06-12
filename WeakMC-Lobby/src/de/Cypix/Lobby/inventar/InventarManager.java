package de.Cypix.Lobby.inventar;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class InventarManager {

    public static String InventarInv_Name = "§8➤ §bInventar", BootsInv_Name = "§8➤ §3Boots";

    //boots:
    //Feuer, Angry, water, redstone, cloud. tnt, love, happy, speed, sound, vanish, jetpack, ender

    public static String Heads_Name = "§8➤ §bHüte", Pets_Name = "§8➤ §bPets", Gadgets_Name = "§8➤ §bGadgets", Boots_Name = "§8➤ §bBoots";

    public static void openInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 6*9, InventarInv_Name);

        inv.setItem(46, Item(Material.IRON_HELMET, Heads_Name, null));
        inv.setItem(48, Item(Material.MOB_SPAWNER, Pets_Name, null));
        inv.setItem(50, Item(Material.FIREBALL, Gadgets_Name, null));
        inv.setItem(52, Item(Material.GOLD_BOOTS, Boots_Name, null));

        if(p.getInventory().getHelmet() != null)inv.setItem(28, Item(Material.BARRIER, "Helm ausziehen ", null));
        if(p.getInventory().getBoots() != null)inv.setItem(34, Item(Material.BARRIER, "Boots ausziehen", null));

        setPlaceHolder(inv);


        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.CHEST_OPEN, 20 ,20);
    }

    private static void setPlaceHolder(Inventory inv){

        inv.setItem(36, SlotHolder(7, 1));
        inv.setItem(37, SlotHolder(7, 1));
        inv.setItem(38, SlotHolder(7, 1));
        inv.setItem(39, SlotHolder(7, 1));
        inv.setItem(40, SlotHolder(7, 1));
        inv.setItem(41, SlotHolder(7, 1));
        inv.setItem(42, SlotHolder(7, 1));
        inv.setItem(43, SlotHolder(7, 1));
        inv.setItem(44, SlotHolder(7, 1));

    }

    public static ItemStack SlotHolder(int shortid, int amount){
        ItemStack i = new ItemStack(Material.getMaterial(160), 1, (short)shortid);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }



    private static ItemStack Item(Material material, String displayname, List<String> lore){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        if(displayname != null)im.setDisplayName(displayname);
        if(lore != null)im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }

    private static ItemStack LetherBoot(Color color, String displayname){
        ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta im = (LeatherArmorMeta) i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        i.setItemMeta(im);
        return i;
    }



}
