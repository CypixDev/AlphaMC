package de.Cypix.FFA.Listener;

import de.Cypix.FFA.Main.main;
import de.Cypix.FFA.Manager.BuildManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.List;

public class ElseListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent e){
        if(BuildManager.isBuilding(e.getPlayer()))return;
        if(e.getBlockPlaced().getType().equals(Material.WEB)){
            final Block  b = e.getBlock();
            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getinstance(), new Runnable() {
                @Override
                public void run() {
                    b.setType(Material.AIR);
                }
            },30L);
        }else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockBreakEvent e){
        if(BuildManager.isBuilding(e.getPlayer()))return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
    }
    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent e){
        if(BuildManager.isBuilding(e.getPlayer()))return;
        e.setCancelled(true);
        e.getItem().remove();
    }
}
