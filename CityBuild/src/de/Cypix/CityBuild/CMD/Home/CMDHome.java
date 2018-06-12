package de.Cypix.CityBuild.CMD.Home;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.HomeManager;
import de.Cypix.CityBuild.Manager.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CMDHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                HomeManager home = new HomeManager(p);
                if(home.homeexists(args[0])){
                    TeleportManager.teleportPlayer(p, home.getHomeLoc(args[0]));
                }else{
                    p.sendMessage(Var.pr+"§7Dieses §bHome §7must du erst setzten!");

                }
            }
            if(args.length == 0){

                String msg = "§b";
                List<String> list = new HomeManager(p).getHomes();
                if(list.contains("Home")){
                    HomeManager home = new HomeManager(p);
                    TeleportManager.teleportPlayer(p, home.getHomeLoc("Home"));
                    return true;
                }
                for(int i = 0;i<list.size();i++){
                    if(i == list.size()-1){
                        msg = msg+list.get(i);
                    }else {
                        msg = msg + list.get(i) + "§7, §b";
                    }
                }
                p.sendMessage(Var.pr+"Deine Homes: "+msg);
            }
        }
        return false;
    }
}
