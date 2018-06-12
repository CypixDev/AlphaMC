package de.Cypix.SGFFA.CMD;

import de.Cypix.SGFFA.Manager.MapManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CMDForceMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("weakmc.forcemap")){
                if(args.length == 0){

                    Inventory inv = Bukkit.createInventory(null, 3*9, "§b§lMaps");
                    for(int i = 0; i< MapManager.getMaps().size(); i++){
                        ItemStack item = MapManager.getItem(MapManager.getMaps().get(i));
                        inv.setItem(i, item);
                    }


                    p.openInventory(inv);
                    return true;
                }
                if(args.length == 1){
                    if(MapManager.mapexists(args[0])){
                        MapManager.forceMap = args[0];
                        p.sendMessage("Die nächste map ist getzt !");
                    }else{
                        p.sendMessage("Die map gibt es nicht !");
                    }
                    return true;
                }

                p.sendMessage("§7Benutze §b/forcemap");
            }
        }


        return false;
    }
}
