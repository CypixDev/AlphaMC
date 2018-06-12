package de.cypix.flash.countdowns;

import de.cypix.flash.main.Main;
import de.cypix.flash.manager.ScoreBoardManager;
import org.bukkit.Bukkit;

public class IngameCountdown extends Countdown {

   public static int seconds = 60*2;

    int sec = 15;
    @Override
    public void run() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() {
            @Override
            public void run() {
                ScoreBoardManager.updateScoreBoard();
                switch (seconds){
                    case 60*15: case 60*10: case 60*5: case 60*3:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel endet in "+seconds/60+" Minuten");
                        break;
                    case 60:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel endet in einer Minute");
                        break;
                    case 30 : case 15: case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel endet in "+seconds+" Sekunden.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel endet in einer Sekunde.");
                    case 0:
                        Bukkit.broadcastMessage(Main.pr+"Das Spiel ist zuende !");
                        Bukkit.broadcastMessage(Main.pr+"Es hat niemand gewonnen !");
                        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstace(), new Runnable() {
                            @Override
                            public void run() {
                                switch (sec){

                                    case 15: case 10: case 5: case 4: case 3: case 2:
                                        Bukkit.broadcastMessage(Main.pr+"Der Server startet in "+sec+" Sekunde neu !");
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

                                sec--;
                            }
                        },0, 20);

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

    }


    public static String getTime(){
        int sec = seconds;
        int min = 0;
        while(sec >= 60){
            sec-=60;
            min++;
        }

        return min+":"+sec;
    }

}
