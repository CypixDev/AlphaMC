package de.Cypix.FFA.Manager;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvManager {

    public static void setInv(Player p){

        p.getInventory().clear();
        p.getInventory().addItem(getItem(Material.IRON_SWORD, "§8➤ §b§lSchwert", Enchantment.DAMAGE_ALL, 1));
        p.getInventory().addItem(getItem(Material.BOW, "§8➤ §b§lBogen", Enchantment.ARROW_DAMAGE, 1));
        p.getInventory().addItem(getItem(Material.ARROW, "§8➤ §b§lPfeile", 10));
        p.getInventory().setHelmet(getArmor(Material.LEATHER_HELMET, "§8➤ §b§lHelm"));
        p.getInventory().setChestplate(getArmor(Material.IRON_CHESTPLATE, "§8➤ §b§lHarnisch"));
        p.getInventory().setLeggings(getArmor(Material.IRON_LEGGINGS, "§8➤ §b§lHose"));
        p.getInventory().setBoots(getArmor(Material.IRON_BOOTS, "§8➤ §b§lYezzy`s"));
        ShopManager.setShopItem(p);


    }

    private static ItemStack getItem(Material material, String displayname, Enchantment ench, int level){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        i.setItemMeta(im);
        i.addUnsafeEnchantment(ench, level);

        return i;
    }

    private static ItemStack getItem(Material material, String displayname, int amount){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        if(displayname != null)im.setDisplayName(displayname);
        i.setItemMeta(im);
        i.setAmount(amount);

        return i;
    }

    public static ItemStack getArmor(Material material, String displayname){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayname);
        i.setItemMeta(im);

        return i;
    }


}
