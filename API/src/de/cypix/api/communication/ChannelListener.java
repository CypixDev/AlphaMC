package de.cypix.api.communication;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ChannelListener implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(bytes));

        try {
            String subChannel = stream.readUTF();
            if(subChannel.equals("data")){
                String input = stream.readUTF();
                player.sendMessage(" ");
                player.sendMessage("Server >> Info vom Bungee");
                player.sendMessage("Server >> Info: "+input);

                notifyAll();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
