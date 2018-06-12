package de.cypix.ttt.cmd;

import de.cypix.ttt.Var;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStart implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("ttt.start")){
                if(args.length == 0){
                    if(Main.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState){
                        LobbyState ls = (LobbyState) Main.getInstance().getGameStateManager().getCurrentGameState();
                        if(ls.getLobbyCountdown().isRunning()){
                            if(ls.getLobbyCountdown().getSeconds() >= 10){
                                ls.getLobbyCountdown().setSeconds(10);
                                p.sendMessage(Var.pr+"Du hast den Countdown auf 10 sec gesetzt !");
                            }else p.sendMessage(Var.pr+"Die Zeit ist bereits unter 10 sec !");
                        }else p.sendMessage(Var.pr+"Es sind zu wenig Spieler auf dem Server.");
                    }else p.sendMessage(Var.pr+"Du bist nicht in der Lobby //edit ?!");
                }else {
                    p.sendMessage(Var.pr+"§7Bitte benutzte §b/start");



                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }
}
