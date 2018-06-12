package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.Manager.BanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDCheck extends Command {

    public CMDCheck(){
        super("check");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if(p.hasPermission("weakmc.ban")){
            if(args.length == 1){
                p.sendMessage(new ComponentBuilder("§bInfos über "+args[0]).create());
                if(BanManager.isBanned(args[0])) p.sendMessage(new ComponentBuilder("§cDer Spieler ist gebannt !").create());
                if(BanManager.isBanned(args[0])) p.sendMessage(new ComponentBuilder("Für: "+BanManager.getRemainingTime(args[0])).create());
                if(BanManager.isMuted(args[0])) p.sendMessage(new ComponentBuilder("§cDer Sieler ist gemuted !").create());
                if(BanManager.isMuted(args[0])) p.sendMessage(new ComponentBuilder("Für: "+ BanManager.getRemainingTime(args[0])).create());
                if(ProxyServer.getInstance().getPlayer(args[0]) != null) p.sendMessage(new ComponentBuilder("§aDer Spieler ist Online !").create());

            }
        }
    }
}