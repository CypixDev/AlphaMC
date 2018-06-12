package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDEc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;

            if(p.hasPermission("citybuild.ec.others")){
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    p.openInventory(target.getEnderChest());
                }else{
                    p.openInventory(p.getEnderChest());
                }
                return true;
            }

            if(p.hasPermission("citybuild.ec")){
                p.openInventory(p.getEnderChest());
                return true;
            }
            p.sendMessage(Var.nopermsvip);
        }
        return false;
    }
}
