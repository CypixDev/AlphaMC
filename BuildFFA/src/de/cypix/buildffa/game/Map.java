package de.cypix.buildffa.game;

import de.cypix.buildffa.api.EntityFreeze;
import de.cypix.buildffa.main.BuildFFA;
import net.minecraft.server.v1_8_R3.AchievementList;
import net.minecraft.server.v1_8_R3.LocaleI18n;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.io.File;

public class Map {

    private File file;
    private FileConfiguration cfg;

    private String map;
    private String mapname;
    private Location spawn;
    private Location villager;
    private Entity villagerEntity;
    private int save;
    private int death;

    public Map(String map){
        this.file = new File(BuildFFA.getInstance().getDataFolder(), "Maps.yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
        this.spawn = getLocation("Map."+map.toLowerCase()+".Spawn");
        this.villager = getLocation("Map."+map.toLowerCase()+".Villager");
        this.death = cfg.getInt("Map."+map.toLowerCase()+".Death");
        this.save = cfg.getInt("Map."+map.toLowerCase()+".Save");
        this.map = map;
        this.mapname = map;

        this.villagerEntity = villager.getWorld().spawnEntity(villager, EntityType.VILLAGER);
        EntityFreeze.freeze(villagerEntity);
        villagerEntity.setCustomName("§aSettings und so !");
        villagerEntity.setCustomNameVisible(true);


        Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.teleport(spawn);
                    p.sendMessage("Map: "+mapname);
                }
            }
        }, 10);
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getCfg() {
        return cfg;
    }

    public int getDeath() {
        return death;
    }

    public int getSave() {
        return save;
    }

    public Location getSpawn() {
        return spawn;
    }

    public Location getVillager() {
        return villager;
    }

    public String getMap() {
        return map;
    }

    public Entity getVillagerEntity() {
        return villagerEntity;
    }

    public String getMapname() {
        return mapname;
    }

    private Location getLocation(String path){
        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");
        System.out.println(new Location(world, x ,y ,z ,yaw, pitch));
        return new Location(world, x, y, z, yaw, pitch);
    }
}
