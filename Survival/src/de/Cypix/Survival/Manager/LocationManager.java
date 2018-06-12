package de.Cypix.Survival.Manager;

import de.Cypix.Survival.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private static File file = new File(main.getInstance().getDataFolder()+"/Locations.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void saveLocation(String name, Player p){
        cfg.set("Loc."+name+".World", p.getWorld().getName());
        cfg.set("Loc."+name+".X", p.getLocation().getX());
        cfg.set("Loc."+name+".Y", p.getLocation().getY());
        cfg.set("Loc."+name+".Z", p.getLocation().getZ());
        cfg.set("Loc."+name+".Yaw", p.getLocation().getYaw());
        cfg.set("Loc."+name+".Pitch", p.getLocation().getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Location getLocation(String name){
        if(cfg.getStringList("Loc."+name+".World") != null){

            World world = Bukkit.getWorld(cfg.getString("Loc."+name+".World"));
            double x = cfg.getDouble("Loc."+name+".X");
            double y = cfg.getDouble("Loc."+name+".Y");
            double z = cfg.getDouble("Loc."+name+".Z");
            float yaw = (float) cfg.getDouble("Loc."+name+".Yaw");
            float pitch = (float) cfg.getDouble("Loc."+name+".Pitch");

            Location loc = new Location(world, x, y, z, yaw, pitch);

            return loc;
        }
        return null;
    }

}
