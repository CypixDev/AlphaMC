package de.Cypix.SGFFA.CMD;

import de.Cypix.SGFFA.File.Var;
import de.Cypix.SGFFA.Manager.MapManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDNextMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("WeakMC.VIP")){
                if(MapManager.sec <= 10){
                    p.sendMessage(Var.pr+"§7Die Zeit bis zur nächsten §bMap §7ist bereits unter §b10 §7Sekunden!");
                    return true;
                }else{
                    MapManager.sec = 10;
                    p.sendMessage(Var.pr+"§7Die Zeit bis zur nächsten §bMap §7wurde auf §b10 Sekunden §7gesetzt!");
                }
            }
        }
        return false;
    }
}
