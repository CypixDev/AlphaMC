package de.Cypix.BungeeSystem.CMD;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDBroadCast extends Command {

    String prefix = "§bWeakMC §7| ";

    public CMDBroadCast() {
        super("broadcast");
    }


    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer pp = (ProxiedPlayer) sender;

            if (args.length > 0) {

                String message = "";
                for (int i = 0; i < args.length; i++) {
                    message = message + args[i] + " ";

                }

                message = ChatColor.translateAlternateColorCodes('&', message);

                ProxyServer.getInstance().broadcast(prefix + message);

            } else {

                pp.sendMessage("&7Bitte benutze §b/broadcast [Nachricht]");

            }

        } else {

            sender.sendMessage("§cDu musst ein Spieler sein!");

        }

    }
}