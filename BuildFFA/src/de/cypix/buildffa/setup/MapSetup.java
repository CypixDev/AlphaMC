package de.cypix.buildffa.setup;

import de.cypix.buildffa.main.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MapSetup {

    private File file;
    private FileConfiguration cfg;

    private String map;
    private String name;
    private Location spawn;
    private int deathZone;
    private int saveZone;
    private Location villager;

    public MapSetup(String map){
        this.file = new File(BuildFFA.getInstance().getDataFolder(), "Maps.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        this.map = map;
        List<String> maps = cfg.getStringList("Maps");
        if(!BuildFFA.mapexists(map))maps.add(map);
        cfg.set("Maps", maps);
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setName(String name) {
        this.name = name;
        cfg.set("Map."+map.toLowerCase()+".Name", name);
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setSpawn(Location loc){
        this.spawn = loc;
        saveLocation(loc, "Map."+map.toLowerCase()+".Spawn");
    }

    public void setDeathZone(int y){
        this.deathZone = y;
        cfg.set("Map."+map.toLowerCase()+".Death", y);
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setSaveZone(int y) {
        this.saveZone = y;
        cfg.set("Map."+map.toLowerCase()+".Save", y);
        try { cfg.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setVillager(Location villager) {
        this.villager = villager;
        saveLocation(villager, "Map."+map.toLowerCase()+".Villager");
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
}
