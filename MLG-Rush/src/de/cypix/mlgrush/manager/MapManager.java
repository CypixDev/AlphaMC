package de.cypix.mlgrush.manager;

import de.cypix.mlgrush.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapManager {

    private static File file = new File(Main.getInstance().getDataFolder(),"Maps.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void saveMapLayout(String map, Location loc1, Location loc2){
        int i = 0;
        int doppelt = 0;
        Location  firstloc = loc1;
        for(Location loc : getTowPoints(loc1, loc2)){
                loc.setX(loc.getX()-firstloc.getX());
                loc.setY(loc.getY()-firstloc.getY());
                loc.setZ(loc.getZ()-firstloc.getZ());
                if(loc.getBlock().getType() != Material.AIR) {
                    saveBlockLocation("Map." + map.toLowerCase() + ".Layout.Block." + i, loc.getBlock());

                }
                i++;
                System.out.println("Es wurden "+i+" Blöcke gespeichert !");
        }

        cfg.set("Map."+map.toLowerCase()+".Layout.Blocks", i);
        try {
            cfg.save(file);
        } catch (IOException e) {

        }
    }
    public static void loadLayout(String map, Player p){
        int blocks = cfg.getInt("Map."+map.toLowerCase()+".Layout.Blocks");
        for(int i = 0;i<blocks;i++){
            double x = cfg.getDouble("Map."+map.toLowerCase()+".Layout.Block."+i+".X");
            double y = cfg.getDouble("Map."+map.toLowerCase()+".Layout.Block."+i+".Y");
            double z = cfg.getDouble("Map."+map.toLowerCase()+".Layout.Block."+i+".Z");
            Location block = p.getLocation();
            block.setX(p.getLocation().getX()+x);
            block.setY(p.getLocation().getY()+y);
            block.setZ(p.getLocation().getZ()+z);
            Material mat = Material.getMaterial(cfg.getString("Map."+map.toLowerCase()+".Layout.Block."+i+".Id"));
            block.getBlock().setType(mat);
            System.out.println("Block placed !"+i);
        }
    }


    private static List<Location> getTowPoints(Location loc1, Location loc2){
        int yTop = 0;
        int yBotten = 0;
        int xTop = 0;
        int xBotton = 0;
        int zTop = 0;
        int zBotton = 0;

        List<Location> locs = new ArrayList<>();

        if(loc1.getBlockY() >= loc2.getBlockY()){
            yTop = loc1.getBlockY();
            yBotten = loc2.getBlockY();
        }else{
            yTop = loc2.getBlockY();
            yBotten = loc1.getBlockY();
        }

        if(loc1.getBlockX() >= loc2.getBlockX()){
            xTop = loc1.getBlockX();
            xBotton = loc2.getBlockX();
        }else{
            xTop = loc2.getBlockX();
            xBotton = loc1.getBlockX();
        }

        if(loc1.getBlockZ() >= loc2.getBlockZ()){
            zTop = loc1.getBlockZ();
            zBotton = loc2.getBlockZ();
        }else{
            zTop = loc2.getBlockZ();
            zBotton = loc1.getBlockZ();
        }

        for(int x = xBotton; x < xTop; x++){
            for(int y = yBotten; y < yTop;y++){
                for(int z = zBotton;z < zTop;z++){
                    locs.add(new Location(loc1.getWorld(), x, y ,z));
                }
            }
        }
        return locs;
    }

    private static void saveSpawnLocation(Location loc, String path){
        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Yaw", loc.getY());
        cfg.set(path+".Pitch", loc.getPitch());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void saveBlockLocation(String path, Block block){
        Location loc = block.getLocation();
        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Id", block.getType().name());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
