package de.cypix.ttt.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildManager {

    private static ArrayList<Player> list = new ArrayList<>();

    public static void setBuilding(Player p){
        remove(p);
        list.add(p);
    }
    public static void remove(Player p){
        if(list.contains(p)) list.remove(p);
    }
    public static boolean isBuilding(Player p){
        if(list.contains(p)) return true;
        return false;
    }

}
