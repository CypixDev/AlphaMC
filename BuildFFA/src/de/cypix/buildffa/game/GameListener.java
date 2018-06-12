package de.cypix.buildffa.game;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.buildffa.inventory.Items;
import de.cypix.buildffa.main.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;

public class GameListener implements Listener {


    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        final Player p = e.getPlayer();
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20);
        p.setFoodLevel(20);
        if(BuildFFA.getInstance().getGameManager().getCurrentmap() != null) e.getPlayer().teleport(BuildFFA.getInstance().getGameManager().getCurrentmap().getSpawn());

        Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
            @Override
            public void run() {
                ScoreBoardManager.sendScoreBoard(p);
                ArrayList<Integer> list = AlphaAPI.getInstance().getStatsPlayer(p).getBuildFFAInv().getInventory();
                for(int i = 0;i<9;i++){
                    if(!list.isEmpty()){
                        p.getInventory().setItem(i, Items.valueOfId(list.get(i)).getItemStack());
                    }else p.getInventory().setItem(i, Items.valueOfId(i).getItemStack());
                }
            }
        },20);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        AlphaAPI.getInstance().getStatsPlayer(e.getPlayer()).getBuildFFAStats().saveStatsInMysql();
    }



    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if(p.getLocation().getY() < BuildFFA.getInstance().getGameManager().getCurrentmap().getDeath()){
            if(!BuildFFA.getInstance().getGameManager().getDeath().contains(p)){
                BuildFFA.getInstance().getGameManager().addDeath(p);
                p.setHealth(0);
            }
        }
    }

    @EventHandler
    public void FallDamage(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.getDrops().clear();
        Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
            @Override
            public void run() {
                e.getEntity().spigot().respawn();
            }
        },10);
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(BuildFFA.getInstance().getGameManager().getCurrentmap().getSpawn());
        BuildFFA.getInstance().getGameManager().getDeath().remove(e.getPlayer());


        Player p = e.getPlayer();
        ArrayList<Integer> list = AlphaAPI.getInstance().getStatsPlayer(p).getBuildFFAInv().getInventory();
        for(int i = 0;i<9;i++){
            if(!list.isEmpty()){
                p.getInventory().setItem(i, Items.valueOfId(list.get(i)).getItemStack());
            }else p.getInventory().setItem(i, Items.valueOfId(i).getItemStack());
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        if(e.getBlockPlaced().getLocation().getY() > BuildFFA.getInstance().getGameManager().getCurrentmap().getSave()){
            e.setCancelled(true);
            return;
        }
        if(e.getBlockPlaced().getType().equals(Material.SANDSTONE)){
            final Block b = e.getBlock();
            Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
                @Override
                public void run() {
                    b.setType(Material.REDSTONE_BLOCK);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            b.setType(Material.AIR);
                        }
                    }, 20);
                }
            }, 20);
        }
        if(e.getBlockPlaced().getType().equals(Material.RED_SANDSTONE)){
            final Block b = e.getBlock();
            Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
                @Override
                public void run() {
                    b.setType(Material.REDSTONE_BLOCK);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(BuildFFA.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            b.setType(Material.AIR);
                        }
                    }, 40);
                }
            }, 40);
        }
    }

}
