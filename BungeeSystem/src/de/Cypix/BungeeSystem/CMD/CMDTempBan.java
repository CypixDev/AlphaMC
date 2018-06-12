package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.API.UUIDFetcher;
import de.Cypix.BungeeSystem.Manager.BanManager;
import de.Cypix.BungeeSystem.Manager.BanType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Random;

public class CMDTempBan extends Command {

    public CMDTempBan(){
        super("tempban");
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if(p.hasPermission("weakmc.ban")){
            if(args.length >= 3){

                String timee = args[1];
                int zeit = Integer.valueOf(args[2]);

                String used = "";
                long time = 0;

                if(timee.equalsIgnoreCase("sec")) used = "sec";
                if(timee.equalsIgnoreCase("min")) used = "min";
                if(timee.equalsIgnoreCase("hour")) used = "hour";
                if(timee.equalsIgnoreCase("day")) used = "day";
                if(timee.equalsIgnoreCase("week")) used = "week";
                if(timee.equalsIgnoreCase("year")) used = "year";

                if(used == ""){
                    p.sendMessage(new ComponentBuilder("§8➤ §7Gib eine gültige §bZeit §7an").create());
                    return;
                }
                if(used == "sec")time = zeit;

                if(used == "min")time = (zeit*60);

                if(used == "hour")time = (zeit*3600);

                if(used == "day")time = (zeit*86400);

                if(used == "week") time = (zeit*604800);

                if(used == "year") time = (zeit*31536000);

                Random r = new Random();

                String reason = "";

                for(int i = 3;i<args.length;i++){
                    reason = reason+args[i];
                }

                if(BanManager.isinList(args[0])) p.sendMessage(new ComponentBuilder("§8➤ §7Der §bBan §7wird nun überschrieben").create());
                if(BanManager.isinList(args[0])) BanManager.unBan(args[0]);

                BanManager.ban(args[0], UUIDFetcher.getUUID(args[0]).toString(), r.nextInt(99999), BanType.TEMPBAN, time, p.getName(), reason);




                return;
            }
            if(args.length == 1){
                p.sendMessage(new ComponentBuilder("§8➤ §bBenutze §8| §7 /tempban <player> <year/month/week/hour/min/sec> <time> <Reason>").create());
            }else {
                p.sendMessage(new ComponentBuilder("§7Falscher §bSyntax").create());
            }
        }
    }
}
