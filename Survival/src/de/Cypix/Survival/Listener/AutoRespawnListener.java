package de.Cypix.Survival.Listener;

import de.Cypix.Survival.Main.main;
import de.Cypix.Survival.Manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class AutoRespawnListener implements Listener {

    @EventHandler
    public void onAutoRespawn(PlayerDeathEvent e){
        Player p = e.getEntity();
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
               p.spigot().respawn();
            }
        },2);
    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(LocationManager.getLocation("Spawn"));
    }



}
