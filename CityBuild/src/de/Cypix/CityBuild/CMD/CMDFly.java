package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDFly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.fly")){
                if(args.length == 0){
                    if(p.getAllowFlight()){
                        p.setAllowFlight(false);
                        p.sendMessage(Var.pr+"§bFly §7ist jetzt §bdeaktiviert!");
                    }else{
                        p.setAllowFlight(true);
                        p.sendMessage(Var.pr+"§bFly §7ist jetzt §baktiviert!");
                    }
                    return true;
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target.getAllowFlight()){
                        target.setAllowFlight(false);
                        target.sendMessage(Var.pr+"§bFly §7wurde von §b"+p.getName()+"§7 für dich §bdeaktiviert!");
                        p.sendMessage(Var.pr+"§bFly §7von §b"+target.getName()+" §7wurde §bdeaktiviert!");
                    }else{
                        target.setAllowFlight(true);
                        target.sendMessage(Var.pr+"§bFly §7wurde von §b"+p.getName()+"§7 für dich §baktiviert!");
                        p.sendMessage(Var.pr+"§bFly §7von §b"+target.getName()+" §7wurde §baktiviert!");
                }
                    return true;
                }
                p.sendMessage(Var.pr+"§7Bitte benutzte: §b/fly <User>");
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
