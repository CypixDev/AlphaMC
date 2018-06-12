package de.Cypix.SGFFA.Listener;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.Cypix.SGFFA.File.Var;
import de.Cypix.SGFFA.MYSQL.PlayerStats;
import de.Cypix.SGFFA.Main.main;
import de.Cypix.SGFFA.Manager.KillStreakManager;
import de.Cypix.SGFFA.Manager.MapManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.CloudNetwork;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

public class StatsListener implements Listener {


    private static String getPrefix(Player p){
        String rank = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getPrefix();

        return rank;
    }

    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        if ((e.getEntity().getKiller() instanceof Player)) {
            Player killer = e.getEntity().getKiller();
            killer.setHealth(20.0D);
            final Player killed = e.getEntity();

            PlayerStats killerstats = new PlayerStats(killer);
            PlayerStats killedstats = new PlayerStats(killed);


            killerstats.addKill();
            killedstats.addDeath();
            KillStreakManager.resetKillStreak(killed);
            KillStreakManager.addKillStreak(killer);


            String msg1 = ChatColor.translateAlternateColorCodes('&', getPrefix(killed) + killed.getName());
            String msg2 = ChatColor.translateAlternateColorCodes('&', getPrefix(killer) + killer.getName());

            Coins.addCoins(killer, 5);

            e.setDeathMessage(Var.pr+"§c" + msg1 + " §8wurde von §6" + msg2 + " §8umgebracht§7!");
            killer.sendMessage(Var.pr+"Du hast §b5 Coins §7dazu bekommen!");

            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getinstance(), new Runnable() {
                public void run() {
                    killed.spigot().respawn();
                }
            }, 4L);

        }else{

            Player killed = e.getEntity();String msg1 = ChatColor.translateAlternateColorCodes('&', getPrefix(killed) + killed.getName());

            e.setDeathMessage(Var.pr+"§c" + msg1 + " §7ist gestorben.....");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getinstance(), new Runnable() {
                public void run() {
                    killed.spigot().respawn();
                }
            }, 4L);

        }
    }




    /*@EventHandler
    public void onDamage(final EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
            final Player hit = (Player) e.getEntity();
            Player damage = (Player)e.getDamager().getPassenger();
            double health = hit.getHealth()-e.getFinalDamage();

            if(health <= 1) {

                final Player killed = (Player) e.getEntity();
                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getinstance(), new Runnable() {
                    @Override
                    public void run() {
                        hit.teleport(MapManager.getLocation(MapManager.currentMap));
                        killed.setHealth(20);
                    }
                },1);

                Player killer = (Player) e.getDamager();
                killer.setHealth(20);

                PlayerStats killerstats = new PlayerStats(killer);
                PlayerStats killedstats = new PlayerStats(killed);

                killerstats.addKill();
                killedstats.addDeath();
                KillStreakManager.resetKillStreak(killed);
                KillStreakManager.addKillStreak(killer);

                InvManager.setInv(killer);

                String msg1 = ChatColor.translateAlternateColorCodes('&', getPrefix(killed) + killed.getName());
                String msg2 = ChatColor.translateAlternateColorCodes('&', getPrefix(killer) + killer.getName());

                Coins.addCoins(killer, 5);

                Bukkit.broadcastMessage("§7[§bFFA§7] §c" + msg1 + " §8wurde von §6" + msg2 + " §8umgebracht§7!");
                killer.sendMessage("§7[§bFFA§7] Du hast §b5 Coins §7dazu bekommen!");

                killed.setVelocity(new Vector());
                InvManager.setInv(killed);


                damage.playSound(damage.getLocation(), Sound.BAT_DEATH, 10, 10);
                e.setCancelled(true);

            }

        }
    }*/

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        e.setRespawnLocation(MapManager.teleportRandom(e.getPlayer()));
        e.getPlayer().setVelocity(new Vector());
        e.getPlayer().closeInventory();
    }


    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) e.setCancelled(true);
    }


    @EventHandler
    public void onDrop(PlayerDeathEvent e){
        e.getDrops().clear();
    }
}
