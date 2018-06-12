package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.Manager.BanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDUnMute extends Command {

    public CMDUnMute(){
        super("unmute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if(p.hasPermission("weakmc.ban")){
            if(args.length == 1){
                BanManager.unBan(args[0]);
                p.sendMessage(new ComponentBuilder("Spieler wurde unmuted !").create());
            }
        }
    }
}
