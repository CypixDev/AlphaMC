package de.cypix.ttt.countdown;

import de.cypix.ttt.Var;
import de.cypix.ttt.gamestates.GameState;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.listener.TraitorChatListener;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.MapManager;
import de.cypix.ttt.manager.ScoreBoardManager;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.manager.VoteManager;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;

public class LobbyCountdown extends Countdown {

    private boolean isRunning = false;
    private boolean isIdling = false;
    private boolean traitorvoting = false;
    private int seconds = 60;
    private int idelID;

    @Override
    public void run() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                ScoreBoardManager.updateScoreBoard();
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.setLevel(seconds);
                }
                switch (seconds){

                    case 60: case 30: case 15: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel startet in "+seconds+" Sekunden.");
                        break;

                    case 20:
                        Bukkit.broadcastMessage(Var.pr+"Wenn ihr jetzt eine Zahl zwischen 0 und 100 in den Chat schreibt und am nächsten an meiner Zahl seid, werdet ihr Traitor !");
                        traitorvoting = true;
                        //beenden !
                        break;

                    case 10:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel startet in §b"+seconds+" §7Sekunden");
                        for(Player p : Bukkit.getOnlinePlayers()){
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 10 ,10);
                            p.closeInventory();
                            p.getInventory().clear();
                        }
                        Bukkit.broadcastMessage(Var.pr+"Voting wurde beendet !");
                        MapManager.currentmap = VoteManager.getVotedMap();
                        CloudServer.getInstance().setMotdAndUpdate(MapManager.currentmap);

                        Bukkit.broadcastMessage(Var.pr+"Voted Map: "+MapManager.currentmap);


                        break;

                    case 1:
                        Random r = new Random();
                        int zahl = r.nextInt(100);
                        if(!TraitorChatListener.getList().isEmpty()){
                            if(TeamManager.getMaxTraitors() > TeamManager.getTraitors().size()){
                                TeamManager.addTraitor(TraitorChatListener.getPlayerWhithNearstValue(zahl));
                                TraitorChatListener.getPlayerWhithNearstValue(zahl).sendMessage(Var.pr+"Du wirst Traitor werden !");
                                Bukkit.broadcastMessage(Var.pr+"Die Zahl war "+zahl+".");
                            }else{
                                TraitorChatListener.getPlayerWhithNearstValue(zahl).sendMessage(Var.pr+"Du wärst traitor geworden, allerdings haben sich zu viele einen Traitor pass eingelöst !");
                            }
                        }else{
                            if(((LobbyState) Main.getInstance().getGameStateManager().getCurrentGameState()).getLobbyCountdown().isTraitorVoting()) {
                                Bukkit.broadcastMessage(Var.pr + "Niemand hat eine Zahl in den Chat geschrieben.... Schade....");
                                Bukkit.broadcastMessage(Var.pr + "Die zahl wäre " + zahl + " gewesen !");
                            }
                        }

                        Bukkit.broadcastMessage(Var.pr+"Das Spiel startet in einer Sekunde.");
                        break;
                    case 0:
                        Main.getInstance().getGameStateManager().setGameState(GameState.INGAMESTATE);

                        cancel();
                        MapManager.TeleportAllPlayersRandom();
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
        traitorvoting = false;
        TraitorChatListener.getList().clear();
        TraitorChatListener.getMap().clear();
        Bukkit.getScheduler().cancelTask(taskID);
        seconds = 30;
    }

    public void idle(){
        isIdling = true;
        idelID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Var.pr+"Es fehlen noch Spieler bis das Spiel starten kann !");
            }
        },0,20*20);
    }

    public void cancelIdle(){
        isIdling = false;
        Bukkit.getScheduler().cancelTask(idelID);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isIdling() {
        return isIdling;
    }

    public int getSeconds() {
        return seconds;
    }

    public boolean isTraitorVoting() {return traitorvoting;  }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
