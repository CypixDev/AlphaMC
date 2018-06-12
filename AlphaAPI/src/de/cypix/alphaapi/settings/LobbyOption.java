package de.cypix.alphaapi.settings;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public enum LobbyOption {

    ScoreBoard("§b§lScoreBoard", Material.BANNER, Arrays.asList("Hier kannst du das", "ScoreBoard ausschalten !"), 0)
    , NaigatorAnimation("§b§lNavigatoar-Animation",Material.REDSTONE_COMPARATOR, Arrays.asList("Hier kannst du die", "Navigator Animation an/aus machen", "die Animation wechselt hin","und wieder mal !"), 1)
    , Effects("Boots-Effect",Material.GOLD_BOOTS,Arrays.asList("Hier kannst du", "Die Effecte von den Boots aus machen !"), 2)
    , Pets("Pets",Material.MOB_SPAWNER,Arrays.asList("Hier kannst du", "an/aus machen dass du die Pets siehst"), 3)
    , TeleportAnimation("Teleport-Animation",Material.ENDER_PEARL,Arrays.asList("Hier kannst du", "die Teleport Animation", "an/aus -schalten"), 4);


    private String displayName;
    private Material mat;
    private List<String> lore;
    private int id;

    LobbyOption(String s, Material mat, List<String> lore, int i) {
        this.displayName = s;
        this.mat = mat;
        this.lore = lore;
        this.id = i;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getId() {
        return id;
    }
    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(displayName);
        i.setItemMeta(im);
        return i;
    }

    public static LobbyOption valueOf(int i){
        for(LobbyOption lo : LobbyOption.values()){
            if(lo.getId() == i) return lo;
        }
        System.err.println("Ein Fehler ist beim valueOf von den LobbyOptions augetreten !("+i+")");
        return null;
    }
    public static LobbyOption valueOfDisplayName(String displayname){
        for(LobbyOption lo : LobbyOption.values()){
            if(lo.getDisplayName().equalsIgnoreCase(displayname)) return lo;
        }
        System.err.println("Ein Fehler ist beim valueOf von den LobbyOptions augetreten !("+displayname+")");
        return null;
    }
}
