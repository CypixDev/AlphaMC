package de.Cypix.CityBuild.CMD.Tp;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTpa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    TeleportManager.sendtpa(target, p);
                    target.sendMessage(Var.pr+"§7Hey! §b "+p.getName()+" §7möchte sich zur dir teleportieren! Möchtest du die Anfrage §bannehmen?");
                    p.sendMessage(Var.pr+"§7Du hast eine §bTp §7anfrage an §b"+target.getName()+" §7gesendet.");
                }else p.sendMessage(Var.offline);
            }
        }
        return false;
    }
}
