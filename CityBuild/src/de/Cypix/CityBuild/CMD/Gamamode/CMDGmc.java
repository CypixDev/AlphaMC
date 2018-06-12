package de.Cypix.CityBuild.CMD.Gamamode;

import de.Cypix.CityBuild.File.Var;
import javafx.print.PageLayout;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDGmc implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.gm")){
                if(args.length == 0){
                    p.sendMessage(Var.pr+"Du bist jetzt im §bCreative");
                    p.setGameMode(GameMode.CREATIVE);
                }
                if(args.length == 1){
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null){
                        target.setGameMode(GameMode.CREATIVE);
                        target.sendMessage(Var.pr+"Dein §bGame-Modus §7wurde von §b"+p.getName()+" auf §bCreative §7gesetzt!");
                    }
                }
            }else{
                p.sendMessage(Var.noperm);
            }
        }
        return false;
    }
}
