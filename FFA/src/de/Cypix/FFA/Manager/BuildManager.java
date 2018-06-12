package de.Cypix.FFA.Manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildManager {

    private static List<Player> list = new ArrayList<Player>();


    public static void addPlayer(Player p){
        if(!list.contains(p))list.add(p);
    }

    public static boolean isBuilding(Player p){
        if(list.contains(p))return true;
        return false;
    }

    public static void removeFormList(Player p){
        if(list.contains(p))list.remove(p);
    }



}
