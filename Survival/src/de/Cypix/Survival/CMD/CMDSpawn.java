package de.Cypix.Survival.CMD;

import de.Cypix.Survival.Main.main;
import de.Cypix.Survival.Manager.LocationManager;
import de.Cypix.Survival.Manager.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSpawn implements CommandExecutor {


    private static int idelID;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            SpawnManager.map.put(p, p.getLocation());

            idelID = Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {

                    p.teleport(LocationManager.getLocation("Spawn"));
                    SpawnManager.idelID.remove(p);
                    SpawnManager.map.remove(p);
                    p.sendMessage("§aDu bist jetzt am Spawn !");

                }
            },60);
            SpawnManager.idelID.put(p,idelID);
            p.sendMessage("§cDu wirst in 3 sec Teleportiert !");
            p.sendMessage("§cDu darfst dch nicht bewegen !");
        }
        return false;
    }
}
