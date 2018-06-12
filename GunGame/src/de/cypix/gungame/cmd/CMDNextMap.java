package de.cypix.gungame.cmd;

import de.cypix.gungame.main.GunGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDNextMap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("GunGame.nextmap")){
                GunGame.getInstance().getGameManager().setSec(10);
                p.sendMessage("§b§lDu hast die Zeit auf 10 Sekunden gesetzt !");
            }
        }
        return false;
    }

}
