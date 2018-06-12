package de.Cypix.Lobby.inventar.Extras;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public enum Boots {

    //Feuer, Angry, water, redstone, cloud. tnt, love, happy, speed, sound, vanish, jetpack, ender

    //ID von 0 - 100//

    CLOUD(Material.LEATHER_BOOTS, Effect.CLOUD, Color.WHITE, "§8➤ §l§fCloud-Boots", 250, 0, 11),
    WATER(Material.GOLD_BOOTS, Effect.WATERDRIP, "§8➤ §3§lWater-Boots", 250, 1, 24),
    FIRE(Material.LEATHER_BOOTS, Effect.LAVA_POP, Color.ORANGE, "§8➤ §6§lFire-Boots", 250, 2, 13),
    LOVE(Material.LEATHER_BOOTS, Effect.HEART, Color.RED, "§8➤ §c§lLove-Boots", 250, 3, 19),
    ENDER(Material.LEATHER_BOOTS, Effect.ENDER_SIGNAL,  Color.BLACK, "§8➤ §5§lEnder-Boots", 250, 4, 12),
    NOTE(Material.CHAINMAIL_BOOTS,Effect.NOTE , "§8➤ §d§lNote-Boots", 250, 5, 20),
    LAVA(Material.LEATHER_BOOTS, Effect.LAVADRIP, Color.YELLOW, "§8➤ §e§lLava-Boots", 250, 6, 15),
    SMOKE(Material.LEATHER_BOOTS, Effect.LARGE_SMOKE, Color.GRAY, "§8➤ §7§lRauch-Boots", 250, 7, 22),
    SNOW(Material.LEATHER_BOOTS, Effect.SNOW_SHOVEL, Color.WHITE, "§8➤ §b§lSnow-Boots", 250, 8, 23),
    ANGRY(Material.LEATHER_BOOTS, Effect.VILLAGER_THUNDERCLOUD, Color.PURPLE, "§8➤ §4§lAngry-Boots", 250, 9, 10),
    HAPPY(Material.LEATHER_BOOTS, Effect.HAPPY_VILLAGER, Color.AQUA, "§8➤ §a§lHappy-Boots", 250, 10, 14),
    RAINBOW(Material.LEATHER_BOOTS, Effect.FIREWORKS_SPARK, Color.LIME, "§8➤ §a§lRainbow-§b§lBoots", 250, 11, 21);


    Material material;
    int prise;
    String name;
    Color color;
    Effect effect;
    int ID;
    int slot;

    Boots(Material material, Effect effect, Color color, String name, int prise, int ID, int slot) {
        this.slot = slot;
        this.material = material;
        this.name = name;
        this.color = color;
        this.prise = prise;
        this.ID = ID;
        this.effect = effect;
    }

    Boots(Material material, Effect effect, String name, int prise, int ID, int slot) {
        this.slot = slot;
        this.name = name;
        this.material = material;
        this.prise = prise;
        this.effect = effect;
    }
    public int getSlot(){
        return  this.slot;
    }
    public Effect getEffect(){
        return this.effect;
    }

    public int getID(){
        return this.ID;
    }
    public static Boots valueof(String itemname){
        for(Boots boot : Boots.values()){
            if(boot.getName().equalsIgnoreCase(itemname)){
                return boot;
            }
        }
        return null;
    }
    public static Boots valueof(int id){
        for(Boots boot : Boots.values()){
            if(boot.getID() == id)return boot;
        }
        return null;
    }
    public static boolean Bootsexists(String itemname){
        for(Boots boot : Boots.values()){
            if(boot.getName().equalsIgnoreCase(itemname)){
                return true;
            }
        }
        return false;
    }

    public String getName(){
        return this.name;
    }
    public Material getMaterial(){
        return this.material;
    }
    public Color getColor(){
        if(getMaterial().equals(Material.LEATHER_BOOTS)){
            return this.color;
        }
        return null;
    }
    public int getPrise(){
        return this.prise;
    }
    public ItemStack getItemStack() {
        if (material.equals(Material.LEATHER_BOOTS)) {
            ItemStack i = new ItemStack(this.material);
            LeatherArmorMeta im = (LeatherArmorMeta) i.getItemMeta();
            im.setDisplayName(this.name);
            im.setColor(this.color);
            i.setItemMeta(im);
            return i;
        } else {
            ItemStack i = new ItemStack(this.material);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(this.name);
            i.setItemMeta(im);
            return i;
        }
    }

}
