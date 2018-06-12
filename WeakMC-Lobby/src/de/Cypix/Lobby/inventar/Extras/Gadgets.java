package de.Cypix.Lobby.inventar.Extras;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Gadgets {

    //ID von 301 - 400//

    CLAYBOMB(Material.IRON_SPADE, "ClayBomb", 500, 301, 10);

    private Material material;
    private String name;
    private int prise;
    private int ID;
    private int slot;


    Gadgets(Material material, String name, int prise, int ID, int slot) {
        this.name = name;
        this.material = material;
        this.prise = prise;
        this.ID = ID;
        this.slot = slot;
    }

    public Material getMaterial(){
        return this.material;
    }
    public String getName(){
        return this.name;
    }
    public int getPrise(){
        return  this.prise;
    }
    public int getID(){
        return this.ID;
    }
    public int getSlot(){
        return this.slot;
    }

    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(this.material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(this.name);
        i.setItemMeta(im);
        return i;
    }

    public static boolean gadgetsexists(String itemname){
        for(Gadgets gd : Gadgets.values()){
            if(gd.getName().equalsIgnoreCase(itemname))return true;
        }
        return false;
    }
    public static Gadgets valueof(String itemname){
        for(Gadgets gd : Gadgets.values()){
            if(gd.getName().equalsIgnoreCase(itemname))return gd;
        }
        return null;
    }


}
