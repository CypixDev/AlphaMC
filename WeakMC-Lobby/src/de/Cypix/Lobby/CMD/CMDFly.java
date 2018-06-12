package de.Cypix.Lobby.CMD;

import de.Cypix.Lobby.Files.Var;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMDFly implements CommandExecutor {

    public static List<Player> list = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("weakmc.fly")){
                if(list.contains(p)){
                    p.setAllowFlight(false);
                    p.sendMessage(Var.pr+"§b§lFly §r§7ist jetzt §c§lDeaktiviert !");
                    list.remove(p);
                }else{
                    p.setAllowFlight(true);
                    p.sendMessage(Var.pr+"§b§lFly §r§7ist jetzt §a§lAktiviert !");
                    list.add(p);
                }
            }
        }

        return false;
    }
}
