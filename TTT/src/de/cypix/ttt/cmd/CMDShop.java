package de.cypix.ttt.cmd;

import de.cypix.ttt.manager.TeamManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDShop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(TeamManager.getTraitors().contains(p)){
                //traitor shop
                return true;
            }
            if(TeamManager.getDetectives().contains(p)){
                //detectives
                return true;
            }
        }
        return false;
    }
}
