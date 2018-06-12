package de.Cypix.CityBuild.CMD.Times;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMDDay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.day")){
                if(args.length == 0){
                    p.getWorld().setTime(6000);
                    return true;
                }
                p.sendMessage("Use: /day");
            }else p.sendMessage(Var.noperm);
        }

        return false;
    }

}
