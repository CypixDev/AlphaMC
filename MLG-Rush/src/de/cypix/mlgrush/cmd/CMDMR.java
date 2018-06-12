package de.cypix.mlgrush.cmd;

import de.cypix.mlgrush.manager.LobbyManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDMR implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("mlgrush.setup")){
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("set")){
                        if (args[1].equalsIgnoreCase("lobby")) {
                            p.sendMessage("Spawn gesetzt !");
                            Location loc = p.getLocation();
                            loc.setY(loc.getY()+1);
                            LobbyManager.setSpawnLocation(loc);
                            return true;
                        }
                    }
                }
                p.sendMessage("§cUse: /mr set lobby");
            }
        }
        return false;
    }
}
