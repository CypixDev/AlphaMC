package de.cypix.knockback.setup;

import de.cypix.knockback.main.KnockBack;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SetupManager {

    private File file;
    private FileConfiguration cfg;

    public SetupManager(){
        file = new File(KnockBack.getInstance().getDataFolder(), "Maps.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void createMapIfNotExists(Player p, String map){
        List<String> maps = cfg.getStringList("Maps");
        if(!maps.contains(map.toLowerCase())){
            maps.add(map.toLowerCase());
            cfg.set("Maps", maps);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage("Map erstellt !");
        }
    }

    public void saveSpawn(Player p, String map, Location loc){
        p.sendMessage("Spawn für die Map "+map+" wurde gespeichert !");
        saveLocation(loc, "Map."+map.toLowerCase()+".Spawn");
    }
    public void saveDeathZone(Player p, String map, Location loc){
        cfg.set("Map."+map.toLowerCase()+".Death", loc.getY());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendMessage("Die Death-Zone von der Map "+map+" wurde auf "+loc.getY()+" gesetzt !");
    }
    public void saveSaveZone(Player p, String map, Location loc){
        cfg.set("Map."+map.toLowerCase()+".Save", loc.getY());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendMessage("Die Death-Zone von der Map "+map+" wurde auf "+loc.getY()+" gesetzt !");
    }

    private void saveLocation(Location loc, String path){
        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Yaw", loc.getYaw());
        cfg.set(path+".Pitch", loc.getPitch());
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }
    private Location getLocation(String path){
        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public void saveName(Player p, String map, String mapName){
        cfg.set("Map."+map.toLowerCase()+".Name", mapName);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
