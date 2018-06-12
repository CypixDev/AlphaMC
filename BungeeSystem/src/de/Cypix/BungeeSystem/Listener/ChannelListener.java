package de.Cypix.BungeeSystem.Listener;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.*;
import java.lang.reflect.Proxy;

public class ChannelListener implements Listener {


    @EventHandler
    public void onPluginMessage(PluginMessageEvent e){
        if(!e.getTag().equalsIgnoreCase("BungeeCord"))return;

        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(e.getData()));

        try {
            String channel = stream.readUTF();
            if(channel.equals("data")){
                String input = stream.readUTF();
                sendToServer("info", "Player Cypix("+input+") ist geil", BungeeCord.getInstance().getServerInfo("Lobby-1"));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void sendToServer(String channel, String message, ServerInfo server){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(stream);
        JsonObject obj = new JsonObject();
        obj.addProperty("Test", "Erfolgreich");
        String outputString = obj.getAsString();

        try{
            output.writeUTF(channel);
            output.writeUTF(outputString);
        }catch (IOException e1){
            e1.printStackTrace();
        }
        server.sendData("info", stream.toByteArray());
    }

}
