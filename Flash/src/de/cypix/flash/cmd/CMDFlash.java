package de.cypix.flash.cmd;

import de.cypix.flash.main.Main;
import de.cypix.flash.manager.MapManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFlash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("flash.setup")){
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
                            if(args[2].equalsIgnoreCase("set")){
                                if(args[3].equalsIgnoreCase("spawn")){
                                    MapManager.setInGameLocation(args[1], p.getLocation());
                                    p.sendMessage("Spawn gesetzt !");
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("respawn")){
                                    MapManager.setRespawnZone(args[1], p.getLocation());
                                    p.sendMessage("respawn zone gesetzt");
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("Item")){
                                    MapManager.setItem(p, args[1]);
                                    p.sendMessage("Item gesetzt");
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
                p.sendMessage(Main.pr+"Use: /flash <map/setlobby> <map> <set/create> <spawn/respawn/item/display> <display>");
            }else p.sendMessage(Main.noperm);
        }
        return false;
    }
}
