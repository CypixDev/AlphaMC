package de.Cypix.CityBuild.CMD.Tp;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.tp")){
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null){
                        p.teleport(target);
                        p.sendMessage(Var.pr+"§7Du wirst zu §b"+target.getName()+" §7Teleportiert !");
                    }else p.sendMessage(Var.offline);
                }
                if(args.length == 2){
                    Player p1 = Bukkit.getPlayer(args[0]);
                    Player p2 = Bukkit.getPlayer(args[1]);
                    if(p1 != null && p2 != null) {
                        p1.teleport(p2);
                        p1.sendMessage(Var.pr + "§7Du wurdest von §b" + p.getName() + " §7zu §b" + p1.getName() + " §7Teleportiert.");
                        p.sendMessage(Var.pr + "§7Du hast den §b" + p1.getName() + " §7zu §b" + p2.getName() + " §7Teleportiert.");
                    }else p.sendMessage(Var.offline);
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
