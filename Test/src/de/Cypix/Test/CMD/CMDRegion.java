package de.Cypix.Test.CMD;

import de.Cypix.Test.Regionen.RegionType;
import de.Cypix.Test.Regionen.RegionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDRegion implements CommandExecutor {

    String pr = "§7[§aRegions§7] ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Region")){
                if(args.length > 0){
                    if(RegionsManager.regionExists(args[0])){
                        if(args[1].equalsIgnoreCase("set1")){
                            RegionsManager.saveRegionLocation(args[0], 1, p.getLocation());
                            p.sendMessage(pr+"Position 1 wurde für die Region "+args[0]+" gesetzt !");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("set2")){
                            RegionsManager.saveRegionLocation(args[0], 2, p.getLocation());
                            p.sendMessage(pr+"Position 2 wurde für die Region "+args[0]+" gesetzt !");
                            return true;
                        }
                        if(args[1].equalsIgnoreCase("add")){
                            if(args.length == 2){
                                if(RegionsManager.getRegionTypes(args[0]).contains(RegionType.valueof(args[2]))){
                                    p.sendMessage("Diese Option ist bereits enthalten !");
                                    return true;
                                }else{
                                    RegionsManager.addOption(args[0], RegionType.valueof(args[2]));
                                    p.sendMessage("Erfolgreich hizugefügt !");
                                    return true;
                                }
                            }
                        }
                    }else{
                        if(args[1].equalsIgnoreCase("create")){
                            RegionsManager.createRegion(args[0]);
                            p.sendMessage(pr+"Region erstellt !");
                            return true;
                        }
                        p.sendMessage(pr+"§cErst die Region Erstellen !");
                        return true;
                    }

                    return true;
                }
                p.sendMessage(pr+"Use: /region <name> <create/set1/set2/add> <option>");

            }
        }

        return false;
    }
}
