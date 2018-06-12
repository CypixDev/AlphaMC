package de.Cypix.BungeeSystem.CMD;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDHelp extends Command {

    public CMDHelp(){
        super("help");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer)sender;
        if(p.hasPermission("BungeeSystem.Help")){
            p.sendMessage(new ComponentBuilder("§c/ban <Player> <reason> §6Bestrafe einen Spieler !").create());
            p.sendMessage(new ComponentBuilder("§c/unban <Player> §6erlasse die Strafe für einen Spieler !").create());
            p.sendMessage(new ComponentBuilder("§c/unmute <Player> §6um einen Spieler zu unmuten").create());
            p.sendMessage(new ComponentBuilder("§c/check <Player> §6überfrüfe ob ein Spieler gebannt/muted ist !").create());
        }else{
            p.sendMessage(new ComponentBuilder("§7---------§6HELP§7---------").create());
            p.sendMessage(new ComponentBuilder("§6/Party um eine Party mit deien Freunden zu machen !").create());
            p.sendMessage(new ComponentBuilder("§6/friend um alles über den Command zu erfahren !").create());
            p.sendMessage(new ComponentBuilder("§7---------§6HELP§7---------").create());
        }
    }
}
