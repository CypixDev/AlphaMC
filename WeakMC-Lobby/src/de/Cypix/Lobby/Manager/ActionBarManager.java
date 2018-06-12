package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.API.TitleAPI;
import de.Cypix.Lobby.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBarManager {

    public static String actionBar = "&6Es sind gerade keine Wartungen gelant oder so....";

    public static void startScheduler(){
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    TitleAPI.sendActionBar(p, ChatColor.translateAlternateColorCodes('&', actionBar));
                }
            }
        }, 0, 20L);
}
}
