package de.cypix.buildffa.game;

import de.cypix.alphaapi.main.AlphaAPI;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class KillStreakManager {

    public static HashMap<Player, Integer> list = new HashMap<>();


    public static void add(Player p){
        if(list.containsKey(p)) {
            list.put(p, list.get(p)+1);
            //beim reset einbauen !
            if(list.get(p) > AlphaAPI.getInstance().getStatsPlayer(p).getBuildFFAStats().getKillStreak()){
                AlphaAPI.getInstance().getStatsPlayer(p).getBuildFFAStats().setKillStreak(list.get(p));
                p.sendMessage("Du hast einen neuen Hight-Score vong deinem KillStreak her !");
            }
        }else list.put(p, 1);
    }
    public static void reset(Player p){
        list.remove(p);
    }

    public static int get(Player p){
        if(list.containsKey(p)) return list.get(p);
        return 0;
    }
}
