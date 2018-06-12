package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.Var;
import de.dytanic.cloudnet.bridge.event.proxied.ProxiedServerAddEvent;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDTc extends Command {
    public CMDTc() {
        super("tc");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer p = (ProxiedPlayer)sender;
            if(p.hasPermission("weakmc.tc")){
                if(args.length > 0){
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }
                    for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()){
                        if(pp.hasPermission("weakmc.tc")){
                            pp.sendMessage("§7§bTeamChat §b§l➲ §r§b"+p.getName()+"§7: "+message);
                        }
                    }
                }

            }else{
                Var.noperm(p);
            }
        }
    }
}
