package de.Cypix.BungeeSystem.CMD;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDPing extends Command {

    public CMDPing() {
        super("ping");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if(args.length == 0){
                p.sendMessage(new ComponentBuilder("§7Dein §bPing §7beträgt: §b"+p.getPing()+"ms").create());
            }else if(args.length == 1){
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if(target != null){
                    p.sendMessage(new ComponentBuilder("§b"+target.getName()+" §7hat einen Ping von §b"+target.getPing()+"ms").create());
                }else{
                    p.sendMessage(new ComponentBuilder("§7Der Spieler ist nicht §bOnline").create());
                }
            }
        }else{
            sender.sendMessage(new ComponentBuilder("Ka was für ein Ping du hast villeicht 0 !?").create());
        }
    }
}
