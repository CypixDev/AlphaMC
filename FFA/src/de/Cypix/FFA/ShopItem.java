package de.Cypix.FFA;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public enum ShopItem {

    BOOST(Material.SLIME_BALL, "§b§lBoost", 50), GRANATE(Material.POTION, "§b§lGranate", 50), KOBWEB(Material.WEB, "§b§lCobWeb", 50); //ONEHITBOW(Material.BOW, "One-Hit-Bow", 50);

    private String name;
    private Material mat;
    private int price;

    ShopItem(Material mat, String name, int price) {
        this.name = name;
        this.mat = mat;
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }

    public String getName() {
        return this.name;
    }
    public Material getMateri(){
        return this.mat;
    }

    public static ShopItem valueof(String itemname){
        for(ShopItem item : ShopItem.values()){
            if(item.getName().equalsIgnoreCase(itemname)){
                return item;
            }
        }
        return null;
    }
    public List<String> getLore(){
        if(name == ShopItem.BOOST.getName()) {
            ShopItem item = ShopItem.BOOST;
            List<String> list = new ArrayList<>();
            list.add("§8➤ §7Preis §8× "+item.getPrice());
            list.add("§8➤ §7Mit dem §aBoost §7wirst du in die §bLüfte §7geschleudert!");
            return list;
        }
        if(name == ShopItem.GRANATE.getName()) {
            ShopItem item = ShopItem.GRANATE;
            List<String> list = new ArrayList<>();
            list.add("§8➤ §7Preis §8× "+item.getPrice());
            list.add("§8➤ §7Mit der §bGranate §7kannst du §bSpieler §7in die Luft sprengen!");
            return list;
        }
        if(name == ShopItem.KOBWEB.getName()) {
            ShopItem item = ShopItem.KOBWEB;
            List<String> list = new ArrayList<>();
            list.add("§8➤ §7Preis §8× "+item.getPrice());
            list.add("§8➤ §7Mit dem §bCobweb §7kannst du deine §bGegener §7verlangsamen!");
            return list;
        }
        /*if(name == ShopItem.ONEHITBOW.getName()) {
            ShopItem item = ShopItem.ONEHITBOW;
            List<String> list = new ArrayList<>();
            list.add("Preis: "+item.getPrice());
            list.add("Mit diesem item kannst du Spieler OneHitten !");
            return list;
        }*/
        return null;
    }
    public ItemStack getItemStack(){
        if(getName() == ShopItem.GRANATE.getName()){
            ItemStack i = new ItemStack(Material.getMaterial(373), (byte)1, (short) 16428);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(getName());
            im.setLore(getLore());
            i.setItemMeta(im);
            return i;
        }
        ItemStack i = new ItemStack(this.mat);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(this.name);
        im.setLore(getLore());
        i.setItemMeta(im);
        return i;
    }
    public ItemStack getItem(){
        if(getName() == ShopItem.GRANATE.getName()){
            ItemStack i = new ItemStack(Material.getMaterial(373), (byte)1, (short) 16428);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(getName());
            im.setLore(getLore());
            i.setItemMeta(im);
            return i;
        }

        ItemStack i = new ItemStack(this.mat);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(this.name);
        i.setItemMeta(im);
        return i;
    }

}
