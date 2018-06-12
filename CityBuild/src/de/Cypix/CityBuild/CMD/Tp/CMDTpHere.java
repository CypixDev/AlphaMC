package de.Cypix.CityBuild.CMD.Tp;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTpHere implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    TeleportManager.sendTphere(p, target);
                    target.sendMessage(Var.pr+"§7Du hast eine §bTphere §7anfrage von §b"+target.getName()+" §7bekommen.");
                    p.sendMessage(Var.pr+"§7Du hast die §bTphere §7anfrage an §b"+target.getName()+" §7gesendet!");
                }else p.sendMessage(Var.offline);
            }
        }

        return false;
    }
}
