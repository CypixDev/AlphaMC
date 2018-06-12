package de.Cypix.FFA.CMD;

import de.Cypix.FFA.Manager.BuildManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDBuild implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("FFA.Build")){
                if(!BuildManager.isBuilding(p)){
                    BuildManager.addPlayer(p);
                    p.sendMessage("Du kannst jetzt bauen !");
                }else{
                    BuildManager.removeFormList(p);
                    p.sendMessage("Du kannst nun nicht mehr bauen !");
                }
            }else p.sendMessage("§cKeine Rechte !");
        }
        return false;
    }
}
