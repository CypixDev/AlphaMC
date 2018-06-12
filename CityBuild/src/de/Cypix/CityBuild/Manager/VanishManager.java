package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.File.Var;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {

    private static List<Player> list = new ArrayList<Player>();

    public static boolean isvisible(Player p){
        if(!list.contains(p))return true;
        return false;
    }
    public static void setinvisible(Player p){
        for(Player pp : Bukkit.getOnlinePlayers()){
            if(!pp.hasPermission("citybuild.v.bypass")){
                pp.hidePlayer(p);
            }
        }
        Bukkit.broadcastMessage(Var.pr+p.getName()+" hat §bCityBuild §7verlassen.");
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999999, 9999999));
        list.add(p);
    }

    public static void setvisible(Player p){
        for(Player pp : Bukkit.getOnlinePlayers()){
            pp.showPlayer(p);
        }
        Bukkit.broadcastMessage(Var.pr+p.getName()+" hat §bCityBuild §7betreten.");
        list.remove(p);
        p.removePotionEffect(PotionEffectType.INVISIBILITY);
    }
    public static void setallvisible(){
        for(Player p : Bukkit.getOnlinePlayers()){
            for(Player pp : Bukkit.getOnlinePlayers()){
                p.showPlayer(pp);
                pp.showPlayer(p);
            }
        }
    }
    public static List<Player> getInvisebels(){
        return list;
    }

}
