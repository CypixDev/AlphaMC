package de.cypix.gungame.cmd;

import de.cypix.gungame.main.GunGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSetLevel implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("GunGame.setup")){
                if(args.length == 1){
                    try {
                        Integer i = Integer.valueOf(args[0]);
                        GunGame.getInstance().getLevel().saveLevel(i, p.getPlayer());
                    }catch(NumberFormatException e1){
                        p.sendMessage("Bitte gib das Level als Zahl ein (int)");
                    }
                }
            }
        }
        return false;
    }
}
