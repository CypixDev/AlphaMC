package de.Cypix.SGFFA.CMD;

import de.Cypix.SGFFA.File.Var;
import de.Cypix.SGFFA.Manager.MapManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSgffa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("SGFFA.setup")) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("map")) {
                        if (MapManager.mapexists(args[1])) {
                            if (args[2].equalsIgnoreCase("addspawn")) {
                                p.sendMessage("Der Spawn mit der Nummer "+MapManager.addMapSpawn(args[1], p.getLocation())+" wurde gesetzt !");
                                return true;
                            }
                            if (args[2].equalsIgnoreCase("setItem")) {
                                MapManager.setItem(args[1], p.getItemInHand());
                                p.sendMessage("Dass Item wurde gesetzt !");
                                return true;
                            }
                            if(args[2].equalsIgnoreCase("getSpawns")){
                                p.sendMessage(MapManager.getSpawnss(args[1])+"");
                                return true;
                            }

                        } else if (args[2].equalsIgnoreCase("create")) {
                            MapManager.createMap(args[1]);
                            p.sendMessage("Die map wurde erstellt jetzt nur noch den spawn und dass Item setzten !");
                            return true;
                        } else {
                            p.sendMessage("Erst die map erstellen !");
                            return true;
                        }
                    }

                }

                if (args.length == 1) {
                    if(args[0].equalsIgnoreCase("get")){
                        if(args[1].equalsIgnoreCase("chest")){
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("enderchest")){
                            return true;
                        }
                    }
                }
                p.sendMessage("§cUse: /ffa <map/get> <map/chest/enderchest> <addSpawn/create/setItem/setkill/setsave>");

            }else p.sendMessage(Var.noperm);
        }



        return false;
    }
}
