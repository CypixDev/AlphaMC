package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private static File file = new File(main.getInstance().getDataFolder(), "Location.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static void saveLocation(Location loc, String path){
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

    public static Location getLocation(String path){
        if(cfg.get(path+".World") != null) {
            World world = Bukkit.getWorld(cfg.getString(path + ".World"));
            double x = cfg.getDouble(path + ".X");
            double y = cfg.getDouble(path + ".Y");
            double z = cfg.getDouble(path + ".Z");
            float yaw = (float) cfg.getDouble(path + ".Yaw");
            float pitch = (float) cfg.getDouble(path + ".Pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);
            return loc;
        }else{
            Bukkit.broadcastMessage("§cBitte setzte den Spawn für §4"+path);
            return null;
        }
    }

}
