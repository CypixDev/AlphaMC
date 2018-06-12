package de.Cypix.SGFFA.Listener;

import de.Cypix.SGFFA.MYSQL.PlayerStats;
import de.Cypix.SGFFA.Manager.KillStreakManager;
import de.Cypix.SGFFA.Manager.MapManager;
import de.Cypix.SGFFA.Manager.ScoreBoardManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        MapManager.teleportRandom(e.getPlayer());
        e.setJoinMessage("");
        p.getInventory().clear();
        p.getInventory().setBoots(new ItemStack(Material.CHEST.AIR));
        p.getInventory().setChestplate(new ItemStack(Material.CHEST.AIR));
        p.getInventory().setLeggings(new ItemStack(Material.CHEST.AIR));
        p.getInventory().setHelmet(new ItemStack(Material.CHEST.AIR));
        p.setHealth(20);
        ScoreBoardManager.sendScoreBoard(e.getPlayer());
        PlayerStats.addPlayer(e.getPlayer());
        PlayerStats.loadStats(e.getPlayer());
        KillStreakManager.addPlayer(e.getPlayer());
        p.setGameMode(GameMode.SURVIVAL);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage("");
        PlayerStats.saveStats(e.getPlayer());

    }

}
