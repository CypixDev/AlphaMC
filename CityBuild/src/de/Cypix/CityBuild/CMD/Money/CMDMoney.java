package de.Cypix.CityBuild.CMD.Money;

import de.Cypix.CityBuild.API.UUIDFetcher;
import de.Cypix.CityBuild.File.PlayerFile;
import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.MoneyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CMDMoney implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                MoneyManager m = new MoneyManager(p);
                p.sendMessage(Var.pr+"§7Du hast §b"+m.getBalance()+" §7Money");
                return true;
            }
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    MoneyManager m = new MoneyManager(target);
                    p.sendMessage(Var.pr+"§b"+target.getName()+" §7hat §b"+m.getBalance()+" §7Money");
                }else{
                    String uuid = UUIDFetcher.getUUID(args[0]).toString();
                    if(PlayerFile.isRegistert(uuid)){

                        p.sendMessage(Var.pr+"§b"+args[0]+" §7hat §b"+PlayerFile.getInt(uuid, " §7Money")+" §7Money");
                    }else{
                        p.sendMessage(Var.pr+"§7Der Spieler hat noch kein §bCityBuild §7gespielt !");
                    }
                }
                return true;
            }
            p.sendMessage(Var.pr+"§7Bitte benutze §b/money <player>");
        }
        return false;
    }
}
