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

public class CMDPay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 2){
                try{
                    Player target = Bukkit.getPlayer(args[0]);
                    int mon = Integer.valueOf(args[1]);
                    if(target != null){
                        MoneyManager p1 = new MoneyManager(p);
                        MoneyManager p2 = new MoneyManager(target);
                        if(p1.remove(mon)){
                            p.sendMessage(Var.pr+"§7Du hast "+target.getName()+" §b"+mon+" §7bezahlt");
                            target.sendMessage(Var.pr+"§7Du hast von "+p.getName()+" §b"+mon+" §7bekommen");
                            p2.add(mon);
                        }else{
                            if(p.hasPermission("citybuild.pay.unlimeted")){
                                p.sendMessage(Var.pr+"§7Du hast "+target.getName()+" §b"+mon+" §7bezahlt");
                                target.sendMessage(Var.pr+"§7Du hast von "+p.getName()+" §b"+mon+" §7bekommen");
                                p2.add(mon);
                                return true;
                            }
                            p.sendMessage(Var.pr+"§7Du hast zu wenig §bMoney");
                        }
                    }else{
                        String uuid = UUIDFetcher.getUUID(args[0]).toString();
                        if(PlayerFile.isRegistert(uuid)){
                            MoneyManager p1 = new MoneyManager(p);
                            if(p1.remove(mon)) {
                                PlayerFile.save(uuid, "Money", mon);
                                p.sendMessage(Var.pr+"§7Du hast " + args[0] + " §b" + mon + " §7Bezahlt.");
                            }else{
                                if(p.hasPermission("citybuild.pay.unlimeted")){
                                    PlayerFile.save(uuid, "Money", mon);
                                    p.sendMessage(Var.pr+"§7Du hast " + args[0] + " §b" + mon + " §7Bezahlt.");
                                    return true;
                                }
                                p.sendMessage(Var.pr+"§7Du hast zu wenig §bMoney!");
                            }
                        }else{
                            p.sendMessage(Var.pr+"§7Der Spieler war noch §bnie §7auf dem Server!");
                        }
                    }
                    return true;
                }catch(NumberFormatException e1){

                }
            }
            p.sendMessage(Var.pr+"§7Bitte benutze §b/pay <user> <balance>");
        }
        return false;
    }
}
