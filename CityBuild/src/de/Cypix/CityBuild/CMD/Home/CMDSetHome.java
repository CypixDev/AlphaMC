package de.Cypix.CityBuild.CMD.Home;

import de.Cypix.CityBuild.Manager.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                new HomeManager(p).setHome("Home");
            }
            if(args.length == 1){
                HomeManager home = new HomeManager(p);
                home.setHome(args[0]);
            }
        }
        return false;
    }
}
