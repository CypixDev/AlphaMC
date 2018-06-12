package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.Manager.BuildManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(!BuildManager.list.contains(e.getPlayer()))e.setCancelled(true);
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(!BuildManager.list.contains(e.getPlayer()))e.setCancelled(true);
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(!BuildManager.list.contains(e.getPlayer()))e.setCancelled(true);
    }
    @EventHandler
    public void onpickup(PlayerPickupItemEvent e) {if(!BuildManager.list.contains(e.getPlayer())) e.setCancelled(true);}
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        try {
            if (!BuildManager.list.contains(e.getPlayer())) e.setCancelled(true);

        }catch(NullPointerException e1){
        }
    }
}
