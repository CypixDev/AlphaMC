package de.cypix.alphaapi.stats;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.uuidfatcher.UUID;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStats implements CommandExecutor  {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getServer().startsWith("Lobby")){
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("knockback")){
                        AlphaAPI.getInstance().getStatsPlayer(p).getKnockBackStats().sendStats();
                        return true;
                    }
                }
                if(args.length == 2){
                    if(Bukkit.getPlayer(args[1]) != null){
                        if(args[0].equalsIgnoreCase("knockback")) AlphaAPI.getInstance().getStatsPlayer(Bukkit.getPlayer(args[1])).getKnockBackStats().sendStats(p);
                        if(args[0].equalsIgnoreCase("buildffa")) AlphaAPI.getInstance().getStatsPlayer(Bukkit.getPlayer(args[1])).getBuildFFAStats().sendStats(p);
                        return true;
                    }else{
                        if(UUID.isRegistert(args[1])){
                            OfflineStatsPlayer osp = new OfflineStatsPlayer(UUID.getUUID(args[1]));
                            osp.getKnockBackStats().sendStats(p);

                        }
                    }
                }
                p.sendMessage("§cUse: /stats knockback/buildffa <player>");
                return true;
            }
        }
        return false;
    }
}
