package de.Cypix.Survival.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;

public class PlayTimeChangeEvent extends Event {

    public HandlerList list = new HandlerList();

    @Override
    public HandlerList getHandlers() {



        return list;
    }

    public List<Player> getPlayers(){
        List<Player> players = new ArrayList<Player>();
        for(Player p : Bukkit.getOnlinePlayers()){
            players.add(p);
        }
        return players;
    }
}
