package de.Cypix.SGFFA.CMD;

import de.Cypix.SGFFA.MYSQL.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDStats implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                PlayerStats ps = new PlayerStats(p);

                double kills = ps.getKills();
                double death = ps.getDeaths();
                double kd = kills/death;

                p.sendMessage("§7§l┏╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┓");
                p.sendMessage(" ");
                p.sendMessage("§8➤ §7Kills §8× §b"+ps.getKills());
                p.sendMessage("§8➤ §7Tode §8× §b"+ps.getDeaths());
                p.sendMessage("§8➤ §7KD §8× §b"+Math.round(kd));
                p.sendMessage("§8➤ §7Höchste Killstreak §8× §b"+ps.getKillStreak());
                p.sendMessage(" ");
                p.sendMessage("§7§l┗╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┛");
                return true;
            }
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null){
                    PlayerStats ps = new PlayerStats(target);

                    double kills = ps.getKills();
                    double death = ps.getDeaths();
                    double kd = kills/death;

                    kd = kd;

                    p.sendMessage("§7§l┏╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┓");
                    p.sendMessage(" ");
                    p.sendMessage("§8➤ §7Kills §8× §b"+ps.getKills());
                    p.sendMessage("§8➤ §7Tode §8× §b"+ps.getDeaths());
                    p.sendMessage("§8➤ §7KD §8× §b"+Math.round(kd));
                    p.sendMessage("§8➤ §7Höchste Killstreak §8× §b"+ps.getKillStreak());
                    p.sendMessage(" ");
                    p.sendMessage("§7§l┗╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┛");
                    return true;
                }else{
                    //offline stats ohne den PlayerStats constructor
                    p.sendMessage("offline !");
                }
            }
        }
        return false;
    }
}