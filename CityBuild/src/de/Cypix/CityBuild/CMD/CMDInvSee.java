package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDInvSee implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.invsee")){
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null){
                        p.openInventory(target.getInventory());
                    }else p.sendMessage(Var.offline);
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
