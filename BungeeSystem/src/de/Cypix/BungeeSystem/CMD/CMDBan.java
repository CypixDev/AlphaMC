package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.Manager.BanManager;
import de.Cypix.BungeeSystem.Manager.BanType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Random;

public class CMDBan extends Command {

    public CMDBan(){
        super("ban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if(p.hasPermission("weakmc.ban")){
            if(args.length == 2){
                Random r = new Random();
                ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(args[0]);
                if(pp != null){
                    if(BanManager.isinList(pp.getUniqueId())) p.sendMessage(new ComponentBuilder("§8➤ §7Der §bBan §7wird nun überschrieben").create());
                    if(BanManager.isinList(pp.getUniqueId())) BanManager.unBan(pp.getName());
                    if(args[1].equalsIgnoreCase("1")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.PERMA, 1, p.getName(), "Hausverbot");
                        pp.disconnect(new ComponentBuilder("§8➤ §7Du wurdest vom §bWeakMC §7Netzwerk §bgebannt").create());
                        return;
                    }
                    if(args[1].equalsIgnoreCase("2")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.TEMPBAN, 2592000, p.getName(), "Hacking");
                        pp.disconnect(new ComponentBuilder("§8➤ §7Du wurdest vom §bWeakMC §7Netzwerk §bgebannt").create());
                        return;
                    }
                    if(args[1].equalsIgnoreCase("3")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.PERMA, -1, p.getName(), "Drohung");
                        pp.disconnect(new ComponentBuilder("§8➤ §7Du wurdest vom §bWeakMC §7Netzwerk §bgebannt").create());
                        return;
                    }
                    if(args[1].equalsIgnoreCase("4")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.TEMPBAN, 604800, p.getName(), "Werbung");
                        pp.sendMessage(new ComponentBuilder("§8➤ §7Du wurdest §bgemuted").create());
                        return;
                    }
                    if(args[1].equalsIgnoreCase("5")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.TEMPMUTE, 1296000, p.getName(), "Rassismus");
                        pp.sendMessage(new ComponentBuilder("§8➤ §7Du wurdest §bgemuted").create());
                        return;
                    }
                    if(args[1].equalsIgnoreCase("6")) {
                        BanManager.ban(pp, pp.getUniqueId().toString(), r.nextInt(99999), BanType.TEMPMUTE, 604800, p.getName(), "Beleidigung");
                        pp.sendMessage(new ComponentBuilder("§8➤ §7Du wurdest §bgemuted").create());
                        return;
                    }

                }else{




                }
            }
            if(args.length != 3){
                p.sendMessage(new ComponentBuilder("§7§l┏╋§7§m§l----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┓").create());
                p.sendMessage(new ComponentBuilder(" ").create());
                //nope//null !
                p.sendMessage(new ComponentBuilder("§8➤ §4Permanent §8| §b1").create());

                p.sendMessage(new ComponentBuilder("§8➤ §7Hacking §7× §b30 Tage §8| §b2").create());
                p.sendMessage(new ComponentBuilder("§8➤ §7Drohung §7× §4Permanent §8| §b3").create());
                p.sendMessage(new ComponentBuilder("§8➤ §7Werbung §7× §7Mute §8➤ §b7 §7Tage §8| §b4").create());
                p.sendMessage(new ComponentBuilder("§8➤ §7Rassismus §7× §7Mute §8➤ §b15 §7Tage §8| §b5").create());
                p.sendMessage(new ComponentBuilder("§8➤ §7Beleidigung §7× §7Mute §8➤ §b7 §7Tage §8| §b6").create());
                p.sendMessage(new ComponentBuilder(" ").create());
                p.sendMessage(new ComponentBuilder("§7§L┗╋§7§m§l----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┛").create());
                return;
            }else{
                p.sendMessage(new ComponentBuilder("§7Falsche §bBenutzung").create());
                return;
            }
        }else{
            p.sendMessage(new ComponentBuilder("§8➤§7Dafür hast du keine §bRechte").create());
        }
    }
}
