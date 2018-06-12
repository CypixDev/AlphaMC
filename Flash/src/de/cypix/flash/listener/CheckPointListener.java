package de.cypix.flash.listener;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.cypix.flash.gamestates.GameState;
import de.cypix.flash.gamestates.GameStateManager;
import de.cypix.flash.main.Main;
import de.cypix.flash.manager.CheckPointManager;
import de.cypix.flash.mysql.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CheckPointListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(Main.getInstace().getPlayers().contains(e.getPlayer())) {
            if (e.getPlayer().getLocation().getBlock().getType().equals(Material.STONE_PLATE)) {
                if(!CheckPointManager.isthislastCheckpoint(e.getPlayer(), e.getPlayer().getLocation().getBlock().getLocation())){
                    CheckPointManager.setnewCheckPoint(e.getPlayer(), e.getPlayer().getLocation().getBlock().getLocation());
                    e.getPlayer().sendMessage(Main.pr+"Der CheckPoint wurde gesetzt.");
                    Coins.addCoins(e.getPlayer(), 10);
                    e.getPlayer().sendMessage(Main.pr+"Du hast 10 Coins bekommen !");
                    PlayerStats stats = new PlayerStats(e.getPlayer());
                    stats.addCheckPoint();
                }
            }
        }
    }

    int seconds = 15;

    @EventHandler
    public void onMoveWin(PlayerMoveEvent e){
        if(Main.getInstace().getPlayers().contains(e.getPlayer())) {
            if(e.getPlayer().getLocation().getBlock().getType().equals(Material.GOLD_PLATE)){
                new PlayerStats(e.getPlayer()).addWin();
                new GameStateManager().setGameState(GameState.ENDING_STATE);
                Bukkit.broadcastMessage(Main.pr+e.getPlayer().getName()+" hat das Spiel Gewonnen !");

                Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() {
                    @Override
                    public void run() {
                        switch (seconds){

                            case 15: case 10: case 5: case 4: case 3: case 2:
                                Bukkit.broadcastMessage(Main.pr+"Der Server startet in "+seconds+" Sekunde neu !");
                                break;
                            case 1:
                                Bukkit.broadcastMessage(Main.pr+"Der Server startet in einer Sekunde neu !");
                                break;
                            case 0:
                                Bukkit.shutdown();
                                Bukkit.broadcastMessage(Main.pr+"Der Server wird Gestoppt.....");
                                Bukkit.shutdown();
                                break;
                            default:
                                break;
                        }

                        seconds--;
                    }
                },0, 20);
                //win !
            }
        }
    }

}
