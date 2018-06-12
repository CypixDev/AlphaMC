package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.AdminShopManager;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class CMDSign implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.sings")){
                if(args.length >= 1){
                    if(args[0].equalsIgnoreCase("getid")) {

                        Block block = p.getTargetBlock((HashSet<Byte>) null, 10);
                         Sign s = (Sign) block.getState();

                         p.sendMessage("§4"+AdminShopManager.getSignId(s.getLocation())+"");
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("setline")){
                        if(args.length == 4){
                            AdminShopManager.setLine(Integer.valueOf(args[1]), Integer.valueOf(args[2]), args[3]);
                            return true;
                        }
                    }
                    if(args[0].equalsIgnoreCase("setitem")){
                        if(args.length == 2){
                            AdminShopManager.setItemtoBuy(Integer.valueOf(args[1]), p.getItemInHand());
                            return true;
                        }
                    }
                    if(args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload")){
                        AdminShopManager.loadSigns();
                        return true;
                    }
                    p.sendMessage("§cUse: /sign <rl/setline/getid/setitem> <signid> <linenum.> <lineString>");
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
