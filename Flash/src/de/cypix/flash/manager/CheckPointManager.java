package de.cypix.flash.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CheckPointManager {

    private static HashMap<Player, Location> map = new HashMap<>();

    public static boolean isthislastCheckpoint(Player p, Location loc){
        if(map.get(p).getBlock().getLocation() == loc.getBlock().getLocation()){
            return true;
        }
        return false;
    }

    public static void setnewCheckPoint(Player p, Location loc){
        map.put(p, loc.getBlock().getLocation());
    }

    public static void setAllCheckpoint(){
        for(Player p : Bukkit.getOnlinePlayers()){
            map.put(p, MapManager.getIngameLocation(MapManager.currentmap).getBlock().getLocation());
        }
    }
    public static Location getLastCheckpoint(Player p){
        return map.get(p);
    }
}
