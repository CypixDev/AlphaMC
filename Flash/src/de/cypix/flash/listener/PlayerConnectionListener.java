package de.cypix.flash.listener;

import de.cypix.flash.gamestates.GameStateManager;
import de.cypix.flash.gamestates.LobbyState;
import de.cypix.flash.main.Main;
import de.cypix.flash.manager.MapManager;
import de.cypix.flash.manager.ScoreBoardManager;
import de.cypix.flash.manager.SpecManager;
import de.cypix.flash.manager.VoteManager;
import de.cypix.flash.mysql.PlayerStats;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerConnectionListener implements Listener{

    private GameStateManager gamestatemanager;

    public PlayerConnectionListener(GameStateManager gameStateManager){this.gamestatemanager = gameStateManager;}

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.setAllowFlight(false);
        ScoreBoardManager.sendScoreBoard(p);
        p.setGameMode(GameMode.SURVIVAL);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 10));
        PlayerStats.addPlayer(p);
        PlayerStats.loadStats(p);
        if(gamestatemanager.getCurrentGameState() instanceof LobbyState){
            VoteManager.setVoteItem(p);
            p.teleport(MapManager.getLobbySpawn());
            LobbyState lobbyState = (LobbyState) gamestatemanager.getCurrentGameState();
            Main.getInstace().getPlayers().add(p);
            e.setJoinMessage(Main.pr+p.getName()+" Joined the Game.");
            if(Main.getInstace().getPlayers().size() >= LobbyState.MIN_PLAYERS){
                if(!lobbyState.getLobbyCountdown().isRunning()){
                    if(lobbyState.getLobbyCountdown().isIdeling()) lobbyState.getLobbyCountdown().cancelIdle();
                    lobbyState.getLobbyCountdown().run();
                }
            }
        }else{
            e.setJoinMessage("");
            e.getPlayer().setAllowFlight(true);
            SpecManager.getSpec().add(p);
            p.teleport(MapManager.getIngameLocation(MapManager.currentmap));
            for(Player pp : Main.getInstace().getPlayers()){
                pp.hidePlayer(p);
            }
            ItemStack i = new ItemStack(Material.COMPASS);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("§bSpieler");
            i.setItemMeta(im);
            p.getInventory().setItem(4, i);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        PlayerStats.saveStats(e.getPlayer());
        if(gamestatemanager.getCurrentGameState() instanceof LobbyState){
            LobbyState lobbystate = (LobbyState)gamestatemanager.getCurrentGameState();
            Main.getInstace().getPlayers().remove(p);
            SpecManager.getSpec().remove(p);
            e.setQuitMessage(Main.pr+p.getName()+" hat dass Spiel verlassen !");
            if(Main.getInstace().getPlayers().size() < LobbyState.MIN_PLAYERS){
                if(lobbystate.getLobbyCountdown().isRunning()){
                    lobbystate.getLobbyCountdown().cancel();
                    if(!lobbystate.getLobbyCountdown().isIdeling()){
                        lobbystate.getLobbyCountdown().idel();
                    }
                }
            }
        }
    }


}
