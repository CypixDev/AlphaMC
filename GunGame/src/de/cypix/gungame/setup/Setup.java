package de.cypix.gungame.setup;

import de.cypix.gungame.main.GunGame;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Setup {

    private static File file = new File(GunGame.getInstance().getDataFolder(), "Maps.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static List<String> getMaps(){
        return cfg.getStringList("Maps");
    }

    public static void addMapIfNotExists(String map){
        if(!getMaps().contains(map.toLowerCase())){
            List<String> list = getMaps();
            list.add(map.toLowerCase());
            cfg.set("Maps", list);

            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void setMapSpawn(String map, Location loc){
        saveLoc("Map."+map.toLowerCase()+".Spawn", loc);
    }
    public static void setMapName(String map, String name){
        cfg.set("Map."+map.toLowerCase()+".Name", name);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveLoc(String path, Location loc){
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

    public static void setItem(String map, ItemStack item) {
        cfg.set("Map."+map.toLowerCase()+".Item", item);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
