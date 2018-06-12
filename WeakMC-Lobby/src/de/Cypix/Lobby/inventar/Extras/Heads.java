package de.Cypix.Lobby.inventar.Extras;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public enum Heads {

    //ID von 201-300//

    CAM("§8➤ §bKamera", "FHG_Cam", 250, 201),
    CLOCK("§8➤ §bUhr", "Olaf_C", 250, 202),
    WORLD("§8➤ §bWelt", "cskult", 250, 203),
    SNOWMAN("§8➤ §bSchneeMann", "Snowman_7", 250, 204),
    RADIO("§8➤ §bRadio", "uioz", 250, 205),
    HULK("§8➤ §bHulk", "Incredible_Hulk", 250, 206),
    AQUARIUM("§8➤ §bAquarium", "Fish181", 250, 207),
    IRONMAN("§8➤ §bIronMan", "iron_man", 250, 208),
    YOSHI("§8➤ §bYoshi", "Yoshi", 250, 209),
    LUIGI("§8➤ §bLuigi", "Luigi", 250, 210),
    MARIO("§8➤ §bMario", "Mario", 250, 211),
    NUTELLA("§8➤ §bNutella", "Chipsandip", 250, 212),
    APPLE("§8➤ §bApfel", "MHF_Apple", 250, 213),
    SUSHI("§8➤ §bSushi", "lmaoki", 250, 214),
    HAMBURGER("§8➤ §bHamburger", "simbasbestbud", 250, 215),
    TACO("§8➤ §bTaco", "Crunchy_Taco34", 250, 216),
    POPCORN("§8➤ §bPopcorn", "ZachWarnerHD", 250, 217),
    R2D2("§8➤ §bR2-D2", "r2d2", 250, 218),
    BOBAFETT("§8➤ §bBoba Fett", "bobafeet", 250, 219),
    DARTHVADER("§8➤ §bDarth Vader", "Darth_Vader", 250, 220),
    TNT("§8➤ §bTNT", "MHF_TNT2", 250, 221),
    MISSINGTEXTURE("§8➤ §bVermisste Textur", "ddrl46", 250, 222);


    private String name;
    private String playername;
    private int prise;
    private int ID;


    Heads(String name, String playername, int prise, int ID) {
        this.name = name;
        this.playername = playername;
        this.prise = prise;
        this.ID = ID;
    }

    public String getName(){
        return this.name;
    }
    public int getID(){
        return  this.ID;
    }
    public String getPlayername(){
        return this.playername;
    }
    public int getPrise(){
        return this.prise;
    }
    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(Material.getMaterial(397), (byte)1, (short) 3);
        SkullMeta im = (SkullMeta) i.getItemMeta();
        im.setOwner(this.playername);
        im.setDisplayName(this.name);
        i.setItemMeta(im);
        return i;
    }

    public static boolean headsexists(String itemname){
        for(Heads head : Heads.values()){
            if(head.getName().equalsIgnoreCase(itemname))return true;
        }
        return false;
    }

    public static Heads valueof(int ID){
        for(Heads head : Heads.values()){
            if(head.getID() == ID)return head;
        }
        return null;
    }

    public static Heads valueof(String itemname){
        for(Heads head : Heads.values()){
            if(head.getName().equalsIgnoreCase(itemname))return head;
        }
        return null;
    }


}
