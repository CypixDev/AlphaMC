package de.Cypix.Lobby.CMD;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Manager.BuildManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class CMDBuild implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("weakmc.build")){
                if(BuildManager.list.contains(p)){
                    BuildManager.list.remove(p);
                    p.sendMessage(Var.pr+"§8➤ §7Du kannst jetzt §cnicht §7mehr Bauen!");
                    p.setGameMode(GameMode.SURVIVAL);
                }else{
                    BuildManager.list.add(p);
                    p.sendMessage(Var.pr+"§8➤ §7Du kannst §ajetzt §7mehr Bauen!");
                    p.setGameMode(GameMode.CREATIVE);
                }
            }
        }

        return false;
    }
}
