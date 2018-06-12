package de.Cypix.CityBuild.CMD.Tp;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTpDeny implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(TeleportManager.deny(p)){
                p.sendMessage(Var.pr+"§7Du hast die §bTpa §7anfrage abgelehnt.");
            }else{
                p.sendMessage(Var.pr+"§7Du hast keine §bTpa §7anfrage bekommen!");
            }
        }
        return false;
    }
}
