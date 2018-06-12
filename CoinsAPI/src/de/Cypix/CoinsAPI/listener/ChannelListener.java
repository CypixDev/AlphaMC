package de.Cypix.CoinsAPI.listener;

import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ChannelListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player p, byte[] message) {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subChannel = stream.readUTF();
            if(subChannel.equals("data")){
                String input = stream.readUTF();
                JsonObject obj = new JsonObject().getAsJsonObject(input);
                p.sendMessage(obj.get("Test").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
