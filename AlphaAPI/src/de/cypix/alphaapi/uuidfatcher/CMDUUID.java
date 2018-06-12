package de.cypix.alphaapi.uuidfatcher;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDUUID implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                p.sendMessage("§cUse: /UUID <player>");
                return true;
            }
            if(args.length == 1){
                p.sendMessage("UUID of Player "+args[0]+" is "+UUID.getUUID(args[0]));
                return true;
            }

        }
        return false;
    }
}
