package de.Cypix.Belohnung.CMD;

import de.Cypix.Belohnung.Manager.BelohnungManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDBelohnung implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Lobby.setBelohnung")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("set")) {
                        BelohnungManager.setVillager(p);
                        p.sendMessage("Der Villager wurde gesetzt !");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        if (!BelohnungManager.list.contains(p)) {
                            p.sendMessage("Bitte clicke jetzt auf den Villager den du removen möchtest !");
                            BelohnungManager.list.add(p);
                            return true;
                        } else {
                            BelohnungManager.list.remove(p);
                            p.sendMessage("Der vorgang wurde abgebrochen !");
                            return true;
                        }
                    }
                }else{
                    p.sendMessage("§cUse: /belohnung <set/remove>");
                    return false;
                }
            }
        }

        return false;
    }

}
