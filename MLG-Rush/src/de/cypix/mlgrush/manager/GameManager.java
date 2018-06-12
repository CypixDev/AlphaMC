package de.cypix.mlgrush.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private static HashMap<Player, Player> herrausforderung = new HashMap<>();


    public static void onHit(Player from, Player to){
        for(Map.Entry<Player, Player> checking : herrausforderung.entrySet()){
            if(checking.getKey().equals(from)){
                if(checking.getValue().equals(to)){
                    from.sendMessage("Du hast dem Spieler bereits eine anfrage gesendet !");
                }else{
                    herrausforderung.get(from).sendMessage(from.getName()+"hat die Herrausforderung zurrück gezogen !");
                    herrausforderung.put(from, to);
                }
            }
            if(checking.getValue().equals(to)){
                from.sendMessage("Du hast die anfrage angenommen !");
                to.sendMessage("Die anfrage wurde angenommen !");
            }
        }
            from.sendMessage("Du hast "+to.getName()+" eine anfrage geschickt !");
        to.sendMessage("Du hast von "+from.getName()+" eine Anfrage bekommen !");
        herrausforderung.put(from, to);
    }

}
