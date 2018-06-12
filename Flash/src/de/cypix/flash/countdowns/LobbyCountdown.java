package de.cypix.flash.countdowns;

import de.cypix.flash.gamestates.GameState;
import de.cypix.flash.gamestates.LobbyState;
import de.cypix.flash.main.Main;
import de.cypix.flash.manager.CheckPointManager;
import de.cypix.flash.manager.IngameManager;
import de.cypix.flash.manager.MapManager;
import de.cypix.flash.manager.VoteManager;
import de.cypix.flash.mysql.PlayerStats;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.server.ServerState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LobbyCountdown extends Countdown {

    private int idelID;
    private final int IDLE_SECONDS = 60,
    RESET_SECONDS = 60;
    private boolean isRunning = false, isIdeling = false;
    private int seconds = RESET_SECONDS;

    @Override
    public void run() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.setLevel(seconds);
                }
                switch (seconds){
                    case 60: case 30: case 15: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel startet in §b"+seconds+" §7Sekunden");
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10 ,10);
                        }
                        break;
                    case 10:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel startet in §b"+seconds+" §7Sekunden");
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10 ,10);
                            p.closeInventory();
                            p.getInventory().clear();
                        }
                        Bukkit.broadcastMessage(Main.pr+"Voting wurde beendet !");
                        MapManager.currentmap = VoteManager.getVotedMap();
                        CloudServer.getInstance().setMotdAndUpdate(MapManager.currentmap);

                        Bukkit.broadcastMessage(Main.pr+"Voted Map: "+MapManager.currentmap);


                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel starte in §beiner §7Sekunde.");
                        break;
                    case 0:
                        CheckPointManager.setAllCheckpoint();
                        new IngameCountdown().run();
                        Main.getInstace().getGameStatemanager().setGameState(GameState.INGAME_STATE);
                        cancel();
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.teleport(MapManager.getIngameLocation(MapManager.currentmap));
                            PlayerStats stats = new PlayerStats(p);
                            stats.addGame();
                            IngameManager.setInv(p);
                        }
                        CloudServer.getInstance().setServerStateAndUpdate(ServerState.INGAME);
                        CloudServer.getInstance().setMaxPlayers(100);
                        break;

                        default:
                            break;
                }

                seconds--;
            }
        },0, 20);
    }

    @Override
    public void cancel() {
        isRunning = false;
        Bukkit.getScheduler().cancelTask(taskID);
        seconds = RESET_SECONDS;
    }
    public void idel(){

        isIdeling = true;
        idelID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() {
            @Override
            public void run() {
                int missingplayers = LobbyState.MIN_PLAYERS - Bukkit.getOnlinePlayers().size();
                if(missingplayers == 1){
                    Bukkit.broadcastMessage(Main.pr+"Es fehlt noch ein Spieler bis das Spiel startet");
                }else{
                    Bukkit.broadcastMessage(Main.pr+"Es fehlen noch "+missingplayers+" Spieler bis dass Spiel startet");
                }
            }
        }, 0, IDLE_SECONDS*20);
    }

    public void cancelIdle(){
        isIdeling = false;
        Bukkit.getScheduler().cancelTask(idelID);
    }
    public boolean isIdeling() {
        return isIdeling;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
