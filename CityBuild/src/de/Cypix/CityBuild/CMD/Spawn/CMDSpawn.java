package de.Cypix.CityBuild.CMD.Spawn;

import de.Cypix.CityBuild.Manager.SpawnManager;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            TeleportManager.teleportPlayer(p, SpawnManager.getSpawn());
        }
        return false;
    }
}
