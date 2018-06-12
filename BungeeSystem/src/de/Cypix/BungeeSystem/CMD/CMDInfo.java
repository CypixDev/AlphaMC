package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.communication.Server;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDInfo extends Command {


    public CMDInfo() {
        super("info");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer pp = (ProxiedPlayer)commandSender;
        pp.sendMessage(new TextComponent("Hey"));
        Server.send("data", "Das weiß nur ich !", pp.getServer().getInfo());
    }
}
