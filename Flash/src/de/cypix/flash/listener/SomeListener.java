package de.cypix.flash.listener;

import de.cypix.flash.main.Main;
import de.cypix.flash.manager.*;
import de.cypix.flash.mysql.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SomeListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(!BuildManager.isBuilding(p)) e.setCancelled(true);
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(!BuildManager.isBuilding(p))e.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(!BuildManager.isBuilding(p)) e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onInv(InventoryClickEvent e){
        if(!BuildManager.isBuilding((Player) e.getWhoClicked())) e.setCancelled(true);
        if(e.getClickedInventory().getTitle().equalsIgnoreCase(SpecManager.invName)){
            if(e.getCurrentItem() != null){
                e.getWhoClicked().teleport(Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName()));
                e.getWhoClicked().sendMessage(Main.pr+"Du wurdest zu "+e.getCurrentItem().getItemMeta().getDisplayName()+" Teleportiert.");
            }
        }
    }
    @EventHandler
    public void onSpecinvopen(PlayerInteractEvent e){
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bSpieler")){
            e.getPlayer().openInventory(SpecManager.getPlayersInv());
        }
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(IngameManager.InstantDeathItemname)){
            Player p = e.getPlayer();
            PlayerStats stats = new PlayerStats(p);
            stats.addDeath();
            p.teleport(CheckPointManager.getLastCheckpoint(p));
        }
    }

}
