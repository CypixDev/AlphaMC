package de.Cypix.CityBuild.CMD.Tp;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTpAccept implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(TeleportManager.accept(p)){
                p.sendMessage(Var.pr+"§7Du hast die §bTpa §7anfrage angenommen.");
            }else{
                p.sendMessage(Var.pr+"§7Du hast §bkeine §7Tpa anfrage bekommen!");
            }
        }
        return false;
    }
}
