package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.Main.main;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpManager {

    private static File file = new File(main.getInstance().getDataFolder(), "Warps.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static String Inv_Name = "§6§lWarps";

    public static void createWarp(Player p, String name){
        if(!warpexists(name)){
            cfg.set("Warp."+name.toLowerCase()+".Name", name);
            List<String> list = new ArrayList<String>();
            list = cfg.getStringList("Warps");
            list.add(name.toLowerCase());
            cfg.set("Warps", list);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage("Warp erstellt ! jetzt noch dass Item und den Spawn setzten !");
        }else p.sendMessage("Der Warp ist bereits erstellt worden !");
    }

    public static void setWarpSpawn(Player p, String name){
        if(warpexists(name)){
            saveLocation(p.getLocation(), "Warp."+name.toLowerCase()+".Loc");
            p.sendMessage("Spawn gesetzt !");
        }else p.sendMessage("Bitte erst createn !");
    }
    public static void setWarpItem(Player p, String name) {
        if (warpexists(name)) {
            cfg.set("Warp." + name.toLowerCase() + ".Item", p.getItemInHand());
            p.sendMessage("Item gesetzt !");
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else p.sendMessage("erst createn !");
    }
    public static void setDisplayname(String name, String displayname){
        if(warpexists(name)){
            cfg.set("Warp."+name.toLowerCase()+".Display", displayname);

            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDisplayName(String name){
        if(warpexists(name)){
            if(cfg.getString("Warp."+name.toLowerCase()+".Display") != null){
                String display = cfg.getString("Warp."+name.toLowerCase()+".Display");
                display = display.replace("_", " ");
                return ChatColor.translateAlternateColorCodes('&', display);
            }
        }
        return "§cBitte angeben !";
    }

    public static ItemStack getWarpItem(String name){
        if(warpexists(name)){
            if(cfg.getItemStack("Warp."+name.toLowerCase()+".Item") != null);
            ItemStack i = cfg.getItemStack("Warp."+name.toLowerCase()+".Item");
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(getDisplayName(name));
            i.setItemMeta(im);
            return i;
        }
        ItemStack i = new ItemStack(Material.BARRIER);
        return i;
    }

    public static Location getWarpLocation(String name){
        return getLocation("Warp."+name.toLowerCase()+".Loc");
    }

    public static boolean warpexists(String name){
        if(cfg.getString("Warp."+name.toLowerCase()+".Name") != null) return true;
        return false;
    }

    public static List<String> getWarps(){
        return cfg.getStringList("Warps");
    }

    public static void openWarpInv(Player p){
        Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, Inv_Name);

        for(String name : getWarps()){
            inv.addItem(getWarpItem(name));
        }

        p.openInventory(inv);
    }

    public static Location getWarpNameperDisplay(String displayname){
        for(String name : getWarps()){
            if(cfg.getString("Warp."+name.toLowerCase()+".Display").equalsIgnoreCase(displayname)){
                return getLocation("Warp."+name.toLowerCase()+".Loc");
            }
        }
        return null;
    }

    private static void saveLocation(Location loc, String path){

        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Yaw", loc.getYaw());
        cfg.set(path+".Pitch", loc.getPitch());


        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Location getLocation(String path){

        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");

        Location loc = new Location(world, x, y ,z ,yaw, pitch);

        return loc;
    }
}
