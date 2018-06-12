package de.cypix.buildffa.setup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDSetup implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("BuildFFA.setup")){
                if(args.length > 0){
                    //setup -> map
                    if(args.length == 4){
                        if(args[0].equalsIgnoreCase("map")){
                            MapSetup mapSetup = new MapSetup(args[1]);
                            if(args[2].equalsIgnoreCase("set")){
                                if(args[3].equalsIgnoreCase("villager")){
                                    p.sendMessage("Villager !");
                                    mapSetup.setVillager(p.getLocation());
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("spawn")){
                                    p.sendMessage("Spawn !");
                                    mapSetup.setSpawn(p.getLocation());
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("death")){
                                    p.sendMessage("Death !");
                                    mapSetup.setDeathZone((int) p.getLocation().getY());
                                    return true;
                                }
                                if(args[3].equalsIgnoreCase("save")){
                                    p.sendMessage("Save !");
                                    mapSetup.setSaveZone((int) p.getLocation().getY());
                                    return true;
                                }
                            }
                        }
                    }
                }
                p.sendMessage("Use: /setup map <mapname> <set> <villager, spawn, save, death>");
            }
        }
        return false;
    }
}
