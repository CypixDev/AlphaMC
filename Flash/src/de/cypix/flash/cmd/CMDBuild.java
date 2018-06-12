package de.cypix.flash.cmd;

import de.cypix.flash.main.Main;
import de.cypix.flash.manager.BuildManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDBuild implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Flash.build")){
                if(BuildManager.isBuilding(p)){
                    p.sendMessage(Main.pr+"Du kannst jetzt nicht mehr Bauen !");
                    BuildManager.remove(p);
                }else {
                    p.sendMessage(Main.pr+"Du kannst jetzt abbauen !");
                    BuildManager.setBuilding(p);
                }
            }else p.sendMessage(Main.noperm);
        }
        return false;
    }
}
