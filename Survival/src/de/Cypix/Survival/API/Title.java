package de.Cypix.Survival.API;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;


public class Title {


    public static void sendTitle(Player p, Integer fadeIn, Integer stay, Integer fadeOut, String Title, String subTitle) {
        PlayerConnection connection = ((CraftPlayer) p).getHandle().playerConnection;

        PacketPlayOutTitle PacketPlayOutTime = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket(PacketPlayOutTime);
        if(subTitle != null) {
            IChatBaseComponent TitleSub = ChatSerializer.a("{\"text\": \""+subTitle+"\"}");
            PacketPlayOutTitle PacketPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, TitleSub);
            connection.sendPacket(PacketPlayOutSubTitle);
        }
        if(Title != null) {
            IChatBaseComponent Titlee = ChatSerializer.a("{\"text\": \""+Title+"\"}");
            PacketPlayOutTitle PacketPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, Titlee);
            connection.sendPacket(PacketPlayOutTitle);
        }

    }

}
