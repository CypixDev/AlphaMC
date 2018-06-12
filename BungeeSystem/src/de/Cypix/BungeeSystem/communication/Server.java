package de.Cypix.BungeeSystem.communication;

import net.md_5.bungee.api.config.ServerInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {

    public static void send(String channel, String message, ServerInfo server){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(stream);

        try {
            output.writeUTF(channel);
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.sendData("info", stream.toByteArray());
    }

}
