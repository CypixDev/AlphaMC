package de.Cypix.Lobby.API;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class ActionBar {


    public static void sendActionBar(Player player, String Nachricht) {
        final String NachrichtNeu = Nachricht.replace("_", " ");
        String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
    }

}