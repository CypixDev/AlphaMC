package de.Cypix.CityBuild.CMD.Warps;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.TeleportManager;
import de.Cypix.CityBuild.Manager.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFarmwelt implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            TeleportManager.teleportPlayer(p, WarpManager.getWarpLocation("farmwelt"));
            p.sendMessage(Var.pr+"Du wurdest zu der §bFarmwelt §7Teleportiert!");
        }
        return false;
    }
}
