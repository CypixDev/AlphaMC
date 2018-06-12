package de.cypix.ttt.listener;

import de.cypix.ttt.Var;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraitorChatListener implements Listener {

    private static List<Player> list = new ArrayList<>();
    private static HashMap<Player, Integer> map = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if(Main.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState){
            if(((LobbyState) Main.getInstance().getGameStateManager().getCurrentGameState()).getLobbyCountdown().isTraitorVoting()){
                if(!list.contains(p)){
                    try{
                        if(Integer.valueOf(e.getMessage()) >= 100){
                            p.sendMessage("Diese Zahl liegt nicht zwischen 0 und 100 !");
                        }else{
                            list.add(p);
                            map.put(p, Integer.valueOf(e.getMessage()));
                            p.sendMessage("Danke für die Zahl du wirst vor Spiel start benachrichtigt ob du Gewonnen hast !");
                        }
                    }catch(NumberFormatException e1){
                        p.sendMessage("Du hast deine zahl noch nicht in den Chat geschrieben !");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onTraitorChat(AsyncPlayerChatEvent e){
        if(e.getMessage().startsWith("@t")){
            if(TeamManager.getTraitors().contains(e.getPlayer())) {
                e.setCancelled(true);
                String message = e.getMessage();
                message = message.replace("@t", " ");
                for (Player target : TeamManager.getTraitors()) {
                    target.sendMessage(Var.pr+"§7[§cTraitor Chat§7] §4"+e.getPlayer().getName()+" -> "+message);
                }
            }
        }
    }

    public static Player getPlayerWhithNearstValue(int value){
        Integer nearstvalue = null;
        Player keyPlayer = null;
        List<Integer> list = new ArrayList<>();

        for(Map.Entry<Player, Integer> checking : map.entrySet()){
            if(nearstvalue == null){
                nearstvalue = checking.getValue();
            }
            int distance = checking.getValue()-value;
            if(distance < nearstvalue){
                nearstvalue = distance;
                keyPlayer = checking.getKey();
            }
        }
        return keyPlayer;
    }


    public static List<Player> getList() {
        return list;
    }

    public static HashMap<Player, Integer> getMap() {
        return map;
    }
}
