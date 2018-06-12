package de.cypix.alphaapi.settings;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public enum GenerallyOptions {

    AUTOStats("§b§lAuto-Stats", Material.PAPER, Arrays.asList("§bWenn du das", "§aAktiviert §bhast", "§bbekommst du immer", "§bDeine Aktuellen Stats"), 0);

    private String displayName;
    private Material material;
    private List<String> lore;
    private int id;

    GenerallyOptions(String displayName, Material material, List<String> lore, int id) {
        this.displayName = displayName;
        this.material = material;
        this.lore = lore;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static GenerallyOptions valueOf(int id){
        for(GenerallyOptions go : GenerallyOptions.values()){
            if(go.getId() == id) return go;
        }
        System.err.println("Ein Fehler ist beim valueOf von den GenerallyOptions augetreten !("+id+")");
        return null;
    }

    public ItemStack getItemStack(){
        ItemStack i = new ItemStack(material);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(displayName);
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }

    public static GenerallyOptions valueOfDisplayName(String displayname){
        for(GenerallyOptions lo : GenerallyOptions.values()){
            if(lo.getDisplayName().equalsIgnoreCase(displayname)) return lo;
        }
        System.err.println("Ein Fehler ist beim valueOf von den GenerallyOptions augetreten !("+displayname+")");
        return null;
    }
}
