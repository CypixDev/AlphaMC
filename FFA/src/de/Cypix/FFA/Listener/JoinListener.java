package de.Cypix.FFA.Listener;

import de.Cypix.FFA.API.Title;
import de.Cypix.FFA.MYSQL.PlayerStats;
import de.Cypix.FFA.Manager.*;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        //e.setJoinMessage(e.getPlayer().getDisplayName()+" hat den Server betreten !");
        e.setJoinMessage("");
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_BASS, 10, 10);
        e.getPlayer().setHealth(20);
        InvManager.setInv(e.getPlayer());
        ScoreBoardManager.sendScoreBoard(e.getPlayer());
        PlayerStats.addPlayer(e.getPlayer());
        PlayerStats.loadStats(e.getPlayer());
        KillStreakManager.addPlayer(e.getPlayer());
        e.getPlayer().teleport(MapManager.getLocation(MapManager.currentMap));
        ShopManager.setShopItem(e.getPlayer());

        Title.sendTitle(e.getPlayer(), "§8✦ §b§lFFA §8✦", "§8➥ §b"+e.getPlayer().getName(), 20, 40, 20);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
        PlayerStats.saveStats(e.getPlayer());

        //remove from lists
        if(TeamManager.getList().containsKey(e.getPlayer())){
            TeamManager.getList().remove(TeamManager.getTeamMember(e.getPlayer()));
            TeamManager.getList().remove(e.getPlayer());
        }
        if(TeamManager.getInvite().containsKey(e.getPlayer()))TeamManager.getInvite().remove(e.getPlayer());

    }

}
