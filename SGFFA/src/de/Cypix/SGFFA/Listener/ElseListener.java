package de.Cypix.SGFFA.Listener;

import de.Cypix.SGFFA.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ElseListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.getItemDrop().remove();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(true);
    }

}
