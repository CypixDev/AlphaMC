package de.cypix.gungame.ingame;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.settings.GenerallyOptions;
import de.cypix.gungame.main.GunGame;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.permission.PermissionPool;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;
import java.util.List;

public class GameListener implements Listener {

    private List<Player> death = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage("");
        final Player p = e.getPlayer();
        p.teleport(GunGame.getInstance().getGameManager().getCurrentmap().getSpawn());

        Bukkit.getScheduler().scheduleSyncDelayedTask(GunGame.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(!AlphaAPI.getInstance().getStatsPlayer(p).getSettings().getGenerallySettings().getOptionlist().containsKey(GenerallyOptions.AUTOStats)){
                    AlphaAPI.getInstance().getStatsPlayer(p).getGunGameStats().sendStats();
                }
                GunGame.getInstance().getLevel().setLevel(p);

                ScoreBoardManager.sendScoreBoard(e.getPlayer());
            }
        }, 10);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
        PermissionPool permissionPool = CloudAPI.getInstance().getPermissionPool();
        AlphaAPI.getInstance().getStatsPlayer(e.getPlayer()).getGunGameStats().saveStatsInMysql();
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        e.getItem().remove();
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(e.getPlayer().getLocation().getBlock().getType()== Material.STATIONARY_WATER){
            if(!death.contains(e.getPlayer())) {
                death.add(e.getPlayer());
                e.getPlayer().setHealth(0);
            }
        }
    }
    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }
    /*@EventHandler
    public void onWeatherhange(WeatherChangeEvent e){
        e.getWorld().setThundering(false);
        e.getWorld().setStorm(false);
    }*/
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setFoodLevel(20);
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(GunGame.getInstance().getGameManager().getCurrentmap().getSpawn());
        death.remove(e.getPlayer());
    }

}
