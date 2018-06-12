package de.Cypix.Lobby.CMD;

import de.Cypix.Lobby.Manager.ActionBarManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDActionbar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Lobby.ActionBar")){
                if(args.length > 0){
                    String msg = "";
                    for(int i = 0;i<args.length;i++){
                        msg = msg+" "+args[i];
                    }
                    ActionBarManager.actionBar = msg;
                }
            }
        }
        return false;
    }
}
