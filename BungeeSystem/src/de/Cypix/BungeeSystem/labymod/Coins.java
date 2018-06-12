package de.Cypix.BungeeSystem.labymod;

import com.google.gson.JsonObject;
import net.labymod.serverapi.bungee.LabyModPlugin;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Coins {

    public static void updatePoints(ProxiedPlayer pp, int points ) {
        // Constructing the JsonObject we want to send to the client
        JsonObject pointsObject = new JsonObject();
        pointsObject.addProperty( "coins", points );

        // Sending the server message
        LabyModPlugin.getInstance().sendServerMessage( pp, "Coins", pointsObject );
    }

}
