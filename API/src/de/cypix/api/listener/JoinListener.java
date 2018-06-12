package de.cypix.api.listener;

import de.cypix.api.mysql.Mysql;
import de.cypix.api.stats.FFAStats;
import de.cypix.api.stats.FlashStats;
import de.cypix.api.stats.KnockBackStats;
import de.cypix.api.stats.TTTStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        new KnockBackStats(e.getPlayer()).loadStats();
        new FlashStats(e.getPlayer()).loadStats();
        new FFAStats(e.getPlayer()).loadStats();
        new TTTStats(e.getPlayer()).loadStats();

    }



}
