package de.Cypix.CityBuild.CMD.Spawn;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.SpawnManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.setspawn")){
                p.sendMessage(Var.pr+"Spawn gesetzt !");
                SpawnManager.saveSpawn(p.getLocation());
            }else{
                p.sendMessage(Var.noperm);
            }
        }
        return false;
    }
}
