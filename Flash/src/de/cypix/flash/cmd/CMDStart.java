package de.cypix.flash.cmd;

import de.cypix.flash.gamestates.LobbyState;
import de.cypix.flash.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("flash.start")){
                if(args.length == 0){
                    if(Main.getInstace().getGameStatemanager().getCurrentGameState() instanceof LobbyState){
                        LobbyState ls = (LobbyState) Main.getInstace().getGameStatemanager().getCurrentGameState();
                        if(ls.getLobbyCountdown().isRunning()){
                            if(ls.getLobbyCountdown().getSeconds() >= 10){
                                ls.getLobbyCountdown().setSeconds(10);
                                p.sendMessage(Main.pr+"Du hast den Countdown auf 10 sec gesetzt !");
                            }else p.sendMessage(Main.pr+"Die Zeit ist bereits unter 10 sec !");
                        }else p.sendMessage(Main.pr+"Es sind zu wenig Spieler auf dem Server.");
                    }else p.sendMessage(Main.pr+"Du bist nicht in der Lobby //edit ?!");
                }else p.sendMessage(Main.pr+"§7Bitte benutzte §b/start");
            }else p.sendMessage(Main.noperm);
        }
        return false;
    }
}
