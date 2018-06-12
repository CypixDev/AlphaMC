package de.cypix.gungame.ingame;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.gungame.main.GunGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StatsListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage("");
        if(e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player){
            Player killed = e.getEntity();
            Player killer = e.getEntity().getKiller();

            //killer stats

            killer.sendMessage("§aDu hast "+killed.getName()+" umgebracht !");
            killer.sendMessage("§bDu hast 5 Points und 10 Coins bekommen !");
            AlphaAPI.getInstance().getStatsPlayer(killer).getCoins().addCoins(10);
            AlphaAPI.getInstance().getStatsPlayer(killer).getGunGameStats().addPoints(5);
            AlphaAPI.getInstance().getStatsPlayer(killer).getGunGameStats().addKill();
            AlphaAPI.getInstance().getStatsPlayer(killer).getGunGameStats().addLevel();

            //level record update !
            int record = AlphaAPI.getInstance().getStatsPlayer(killed).getGunGameStats().getLevelRecord();
            int level = AlphaAPI.getInstance().getStatsPlayer(killed).getGunGameStats().getLevel();
            if(level > record){
                killed.sendMessage("Du hattest einen neuen High-Score vong dein Level her !");
                AlphaAPI.getInstance().getStatsPlayer(killed).getGunGameStats().setLevelRecord(level);
            }


            //killes stats

            killed.sendMessage("§cDu wurdest von "+killer.getName()+" umgebracht !");
            AlphaAPI.getInstance().getStatsPlayer(killed).getGunGameStats().setLevel(0);
            AlphaAPI.getInstance().getStatsPlayer(killed).getGunGameStats().addDeath();

            GunGame.getInstance().getLevel().setLevel(killer);
            GunGame.getInstance().getLevel().setLevel(killed);

            ScoreBoardManager.updateScoreBoard();

            final Player p = killed;

            Bukkit.getScheduler().scheduleSyncDelayedTask(GunGame.getInstance(), new Runnable() {
                @Override
                public void run() {
                    p.spigot().respawn();
                }
            }, 5);

        }
    }

}
