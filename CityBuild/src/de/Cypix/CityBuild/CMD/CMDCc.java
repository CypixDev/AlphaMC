package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDCc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.cc")){
                for(int i = 0;i<110;i++){
                    Bukkit.broadcastMessage(" ");
                }
                Bukkit.broadcastMessage(Var.pr+"Der §b§lChat §r§7wurde von §b"+p.getName()+" §7geleert !");
            }else p.sendMessage(Var.noperm);
        }

        return false;
    }
}
