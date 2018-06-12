package de.Cypix.Survival.CMD;

import de.Cypix.Survival.Manager.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            HomeManager hm = new HomeManager(p);
            if(args.length == 2){
                if(args[1].equalsIgnoreCase("setItem")){
                    hm.setItem(args[0], p.getItemInHand());
                    p.sendMessage("Item gesetzt !");
                }
                return true;
            }
            if(args.length == 1){
                if(hm.homeexists(args[0])){
                    p.teleport(hm.getLocation(args[0].toLowerCase()));
                }
            }
        }
        return false;
    }
}
