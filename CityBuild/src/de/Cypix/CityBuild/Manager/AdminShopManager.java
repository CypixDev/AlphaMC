package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AdminShopManager {

    public static String ShopInvName = "§bAdminShop";
    private static File file = new File(main.getInstance().getDataFolder(), "Signs.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    private static HashMap<Location, Integer> list = new HashMap<>();


    public static Inventory getShopInv(ItemStack item){
        Inventory inv = Bukkit.createInventory(null, 6*9, ShopInvName);

        return inv;
    }


    public static void saveSignLoc(Location loc, List<String> lines){
        saveLocation(loc, "Sign."+getSignsamount()+".Loc");
        cfg.set("Sign."+getSignsamount()+".Lines", lines);
        list.put(loc, getSignsamount());

        cfg.set("amount", getSignsamount()+1);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSigns(){
        list.clear();
        for(int i = 0;i<getSignsamount();i++){
            list.put(getLocation("Sign."+i+".Loc"), i);
            Location loc = getLocation("Sign."+i+".Loc");
            Block b = loc.getBlock();

            if(b.getState() instanceof Sign){
                Sign s = (Sign)b.getState();
                s.setLine(0, getLines(i).get(0));
                s.setLine(1, getLines(i).get(1));
                s.setLine(2, getLines(i).get(2));
                s.setLine(3, getLines(i).get(3));
                s.update();
            }else{
                Bukkit.broadcastMessage("§cBitte keine Admin-Shop schilder entfernen !");
            }

        }
    }

    public static boolean isAdminSign(Location loc){
        if(list.containsKey(loc)){
            return true;
        }
        return false;
    }

    public static Location getLocation(int signid){
        return getLocation("Sign."+signid+".Loc");
    }
    public static List<String> getLines(int signid){
        return cfg.getStringList("Sign."+signid+".Lines");
    }

    public static int getSignsamount(){
        return cfg.getInt("amount");
    }

    public static void setItemtoBuy(int signid, ItemStack i){
        cfg.set("Sign."+signid+".Item", i);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ItemStack getItemtoBuy(int signid){
        return cfg.getItemStack("Sign."+signid+".Item");
    }

    public static int getSignId(Location loc){
        for(Location locc : list.keySet()){
            if(issameLocation(loc, locc)){
                return list.get(locc);
            }
        }
        return -1;
    }

    private static boolean issameLocation(Location loc1, Location loc2){
        if(loc1.getX() == loc2.getX() && loc1.getZ() == loc2.getZ() && loc1.getY() == loc2.getY() && loc1.getWorld().getName() == loc2.getWorld().getName()){
            return true;
        }
        return false;
    }

    public static void setLine(int signid, int line, String linestring){
        List<String> lines = cfg.getStringList("Sign."+signid+".Lines");
        lines.set(line, linestring);
        cfg.set("Sign."+signid+".Lines", lines);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private static org.bukkit.Location getLocation(String path){

        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");

        org.bukkit.Location loc = new org.bukkit.Location(world, x, y ,z ,yaw, pitch);

        return loc;
    }

}