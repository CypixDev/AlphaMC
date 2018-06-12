package de.Cypix.Survival.CMD;

import de.Cypix.Survival.FileManager.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMDVanish implements CommandExecutor {

    public static List<Player> list = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("survival.vanish")){
                if(args.length == 0){
                    if(!list.contains(p)) {
                        for (Player pp : Bukkit.getOnlinePlayers()) {
                            if(!pp.hasPermission("Survival.vanisch.see")) {
                                pp.hidePlayer(p);
                            }
                        }
                        list.add(p);
                        p.sendMessage("Du bist jetzt für zimlich alle Spieler unsichtbar !");
                    }else{
                        for(Player pp : Bukkit.getOnlinePlayers()){
                            pp.showPlayer(p);
                        }
                        p.sendMessage("Du bist nun für alle Spieler sichtbar !");
                        list.remove(p);
                    }

                    return true;
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        if(!list.contains(target)) {
                            for (Player pp : Bukkit.getOnlinePlayers()) {
                                if (!pp.hasPermission("Survival.vanisch.see")) {
                                    pp.hidePlayer(target);
                                }
                                list.add(target);
                                target.sendMessage("Du bist jetzt für zimlich alle Spieler unsichtbar !");
                                p.sendMessage("Der Spieler " + target.getDisplayName() + " ist jetzt für zimlich alle Spieler unsichtbar !");
                            }
                        }else{
                            target.sendMessage("Du bist jetzt wieder für alle sichtbar !");
                            p.sendMessage("Der Spieler "+target.getDisplayName()+" ist jetzt wieder für alle sichtbar !");
                            list.remove(target);
                        }
                    }else{
                        p.sendMessage(Var.offline);
                    }
                    return true;
                }
                p.sendMessage("§cUse: /vanish <Player>");
            }else{
                p.sendMessage(Var.noperm);
            }
        }


        return false;
    }
}
