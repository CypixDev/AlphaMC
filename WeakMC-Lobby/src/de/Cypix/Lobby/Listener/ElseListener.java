package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.Main.main;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ElseListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e){

        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

 }
