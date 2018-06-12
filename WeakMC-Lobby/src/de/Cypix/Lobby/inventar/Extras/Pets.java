package de.Cypix.Lobby.inventar.Extras;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public enum Pets {

    //ID von 101 - 200//

    PIG("§8➤ §bPig", "MHF_Pig", 750, EntityType.PIG, 101, 14),
    COW("§8➤ §bKuh", "MHF_Cow", 750, EntityType.COW, 102, 11),
    CHICKEN("§8➤ §bChicken", "MHF_Chicken", 750, EntityType.CHICKEN, 103, 10),
    OCELOT("§8➤ §bOcelot", "MHF_Ocelot", 750, EntityType.OCELOT, 105, 13),
    SHEEP("§8➤ §bSchaf", "MHF_Sheep", 750, EntityType.SHEEP, 106, 15),
    VILLAGER("§8➤ §bVillager", "MHF_Villager", 750, EntityType.VILLAGER, 107, 19),
    //SLIME("§8➤ §bSlime", "MHF_Slime", 750, EntityType.SLIME, 108, 19),
    WOLF("§8➤ §bWolf", "MHF_Wolf", 750, EntityType.WOLF, 104, 12);//VIP
    //MAGMACUBE("§8➤ §bMagma Cube", "MHF_LavaSlime", 750, EntityType.MAGMA_CUBE, 109, 12); //VIP

    private String name;
    private String playername;
    private int prise;
    private EntityType type;
    private int ID;
    private int slot;


    Pets(String name, String playername, int prise, EntityType type, int ID, int slot) {
        this.slot = slot;
        this.name = name;
        this.playername = playername;
        this.prise = prise;
        this.type = type;
        this.ID = ID;
    }


    public int getSlot(){
        return  this.slot;
    }

    public String getName(){
        return this.name;
    }
    public String getPlayername(){
        return this.playername;
    }
    public int getPrise(){
        return  this.prise;
    }
    public int getID(){
        return this.ID;
    }
    public EntityType getType(){
        return  this.type;
    }
    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(Material.getMaterial(397), (byte)1, (short)3);
        SkullMeta im = (SkullMeta)i.getItemMeta();
        im.setOwner(this.playername);
        im.setDisplayName(this.name);
        i.setItemMeta(im);
        return i;
    }

    public static boolean petexists(String itemname){
        for(Pets pet : Pets.values()){
            if(pet.getName().equalsIgnoreCase(itemname))return true;
        }
        return false;
    }

    public static Pets valueof(String itemname){
        for(Pets pet : Pets.values()){
            if(pet.getName().equalsIgnoreCase(itemname))return pet;
        }
        return null;
    }
    public static Pets valueof(Entity en){
        for(Pets pet : Pets.values()){
            if(pet.getType().equals(en)){
                return pet;
            }
        }
        return null;
    }
    public static Pets valueof(int id){
        for(Pets pet : Pets.values()){
            if(pet.getID() == id){
                return pet;
            }
        }
        return null;
    }

}
