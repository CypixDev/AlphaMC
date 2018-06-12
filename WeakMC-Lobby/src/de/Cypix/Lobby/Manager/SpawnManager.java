package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnManager {

    private static File file = new File(main.getInstance().getDataFolder(), "Spawn.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static Location getLocation(){
        if(exists()){
            World world = Bukkit.getWorld(cfg.getString("World"));
            double x = cfg.getDouble("X");
            double y = cfg.getDouble("Y");
            double z = cfg.getDouble("Z");
            float yaw = (float) cfg.getDouble("Yaw");
            float pitch = (float) cfg.getDouble("Pitch");
            Location loc = new Location(world, x, y ,z ,yaw ,pitch);

            return loc;

        }
        return null;
    }

    public static boolean exists(){
        if(cfg.getString("World") != null) return true;
        return false;
    }

    public static void setSpawn(Location loc){
        cfg.set("World", loc.getWorld().getName());
        cfg.set("X", loc.getX());
        cfg.set("Y", loc.getY());
        cfg.set("Z", loc.getZ());
        cfg.set("Yaw", loc.getYaw());
        cfg.set("Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
