package de.Cypix.Survival.Manager;

import de.Cypix.Survival.Events.PlayTimeChangeEvent;
import de.Cypix.Survival.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Manager {

    public static void startPlayedTime(){
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    PlayerManager pm = new PlayerManager(p);
                    pm.addPlayedMinuet();
                }
                Bukkit.getPluginManager().callEvent(new PlayTimeChangeEvent());
            }
        }, 1200, 1200);
    }


}
