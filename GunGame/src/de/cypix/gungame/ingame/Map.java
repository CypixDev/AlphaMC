package de.cypix.gungame.ingame;

import de.cypix.gungame.main.GunGame;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Map {

    File file;
    FileConfiguration cfg;

    private String mapName;
    private String map;
    private Location spawn;


    public Map(String map) {
        this.file = new File(GunGame.getInstance().getDataFolder(), "Maps.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        this.map = map.toLowerCase();
        this.mapName = cfg.getString("Map."+this.map+".Name");
        this.spawn = getLocation("Map."+this.map+".Spawn");

        for(Player p : Bukkit.getOnlinePlayers()){
        p.sendMessage("§bNeue Map: "+mapName);
        p.teleport(this.spawn);
    }

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

    public Location getSpawn() {
        return spawn;
    }

    public String getMap() {
        return map;
    }

    public String getMapName() {
        return mapName;
    }
}
