package de.Cypix.BungeeSystem;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Var {
    public static void noperm(ProxiedPlayer p) {
        p.sendMessage(new ComponentBuilder("§7Dazu hast du keine §bRechte!").create());
    }
}
