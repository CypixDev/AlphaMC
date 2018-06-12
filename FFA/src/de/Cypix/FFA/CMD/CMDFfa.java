package de.Cypix.FFA.CMD;

import de.Cypix.FFA.File.Var;
import de.Cypix.FFA.Manager.MapManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFfa implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("FFA.setup")){
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("map")){
                        if(MapManager.mapexists(args[1])){
                            if(args[2].equalsIgnoreCase("setspawn")){
                                MapManager.setMapSpawn(args[1], p.getLocation());
                                p.sendMessage("Der Spawn wurde gesetzt !");
                                return true;
                            }
                            if(args[2].equalsIgnoreCase("setItem")){
                                MapManager.setItem(args[1], p.getItemInHand());
                                p.sendMessage("Dass Item wurde gesetzt !");
                                return true;
                            }

                        }else if(args[2].equalsIgnoreCase("create")){
                            MapManager.createMap(args[1]);
                            p.sendMessage("Die map wurde erstellt jetzt nur noch den spawn und dass Item setzten !");
                            return true;
                        }else{
                            p.sendMessage("Erst die map erstellen !");
                            return true;
                        }
                    }
                }




                p.sendMessage("§cUse: /ffa map <map> <setSpawn/create/setItem>");
            }else p.sendMessage(Var.noperm);
        }


        return false;
    }
}
