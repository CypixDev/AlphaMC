package de.cypix.flash.cmd;

import de.cypix.flash.main.Main;
import de.cypix.flash.manager.TrollManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDTroll implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("flash.troll")){
                if(TrollManager.isTrolling(p)){
                    TrollManager.remove(p);
                    p.sendMessage(Main.pr+"Du bist jetzt nicht mehr im Troll-Modus !");
                }else{
                    TrollManager.setTrolling(p);
                    p.sendMessage(Main.pr+"Du bist jetzt im Troll Modus !");
                }
            }else p.sendMessage(Main.noperm);
        }
        return false;
    }
}
