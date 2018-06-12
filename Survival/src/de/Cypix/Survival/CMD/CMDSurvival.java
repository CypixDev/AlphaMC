package de.Cypix.Survival.CMD;

import de.Cypix.Survival.FileManager.Var;
import de.Cypix.Survival.Manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSurvival implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("Survival.setup")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("setspawn")){
                        LocationManager.saveLocation("Spawn", p);
                        p.sendMessage("§aDer Spawn wurde gesetzt !");
                        return true;
                    }
                }
            }else{
                p.sendMessage(Var.noperm);
            }
        }else{
            sender.sendMessage("Dass kann nur ein Spieler ausfuehren !");
        }
        return false;
    }
}
