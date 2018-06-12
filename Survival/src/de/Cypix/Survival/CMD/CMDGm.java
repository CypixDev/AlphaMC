package de.Cypix.Survival.CMD;

import de.Cypix.Survival.FileManager.Var;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDGm implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("Survival.gm")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("0"))p.setGameMode(GameMode.SURVIVAL);
                    if(args[0].equalsIgnoreCase("1"))p.setGameMode(GameMode.CREATIVE);
                    if(args[0].equalsIgnoreCase("2"))p.setGameMode(GameMode.ADVENTURE);
                    if(args[0].equalsIgnoreCase("3"))p.setGameMode(GameMode.SPECTATOR);
                    p.sendMessage("Du bist jetzt im "+p.getGameMode().toString()+" Modus !");
                    return true;
                }
                if(args.length == 2){
                    if(args[1].equalsIgnoreCase("all")){
                        for(Player pp : Bukkit.getOnlinePlayers()){
                            if(args[0].equalsIgnoreCase("0"))pp.setGameMode(GameMode.SURVIVAL);
                            if(args[0].equalsIgnoreCase("1"))pp.setGameMode(GameMode.CREATIVE);
                            if(args[0].equalsIgnoreCase("2"))pp.setGameMode(GameMode.ADVENTURE);
                            if(args[0].equalsIgnoreCase("3"))pp.setGameMode(GameMode.SPECTATOR);
                            pp.sendMessage("Es haben alle den GameMode "+pp.getGameMode().toString()+" erhalten !");
                        }
                        p.sendMessage("§cDu hast allen Spielern auf diesem Server den Gamemode "+p.getGameMode().toString()+" gegeben !");
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null){
                        if(args[0].equalsIgnoreCase("0"))target.setGameMode(GameMode.SURVIVAL);
                        if(args[0].equalsIgnoreCase("1"))target.setGameMode(GameMode.CREATIVE);
                        if(args[0].equalsIgnoreCase("2"))target.setGameMode(GameMode.ADVENTURE);
                        if(args[0].equalsIgnoreCase("3"))target.setGameMode(GameMode.SPECTATOR);

                        p.sendMessage("Der Spieler "+target.getDisplayName()+" hat den GameMode erhalten !");
                        target.sendMessage("Du hast den GameMode "+target.getGameMode().toString()+" von "+p.getDisplayName()+" bekommen !");
                    }else{
                        p.sendMessage(Var.offline);
                    }
                    return true;
                }
                p.sendMessage("§cUse: /gm <0/1/2/3> <player/all>");
            }
        }


        return false;
    }
}
