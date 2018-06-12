package de.cypix.ttt.countdown;

import de.cypix.ttt.Var;
import de.cypix.ttt.api.Title;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.ScoreBoardManager;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.mysql.PlayerStats;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InGameCountDown extends Countdown {

    int seconds = 60*15;
    int endingsec = 15;
    int savetime = 20;
    int saveIdelID;

    @Override
    public void run() {
        //schutzZeit
        saveIdelID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch (savetime){

                    case 20: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Var.pr+"Die Schutz-Zeit endet in "+savetime+" Sekunden !");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Var.pr+"Die Schutz-Zeit endet in einer Sekunde !");
                        break;

                    case 0:
                        runTime();
                        TeamManager.setTeams();
                        Main.getInstance().setHitAllow();
                        Bukkit.getScheduler().cancelTask(saveIdelID);
                        TeamManager.RemoveTickets();
                        TeamManager.addGame();
                        break;
                }

                ScoreBoardManager.updateScoreBoard();

                savetime--;
            }
        }, 0 , 20);



    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public void sendPlayer(UUID uuid, String server) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(uuid);
        PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
        playerExecutorBridge.sendPlayer(cloudPlayer,  server);
    }

    public void runTime(){
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                ScoreBoardManager.updateScoreBoard();

                switch (seconds){
                    case 60*10: case 60*5: case 60*2:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel endet in "+seconds/60+" Minuten.");
                        break;
                    case 60:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel endet in einer Minute.");
                        break;

                    case 30: case 15: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel endet in "+seconds+" Sekunden !");
                        break;

                    case 1:
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel endet in einer Sekunde !");
                        break;
                    case 0:
                        //Spiel beenden !
                        Bukkit.broadcastMessage(Var.pr+"Das Spiel wurde Beendet !");
                        for(Player p : Main.getInstance().getPlayers()){
                            PlayerStats ps = new PlayerStats(p);
                            if(!TeamManager.sayWhatheis(p).equalsIgnoreCase("§cTraitor")){
                                ps.addWin();
                            }else{
                            }
                        }
                        Main.getInstance().setHitDisAllow();


                        String winners;
                        winners = "Innocents";

                        for(Player p : Bukkit.getOnlinePlayers()){
                            Title.sendTitle(p, "Die "+winners, "haben das Spiel Gewonnen", 20, 40 ,20);
                        }
                        Bukkit.broadcastMessage(Var.pr+"Die "+winners+" haben Gewonnen !");




                        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {

                                switch (endingsec){

                                    case 10: case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:
                                        PlayerStats.saveallplayersStats();
                                        Bukkit.broadcastMessage(Var.pr+"Der Server startet in "+endingsec+" Sekunden neu !");
                                        break;

                                    case 1:
                                        Bukkit.broadcastMessage(Var.pr+"Der Server startet in einer Sekunde neu !");
                                    case 0:
                                        Bukkit.broadcastMessage(Var.pr+"Der Server startet jetzt neu !");
                                        for(Player p : Bukkit.getOnlinePlayers()){
                                            sendPlayer(p.getUniqueId() ,"Lobby-1");
                                            PlayerStats.saveStats(p);
                                        }
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                            @Override
                                            public void run() {
                                                Bukkit.shutdown();
                                            }
                                        },10);
                                        break;
                                }
                                endingsec--;
                            }
                        },  0, 20);
                        break;
                }



                seconds--;
            }
        }, 0, 20);
    }

}
