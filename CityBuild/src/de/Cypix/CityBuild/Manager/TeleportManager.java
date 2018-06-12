package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeleportManager {

    private static HashMap<Player, Player> list = new HashMap<Player, Player>();

    public static void teleportPlayer(Player p, Location homeLoc) {
        //delay etc...//
        p.teleport(homeLoc);

    }

    public static boolean deny(Player p){
        if(list.containsKey(p)){
            list.remove(p);
            return true;
        }
        return false;
    }
    public static void sendTphere(Player here, Player comehere){
        if(list.containsKey(comehere)){
            list.remove(comehere);
        }
        list.put(comehere, here);
    }

    public static void sendtpa(Player sender, Player getter){
        if(list.containsKey(getter)){
            list.remove(getter);
        }
        list.put(sender, getter);
    }
    public static boolean accept(Player accepter){
        if(list.containsKey(accepter)){
            list.get(accepter).teleport(accepter);
            list.remove(accepter);
            return true;
        }else{
            return false;
        }
    }
}
