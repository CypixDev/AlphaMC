package de.Cypix.CityBuild.CMD.Warps;

import de.Cypix.CityBuild.Manager.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("CityBuild.setup")){
                if(args.length > 1){
                    if(args[1].equalsIgnoreCase("create")){
                        WarpManager.createWarp(p, args[0]);
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("setitem")){
                        WarpManager.setWarpItem(p, args[0]);
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("setdisplay")){
                        WarpManager.setDisplayname(args[0], args[2]);
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("setSpawn")){
                        WarpManager.setWarpSpawn(p, args[0]);
                        return true;
                    }
                }
                if(args.length == 0){
                    WarpManager.openWarpInv(p);
                    return true;
                }
                p.sendMessage("Use: /warp <warpname> <create/setitem/setdisplay/setspawn>");
                return true;
            }
            if(args.length == 0){
                WarpManager.openWarpInv(p);
            }
        }

        return false;
    }
}
