package de.cypix.ttt.manager;

import de.cypix.ttt.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MapManager {

    public static String currentmap;

    private static File file = new File(Main.getInstance().getDataFolder(), "Loc.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static Location getLobbySpawn(){
        return getLocation("Lobby");
    }
    public static void setLobbySpawn(Location loc){
        saveLocation(loc, "Lobby");
    }
    public static Location getIngameLocation(String map){
        return getLocation("Map."+map.toLowerCase()+".Loc");
    }
    public static void setInGameLocation(String map, Location loc){
        saveLocation(loc, "Map."+map.toLowerCase()+".Loc");
    }
    public static boolean mapcreated(String map) {
        if (cfg.getString("Map." + map.toLowerCase() + ".Name") != null) {
            return true;
        }
        return false;
    }
    public static void setDisplay(String map, String display){
        cfg.set("Map."+map.toLowerCase()+".Display", display);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getDisplayName(String map){
        return ChatColor.translateAlternateColorCodes('&', cfg.getString("Map."+map.toLowerCase()+".Display"));
    }

    public static String getrealName(String displayname){
        for(String map : MapManager.getMaps()){
            String display = displayname;
            display = display.replace("§", "&");
            if(cfg.getString("Map."+map.toLowerCase()+".Display").equalsIgnoreCase(display)){
                return map;
            }
        }
        return displayname;
    }

    public static ItemStack getItem(String map){
        return cfg.getItemStack("Map."+map.toLowerCase()+".Item");
    }
    public static void setItem(Player p, String map){
        cfg.set("Map."+map.toLowerCase()+".Item", p.getItemInHand());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createMap(String map){
        cfg.set("Map."+map.toLowerCase()+".Name", map);
        List<String> list = getMaps();
        list.add(map.toLowerCase());
        cfg.set("Maps", list);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getMaps(){
        return cfg.getStringList("Maps");
    }
    public static void setRespawnZone(String map, Location loc){
        saveLocation(loc, "Map."+map.toLowerCase()+".Respawn");
    }
    public static int getRespawn(String map){
        return (int) getLocation("Map."+map.toLowerCase()+".Respawn").getY();
    }

    private static void saveLocation(Location loc, String path){

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

    private static Location getLocation(String path){

        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");

        Location loc = new Location(world, x, y ,z ,yaw, pitch);

        return loc;
    }


    public static void TeleportAllPlayersRandom() {
        /*HashMap<Player, Integer> map = new HashMap<>();
        ArrayList<Integer> used = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            Random r = new Random();
            int i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
            if (!used.contains(i)) {
                used.add(i);
                map.put(p, i);
            } else {
                i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                if(!used.contains(i)){
                    used.add(i);
                    map.put(p, i);
                }else{
                    i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                    if(!used.contains(i)){
                        used.add(i);
                        map.put(p, i);
                    }else{
                        i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                        if(!used.contains(i)){
                            used.add(i);
                            map.put(p, i);
                        }else{
                            i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                            if(!used.contains(i)){
                                used.add(i);
                                map.put(p, i);
                            }else{
                                i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                                if(!used.contains(i)){
                                    used.add(i);
                                    map.put(p, i);
                                }else{
                                    i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                                    if(!used.contains(i)){
                                        used.add(i);
                                        map.put(p, i);
                                    }else{
                                        i = r.nextInt(MapManager.getSpawns(MapManager.currentmap));
                                        if(!used.contains(i)){
                                            used.add(i);
                                            map.put(p, i);
                                        }else{
                                            map.put(p, i = r.nextInt(MapManager.getSpawns(MapManager.currentmap)));
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
            MapManager.TeleportPlayer(MapManager.currentmap, p, map.get(p));
        }*/
        int i = 0;
        for(Player p : Bukkit.getOnlinePlayers()){
            p.teleport(getSpawn(MapManager.currentmap, i));
            i++;
        }
    }

    public static void setTesterButton(String map, Location loc){
        saveLocation(loc, "Map."+map.toLowerCase()+".Tester.Loc.Button");
    }
    public static void setTesterSpawn(String map, Location loc){
        saveLocation(loc, "Map."+map.toLowerCase()+".Tester.Loc.Spawn");
    }
    public static Location getTesterSpawnLocation(String map){
        return getLocation("Map."+map.toLowerCase()+".Tester.Loc.Spawn");
    }
    public static Location getTesterButtonLocation(String map){
        return getLocation("Map."+map.toLowerCase()+".Tester.Loc.Button");
    }

    public static Location getSpawn(String currentmap, Integer integer) {
        return getLocation("Map."+currentmap.toLowerCase()+".Spawns."+integer);
    }

    private static void TeleportPlayer(String map,Player p, int spawn){
        p.teleport(getSpawn(map, spawn));
    }

    public static int getSpawns(String map){
        return cfg.getInt("Map."+map.toLowerCase()+".Spawns_Amount");
    }

    public static int addSpawnLocation(String map, Location location) {
        int spawns = getSpawns(map);

        saveLocation(location, "Map."+map.toLowerCase()+".Spawns."+spawns);

        spawns++;
        cfg.set("Map."+map.toLowerCase()+".Spawns_Amount", spawns);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return spawns-1;
    }
}
