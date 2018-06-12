package de.cypix.buildffa.game;

import de.cypix.alphaapi.main.AlphaAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StatsListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
            Player killed = e.getEntity();
            Player killer = e.getEntity().getKiller();
            e.setDeathMessage(null);
            killed.sendMessage("Du wurdest von "+killer.getName()+" umgebracht !");
            killer.sendMessage("Du hast "+killed.getName()+" umgebracht !");
            AlphaAPI.getInstance().getStatsPlayer(killer).getBuildFFAStats().addKill();
            AlphaAPI.getInstance().getStatsPlayer(killed).getBuildFFAStats().addDeath();
            KillStreakManager.reset(killed);
            KillStreakManager.add(killer);
        }else if(e.getEntity() instanceof Player){
            e.setDeathMessage(null);
            e.getEntity().sendMessage("Du bist gestorben !");
        }
    }
}
