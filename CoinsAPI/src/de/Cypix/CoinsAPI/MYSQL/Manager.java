package de.Cypix.CoinsAPI.MYSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import net.labymod.serverapi.bukkit.LabyModPlugin;
import org.bukkit.entity.Player;

public class Manager {

    private Player p;

    public static HashMap<Player, Integer> coins = new HashMap<>();

    public Manager(Player p) {
        this.p = p;
    }

    public void addPlayer() {
        if(!isinList()) {
            String uuid = p.getUniqueId().toString();
            MYSQL.update("INSERT INTO Coins (Player , UUID, Coins ) VALUES ('"+p.getName()+"', '"+uuid+"', '0');");
        }
        coins.put(p, getCoins());
    }
    public boolean isinList() {
        String uuid = p.getUniqueId().toString();
        ResultSet set = MYSQL.getResult("SELECT * FROM Coins WHERE UUID='"+uuid+"'");
        try {
            return set.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public static boolean isinListPlayer(String player) {

        ResultSet set = MYSQL.getResult("SELECT * FROM Coins WHERE Player='"+player+"'");
        try {
            return set.next();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    public int getCoins() {
        String uuid = p.getUniqueId().toString();
        if(isinList()) {
            ResultSet rs = MYSQL.getResult("SELECT * FROM Coins WHERE UUID='"+uuid+"'");
            try {
                while(rs.next()) {
                    return rs.getInt("Coins");
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }else {
            addPlayer();
        }
        return 0;
    }

    public void setCoins(Integer value) {
        String uuid = p.getUniqueId().toString();
        if(value < 0) {
            value = 0;
        }
        if(isinList()) {
            MYSQL.update("UPDATE Coins SET Coins='"+value+"' WHERE UUID='"+uuid+"'");
            updatePoints(p, value);
        }else {
            addPlayer();
        }
    }



    public static void setCoinsPlayer(String player, Integer value) {
        if(value < 0) {
            value = 0;
        }
        if(isinListPlayer(player)) {
            MYSQL.update("UPDATE Coins SET Coins='"+value+"' WHERE Player='"+player+"'");
        }
    }

    public static int getCoinsPlayer(String player) {
        if(isinListPlayer(player)) {
            ResultSet rs = MYSQL.getResult("SELECT * FROM Coins WHERE Player='"+player+"'");
            try {
                while(rs.next()) {
                    return rs.getInt("Coins");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static void updatePoints(Player pp, int points ) {
        JsonObject pointsObject = new JsonObject();
        pointsObject.addProperty( "coins", points );

        LabyModPlugin.getInstance().sendServerMessage( pp, "Coins", pointsObject );
    }



}
