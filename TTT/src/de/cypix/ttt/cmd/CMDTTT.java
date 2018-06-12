package de.cypix.ttt.cmd;

import de.cypix.ttt.Var;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.MapManager;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class CMDTTT implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ttt.setup")){
                if(args.length == 1) {
                    if (args[0].equalsIgnoreCase("setlobby")) {
                        MapManager.setLobbySpawn(p.getLocation());
                        p.sendMessage("Spawn für die Lobby gesetzt !");
                        return true;
                    }
                }
                if(args.length == 3){
                    if(args[0].equalsIgnoreCase("map")){
                        if(args[2].equalsIgnoreCase("create")){
                            if(MapManager.mapcreated(args[1])){
                                p.sendMessage("Diese Map wurde schon erstellt !");
                            }else{
                                MapManager.createMap(args[1]);
                                p.sendMessage("Die Mapwurde erstellt !");
                            }
                            return true;
                        }
                    }
                }
                if(args.length >= 4){
                    if(args[0].equalsIgnoreCase("map")){
                        if(MapManager.mapcreated(args[1])){
                            if(args[2].equalsIgnoreCase("add")){
                                if(args[3].equalsIgnoreCase("spawn")){
                                    p.sendMessage("Du hast den Spawn mit der Nummer "+MapManager.addSpawnLocation(args[1], p.getLocation())+" gesetzt !");
                                    return true;
                                }
                            }
                            if(args[2].equalsIgnoreCase("spawns")){
                                p.sendMessage("Die Map "+args[1]+" hat "+MapManager.getSpawns(args[1])+" Spawns !");
                                return true;
                            }

                            if(args[2].equalsIgnoreCase("set")){
                                if(args[3].equalsIgnoreCase("Item")){
                                    MapManager.setItem(p, args[1]);
                                    p.sendMessage("Item gesetzt");
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("testerButton")){
                                    Block block = p.getTargetBlock((HashSet<Byte>) null, 3);
                                    MapManager.setTesterButton(args[1], block.getLocation());
                                    p.sendMessage("Button gesetzt !");
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("testerSpawn")){
                                    MapManager.setTesterSpawn(args[1], p.getLocation());
                                    p.sendMessage("Tester Spawn gesetzt !");
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("display")){
                                    String display = args[4];
                                    display = display.replace("_", " ");
                                    MapManager.setDisplay(args[1], display);
                                    p.sendMessage("Display name gesetzt !");
                                    return true;
                                }

                            }
                        }
                    }
                }
                p.sendMessage(Var.pr+"Use: /ttt <map/setlobby> <map> <set/add/create/spawns> <spawn/item/display/testerbutton/testerspawn> <display>");
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
