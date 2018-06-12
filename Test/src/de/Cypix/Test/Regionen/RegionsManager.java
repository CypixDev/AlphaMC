package de.Cypix.Test.Regionen;

import de.Cypix.Test.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegionsManager {

    private static File file = new File(main.getinstance().getDataFolder(), "Regions.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static boolean createRegion(String name){
        if(cfg.getString("Region."+name.toLowerCase()+".Name") == null){
            cfg.set("Region."+name.toLowerCase()+".Name", name);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void saveRegionLocation(String name, int number, Location loc){
        cfg.set("Region."+name.toLowerCase()+".Loc."+number+".World", loc.getWorld().getName());
        cfg.set("Region."+name.toLowerCase()+".Loc."+number+".X", loc.getX());
        cfg.set("Region."+name.toLowerCase()+".Loc."+number+".Y", loc.getY());
        cfg.set("Region."+name.toLowerCase()+".Loc."+number+".Z", loc.getZ());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<RegionType> getRegionTypes(String name){
        if(regionExists(name)) {
            List<String> list = cfg.getStringList("Region." + name.toLowerCase() + ".Options");
            List<RegionType> regions = new ArrayList<RegionType>();
            for (int i = 0; i < list.size(); i++) {
                regions.add(RegionType.valueof(list.get(i)));
            }
            return regions;
        }
        return null;
    }
    public static void addOption(String name, RegionType regionoption){
        List<String> list = cfg.getStringList("Region."+name.toLowerCase()+".Options");
        list.add(regionoption.getName());
        cfg.set("Region."+name.toLowerCase()+".Options", list);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInRegion(Player p, String location){

        Location loc = p.getLocation();
        Location locA = getLocation(location, 0);
        Location locB = getLocation(location, 1);


        Double maxX = (locA.getX() > locB.getX() ? locA.getX() : locB.getX());
        Double minX = (locA.getX() < locB.getX() ? locA.getX() : locB.getX());

        Double maxY = (locA.getY() > locB.getY() ? locA.getY() : locB.getY());
        Double minY = (locA.getY() < locB.getY() ? locA.getY() : locB.getY());

        Double maxZ = (locA.getZ() > locB.getZ() ? locA.getZ() : locB.getZ());
        Double minZ = (locA.getZ() < locB.getZ() ? locA.getZ() : locB.getZ());

        if(loc.getX() <= maxX && loc.getX() >= minX){
            if(loc.getY() <= maxY && loc.getY() >= minY){
                if(loc.getZ() <= maxZ && loc.getZ() >= minZ){
                    return true;
                }
            }
        }

        return false;

    }

    public static Location getLocation(String name, int number){
        if(regionExists(name)) {
            World world = Bukkit.getWorld(cfg.getString("Region."+name.toLowerCase()+".Loc."+number+".World"));
            double x = cfg.getDouble("Region."+name.toLowerCase()+".Loc."+number+".X");
            double y = cfg.getDouble("Region."+name.toLowerCase()+".Loc."+number+".Y");
            double z = cfg.getDouble("Region."+name.toLowerCase()+".Loc."+number+".Z");
            Location loc = new Location(world, x, y, z);
            return loc;
        }
        return null;
    }

    public static boolean regionExists(String name){
        if(cfg.getString("Region."+name.toLowerCase()+".Name") != null)return true;
        return false;
    }
    public static boolean isReady(String name){
        if(cfg.getInt("Region.+"+name.toLowerCase()+".Loc.1.X") != 0){
            if(cfg.getInt("Region.+"+name.toLowerCase()+".Loc.2.X") != 0){
                return true;
            }
        }
        return false;
    }
}
