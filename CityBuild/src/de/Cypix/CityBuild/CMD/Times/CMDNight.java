package de.Cypix.CityBuild.CMD.Times;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDNight implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.night")){
                if(args.length == 0){
                    p.getWorld().setTime(18000);
                    return true;
                }
                p.sendMessage("Use: /night");
            }else p.sendMessage(Var.noperm);
        }

        return false;
    }
}
