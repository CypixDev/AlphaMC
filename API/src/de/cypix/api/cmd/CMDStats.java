package de.cypix.api.cmd;

import de.cypix.api.stats.FFAStats;
import de.cypix.api.stats.FlashStats;
import de.cypix.api.stats.KnockBackStats;
import de.cypix.api.stats.TTTStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStats implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("KnockBack")){
                    new KnockBackStats(p).sendStatsMessage();
                    return true;
                }
                if(args[0].equalsIgnoreCase("ttt")){
                    new TTTStats(p).sendStatsMessage();
                    return true;
                }
                if(args[0].equalsIgnoreCase("ffa")){
                    new FFAStats(p).sendStatsMessage();
                    return true;
                }
                if(args[0].equalsIgnoreCase("flash")){
                    new FlashStats(p).sendStatsMessage();
                    return true;
                }
            }

            p.sendMessage("§8➤ §7Bitte benutzte: §8/Stats §b<KnockBack/TTT/FFA/Flash>");
        }
        return false;
    }
}
