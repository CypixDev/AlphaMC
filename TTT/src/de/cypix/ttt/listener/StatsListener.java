package de.cypix.ttt.listener;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.cypix.ttt.Var;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.SpecManager;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.mysql.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class StatsListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            if(Main.getInstance().isHittingAllowed()){
            }else {
                e.getEntity().sendMessage("Blocked !");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player killed = e.getEntity();
        PlayerStats killedstats = new PlayerStats(killed);
        if(e.getEntity().getKiller() instanceof Player){
            Player killer = e.getEntity().getKiller();
            PlayerStats killerstats = new PlayerStats(killer);
            killer.sendMessage(Var.pr+"Du hast "+killed.getName()+" umgebracht.");
            killer.sendMessage(Var.pr+"Er war "+ TeamManager.sayWhatheis(killed));

            e.setDeathMessage("");


            Main.getInstance().getPlayers().remove(killed);
            Main.getInstance().getSpecs().add(killed);
            SpecManager.setSpectator(killed);

            if(killed.getInventory().contains(Material.IRON_SWORD)){
                List<ItemStack> list = e.getDrops();
                list.clear();
                list.add(new ItemStack(Material.IRON_SWORD));
            }else{
                e.getDrops().clear();
            }

            if(TeamManager.getTraitors().contains(killer)){
                Bukkit.broadcastMessage("Traitor");
                if(TeamManager.getTraitors().contains(killed)){
                    killerstats.removeKarma(10);
                    killerstats.addKill();
                    killedstats.addDeath();
                    killer.sendMessage(Var.pr+"Du hast als Strafe -10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"DU hast einen aus deinem Team umgebracht !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }else{
                    killedstats.addDeath();
                    killerstats.addKill();
                    killerstats.addKarma(10);
                    Coins.addCoins(killer, 20);
                    killer.sendMessage(Var.pr+"Du hast 10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"Du hast auch noch 20 Coins bekommen !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }
            }
            if(TeamManager.getInnocents().contains(killer)){
                Bukkit.broadcastMessage("Innocent");
                if(TeamManager.getTraitors().contains(killed)){
                    killedstats.addDeath();
                    killerstats.addKill();
                    killerstats.addKarma(10);
                    Coins.addCoins(killer, 20);
                    killer.sendMessage(Var.pr+"Du hast 10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"Du hast auch noch 20 Coins bekommen !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }else{
                    killerstats.removeKarma(10);
                    killerstats.addKill();
                    killedstats.addDeath();
                    killer.sendMessage(Var.pr+"Du hast als Strafe -10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"DU hast einen aus deinem Team umgebracht !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }
            }
            //detectives
            if(TeamManager.getDetectives().contains(killer)){
                Bukkit.broadcastMessage("Detective");
                if(TeamManager.getTraitors().contains(killed)){
                    killedstats.addDeath();
                    killerstats.addKill();
                    killerstats.addKarma(10);
                    Coins.addCoins(killer, 20);
                    killer.sendMessage(Var.pr+"Du hast 10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"Du hast auch noch 20 Coins bekommen !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }else{
                    killerstats.removeKarma(10);
                    killerstats.addKill();
                    killedstats.addDeath();
                    killer.sendMessage(Var.pr+"Du hast als Strafe -10 Karma bekommen !");
                    killer.sendMessage(Var.pr+"DU hast einen aus deinem Team umgebracht !");
                    TeamManager.removeFromTeam(killed);
                    TeamManager.Check();
                    return;
                }
            }

            /*PlayerStats.saveStats(killer);
            PlayerStats.saveStats(killed);

            TeamManager.removeFromTeam(killed);*/


        }else{
            e.setDeathMessage(Var.pr+killed.getName()+" ist Gestorben !");
            TeamManager.removeFromTeam(killed);
            killedstats.addDeath();
        }
        respawn(e.getEntity());
    }

    private void respawn(Player p){
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        },10);
    }


}
