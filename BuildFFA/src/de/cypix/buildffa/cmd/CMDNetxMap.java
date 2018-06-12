package de.cypix.buildffa.cmd;

import de.cypix.buildffa.main.BuildFFA;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDNetxMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("BuildFFA.nextmap")){
                BuildFFA.getInstance().getGameManager().setSec(21);
            }
        }
        return false;
    }
}
