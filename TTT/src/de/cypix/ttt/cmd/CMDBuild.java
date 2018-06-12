package de.cypix.ttt.cmd;

import de.cypix.ttt.Var;
import de.cypix.ttt.manager.BuildManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDBuild implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ttt.build")){
                if(BuildManager.isBuilding(p)){
                    p.sendMessage(Var.pr+"Du kannst jetzt nicht mehr Bauen !");
                    BuildManager.remove(p);
                }else {
                    p.sendMessage(Var.pr+"Du kannst jetzt abbauen !");
                    BuildManager.setBuilding(p);
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
