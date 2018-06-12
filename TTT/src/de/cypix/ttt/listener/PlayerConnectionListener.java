package de.cypix.ttt.listener;

import de.cypix.ttt.Var;
import de.cypix.ttt.gamestates.GameStateManager;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.*;
import de.cypix.ttt.mysql.PlayerStats;
import de.dytanic.cloudnet.lib.proxylayout.TabList;
import net.minecraft.server.v1_8_R3.ItemEnderEye;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private GameStateManager gameStateManager;

    public PlayerConnectionListener(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        if(gameStateManager.getCurrentGameState() instanceof LobbyState){
            for(Player pp : Bukkit.getOnlinePlayers()){
                TablistManager.setName(p, pp, "§7"+p.getName());
                for(Player ppp : Bukkit.getOnlinePlayers()){
                    TablistManager.setName(ppp, pp, "§7"+ppp.getName());
                }
            }
            PlayerStats.addPlayer(p);
            PlayerStats.loadStats(p);
            ScoreBoardManager.sendScoreBoard(p);
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setFoodLevel(20);
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            VoteManager.setVoteItem(p);
            TicketManager.setTicketItem(p);
            p.teleport(MapManager.getLobbySpawn());
            e.setJoinMessage(Var.pr+p.getName()+" hat das Spiel betreten !");
            Main.getInstance().getPlayers().add(p);
            if(Main.getInstance().getPlayers().size() >= LobbyState.MIN_PLAYERS){
                if(!lobbyState.getLobbyCountdown().isRunning()){
                    if(lobbyState.getLobbyCountdown().isIdling()) lobbyState.getLobbyCountdown().cancelIdle();
                    lobbyState.getLobbyCountdown().run();
                }
            }
        }else{
            for(Player pp : Main.getInstance().getSpecs()){
                pp.sendMessage(Var.pr+p.getName()+"hat das Spiel betreten.");
            }
            SpecManager.setSpectator(p);
            Main.getInstance().getSpecs().add(p);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(gameStateManager.getCurrentGameState() instanceof LobbyState){
            LobbyState lobbyState = (LobbyState) gameStateManager.getCurrentGameState();
            Main.getInstance().getPlayers().remove(p);
            e.setQuitMessage(Var.pr+p.getName()+" hat dass Spiel verlassen !");
            if(Main.getInstance().getPlayers().size() < LobbyState.MIN_PLAYERS){
                //countdown stoppen !
                if(lobbyState.getLobbyCountdown().isRunning()){
                    lobbyState.getLobbyCountdown().cancel();
                    if(!lobbyState.getLobbyCountdown().isIdling()) lobbyState.getLobbyCountdown().idle();
                }
            }
        }
        if(Main.getInstance().getPlayers().contains(p)){
            if(Main.getInstance().isHittingAllowed()){
                TeamManager.removeFromTeam(p);
                e.setQuitMessage(Var.pr+p.getName()+" hat das Spiel verlassen !");
                TeamManager.Check();
                Main.getInstance().getPlayers().remove(p);
            }
        }
        e.setQuitMessage("");
        PlayerStats.saveStats(p);

    }


}
