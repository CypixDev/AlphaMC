package de.cypix.ttt.api;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {

    public static void sendActionBar(Player p, String msg){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent chat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(chat, (byte) 2);
        connection.sendPacket(packetPlayOutChat);
    }

    public static void sendTitle(Player p, String title, String subtitle, int fadein, int stay, int fadeout){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        IChatBaseComponent Ititle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        IChatBaseComponent Isub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
        PacketPlayOutTitle titletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Ititle, fadein, stay, fadeout);
        PacketPlayOutTitle subtitletime = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, Isub);
        PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, Ititle);
        PacketPlayOutTitle subPacket = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, Isub );
        connection.sendPacket(titletime);
        connection.sendPacket(subtitletime);
        connection.sendPacket(titlePacket);
        connection.sendPacket(subPacket);



    }

}
