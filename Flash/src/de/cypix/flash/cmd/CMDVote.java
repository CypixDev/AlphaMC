package de.cypix.flash.cmd;

import de.cypix.flash.manager.VoteManager;
import javafx.print.PageLayout;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDVote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            p.openInventory(VoteManager.getVoteInv());
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("list")){
                    p.sendMessage(VoteManager.getMap().toString());
                }
            }
        }
        return false;
    }
}
