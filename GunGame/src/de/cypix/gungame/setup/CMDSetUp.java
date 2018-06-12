package de.cypix.gungame.setup;

import de.cypix.gungame.main.GunGame;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class CMDSetUp implements CommandExecutor {

    File file = new File(GunGame.getInstance().getDataFolder(), "Maps.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("GunGame.setup")){
                if(args.length > 1){
                    if(args[0].equalsIgnoreCase("map")){
                        if(args.length == 3){
                            if(args[2].equalsIgnoreCase("setspawn")){
                                p.sendMessage("spawn set !");
                                Setup.addMapIfNotExists(args[1]);
                                Setup.setMapSpawn(args[1], p.getLocation());
                                return true;
                            }
                        }
                        if(args.length == 3){
                            if(args[2].equalsIgnoreCase("setitem")){
                                p.sendMessage("spawn set !");
                                Setup.addMapIfNotExists(args[1]);
                                Setup.setItem(args[1], p.getItemInHand());
                                return true;
                            }
                        }
                        if(args.length == 4){
                            if(args[2].equalsIgnoreCase("setname")){
                                p.sendMessage("Name setted !");
                                Setup.addMapIfNotExists(args[1]);
                                Setup.setMapName(args[1], args[3]);
                                return true;
                            }
                        }
                    }
                }
                p.sendMessage("§cUse: /setup map <name> setspawn/setname/setitem <name>");
            }
        }
        return false;
    }
}
