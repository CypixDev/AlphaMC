package de.Cypix.BungeeSystem.labymod;

import com.google.gson.JsonObject;
import net.labymod.serverapi.bungee.LabyModPlugin;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class SendReport {

    public static void sendReport(ProxiedPlayer pp, String reported, String reporter, String Reason, String Server ) {
        // Constructing the JsonObject we want to send to the client
        JsonObject report = new JsonObject();
        //set in JsonObject
        report.addProperty("Reporter", reporter);
        report.addProperty("Reported", reported);
        report.addProperty( "Reason", Reason);
        report.addProperty("Server", Server);

        // Sending the server message
        LabyModPlugin.getInstance().sendServerMessage( pp, "Report", report );
    }

}
