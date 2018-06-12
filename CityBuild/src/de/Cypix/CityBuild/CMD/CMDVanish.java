package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.VanishManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDVanish implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.v")){
                if(!VanishManager.isvisible(p)){
                    p.sendMessage(Var.pr+"§7Du bist jetzt §bsichtbar!");
                    VanishManager.setvisible(p);
                }else{
                    p.sendMessage(Var.pr+"§7Du bist jetzt §bunsichtbar!");
                    VanishManager.setinvisible(p);
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
