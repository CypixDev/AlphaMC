package de.Cypix.Lobby.navigator;

import de.Cypix.Lobby.Main.main;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Item {

      MLGRUSH("Mlg-Rush", "Mlg-Rush",Material.SANDSTONE, Arrays.asList("lol", "lol"), 11)
    , BEDWARS("BedWars", "Mlg-Rush", Material.BED,  Arrays.asList("lol", "lol"), 12)
    , CITYBUILD("CityBuild", "Mlg-Rush", Material.DIAMOND_AXE, Arrays.asList("lol", "lol"), 14)
    , TTT("TTT", "TTT", Material.STICK, Arrays.asList("lol", "lol"), 15)
    , ONELINE("One-Line", "OneLine", Material.SANDSTONE_STAIRS, Arrays.asList("lol", "lol"), 19)
    , FASTBRIDGE("Fastbridge", "Fastbridge", Material.RED_SANDSTONE, Arrays.asList("lol", "lol"), 22)
    , KNOCKBACK("KnockBack", "KnockBack", Material.STICK, Arrays.asList("lol", "lol"), 25)
    , FFA("FFA", "FFA", Material.DIAMOND_SWORD, Arrays.asList("lol", "lol"), 29)
    , BUILDFFA("BuildFFA", "BuildFFA", Material.SANDSTONE, Arrays.asList("lol", "lol"), 33)
    , LOBBY("Spawn", "Lobby", Material.DIAMOND, Arrays.asList("lol", "lol"), 39)
    , GUNGAME("GunGame", "GunGame", Material.WOOD_AXE, Arrays.asList("lol", "lol"), 41)
    , BattleRoyale("BattleRoyale", "BattleRoyale", Material.BOW, Arrays.asList("§cDieser Modi ist dem Spiel Fortnite so", "§6gut es geht nach empfunden"), 49);

    private String displayname;
    private String serverGroup;
    private Material material;
    private List<String> lore;
    private int slot;

    Item(String displayname, String serverGroup, Material material, List<String> lore, int slot) {
        this.lore = lore;
        this.displayname = displayname;
        this.material = material;
        this.serverGroup = serverGroup;
        this.slot = slot;
    }

    public ItemStack getItem(){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(displayname);
        i.setItemMeta(im);
        i.setAmount(0);
        //CloudAPI.getInstance().getOnlineCount(serverGroup)
        return i;
    }

    public List<String> getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDisplayname() {
        return displayname;
    }

    public String getServerGroup() {
        return serverGroup;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setServerGroup(String serverGroup) {
        this.serverGroup = serverGroup;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public static Item valueOfDisplayName(String displayname){
        for(Item item : Item.values()){
            if(item.getDisplayname().equalsIgnoreCase(displayname)){
                return item;
            }
        }
        System.err.println("Das Item für den Navigator gibt es nicht !("+displayname+")");
        return null;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public static Item valueOfSlot(int slot){
        for(Item item : Item.values()){
            if(item.getSlot() == slot){
                return item;
            }
        }
        return null;
    }

    public static List<Item> getAllItems(){
        List<Item> list = new ArrayList<>();
        for(Item item : Item.values()) list.add(item);

        return list;
    }
    public static Item valueOfServerGroup(String serverGroup){
        for(Item item : Item.values()){
            if(item.getServerGroup().equals(serverGroup)){
                return item;
            }
        }
        return null;
    }
}
//mein name ist holz
//leeeeeeesl
//by Janne

