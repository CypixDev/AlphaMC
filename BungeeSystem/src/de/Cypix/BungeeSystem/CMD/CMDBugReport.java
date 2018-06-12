package de.Cypix.BungeeSystem.CMD;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDBugReport extends Command {

    public CMDBugReport() {
        super("bugreport");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            if(args.length > 1){
                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";
                }
                ProxiedPlayer pp = (ProxiedPlayer) sender;
                pp.sendMessage(new ComponentBuilder("Dein Bug Report: "+message).create());
                pp.sendMessage(new ComponentBuilder("§7Danke für den §bReport§7, sollte der §7Report §aangenommen §7werden, bekommst du §bCoins §7als §bBelohnung§7!").create());

                for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()){
                    if(p.hasPermission("weakmc.bugreport")){
                        p.sendMessage(new ComponentBuilder("§7Der Spieler §b"+pp.getName()+" §7hat einen §cBug-Report §7abgesendet: ").create());
                        p.sendMessage(new ComponentBuilder("§bBug: §7"+message).create());
                    }
                }

            }else{
                ((ProxiedPlayer) sender).sendMessage(new ComponentBuilder("§7Bitte Benutze §b/bugreport <Der Bug>").create());
            }
        }
    }
}
