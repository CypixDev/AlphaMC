package de.Cypix.Survival.CMD;

import de.Cypix.Survival.Manager.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            HomeManager hm = new HomeManager(p);

            if(args.length == 1) {

                if(hm.homeexists(args[0])){
                    p.sendMessage("Dass Home hast du schon gesetzt !");
                    p.sendMessage("Die Location wurde neu gesetzt !");
                    return false;
                }
                if(hm.getHighestHome() == -2){
                    hm.saveHome(args[0]);
                    p.sendMessage("Dass Home "+args[0]+" wurde erstlellt !");
                    return true;
                }
                if (hm.getHighestHome() >= hm.getHomes().size()) {
                    hm.saveHome(args[0]);
                    p.sendMessage("Dass Home "+args[0]+" wurde erstlellt !");
                    return true;
                }
                p.sendMessage("hä ?! ");
            }
        }


        return false;
    }
}
