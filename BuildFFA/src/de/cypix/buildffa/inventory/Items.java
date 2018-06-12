package de.cypix.buildffa.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Items {

    Item0("Schwert", Material.GOLD_SWORD, 1, Enchantment.DAMAGE_ALL, 0),Item1("STICKK", Material.STICK, 1, Enchantment.KNOCKBACK, 1),
    Item2("PICKKKE", Material.DIAMOND_PICKAXE, 1, null, 2),Item3("Blöckchen", Material.SANDSTONE, 64, null, 3),
    Item4("BLOCKKS", Material.RED_SANDSTONE, 64, null, 4),Item5("BLOCKKKEEN", Material.SANDSTONE, 64, null, 5),
    Item6("BLOCCCKKKE", Material.SANDSTONE, 64, null, 6),Item7("Event-Item", Material.FIREBALL, 1, null, 7),
    Item8("Rettunggg", Material.BLAZE_ROD, 1, null, 8);

    private String displayName;
    private Material material;
    private int amount;
    private Enchantment enchantment;
    private int id;


    Items(String displayName, Material material, int amount, Enchantment enchantment, int id) {
        this.displayName = displayName;
        this.material = material;
        this.amount = amount;
        this.enchantment = enchantment;
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getAmount() {
        return amount;
    }

    public Material getMaterial() {
        return material;
    }
    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayName);
        i.setItemMeta(im);
        i.setAmount(amount);
        if(enchantment != null)i.addUnsafeEnchantment(enchantment, 1);
        return i;
    }
    public static Items valueOfId(int i){
        for(Items item : Items.values()){
            if(item.getId() == i) return item;
        }
        return null;
    }
    public static Items valueOfDisplayName(String displayName){
        for(Items items : Items.values()){
            if(items.getDisplayName().equalsIgnoreCase(displayName))return items;
        }
        return null;
    }
}
