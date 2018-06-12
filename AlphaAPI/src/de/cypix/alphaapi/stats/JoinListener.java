package de.cypix.alphaapi.stats;

import de.cypix.alphaapi.main.AlphaAPI;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e){
        AlphaAPI.getInstance().getStatsPlayerList().put(e.getPlayer(), new StatsPlayer(e.getPlayer()));
        CloudPlayer cp = CloudAPI.getInstance().getOnlinePlayer(e.getPlayer().getUniqueId());
        AlphaAPI.getInstance().getStatsPlayer(e.getPlayer()).getCoins().sendCoins();

        if(Bukkit.getServerName().equalsIgnoreCase("Lobby")){
            AlphaAPI.getInstance().getStatsPlayer(e.getPlayer()).getKnockBackStats().sendStats();
        }
        if(Bukkit.getServerName().startsWith("BuildFFA")){
            AlphaAPI.getInstance().getStatsPlayer(e.getPlayer()).getBuildFFAStats().sendStats();
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        final Player p = e.getPlayer();
        Bukkit.getScheduler().scheduleSyncDelayedTask(AlphaAPI.getInstance(), new Runnable() {
            @Override
            public void run() {
                AlphaAPI.getInstance().getStatsPlayerList().remove(p);
            }
        },10);
    }

}
