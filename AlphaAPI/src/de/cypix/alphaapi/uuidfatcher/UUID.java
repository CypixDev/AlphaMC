package de.cypix.alphaapi.uuidfatcher;

import de.cypix.alphaapi.mysql.Mysql;
import io.netty.handler.codec.http.HttpContentEncoder;
import org.bukkit.entity.Player;
import org.sqlite.SQLiteErrorCode;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UUID {

    public static String getUUID(String playername){
        ResultSet rs = Mysql.getResult("SELECT * FROM UUID WHERE Name='"+playername.toLowerCase()+"'");
        try {
            return rs.getString("Player_UUID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertIntoList(Player player){
        Mysql.update("INSERT INTO UUID(Name, Player_Name , Player_UUID)" +
                " VALUES ('"+player.getName().toLowerCase()+"', '"+player.getName()+"', '"+player.getUniqueId()+"')");
    }
    public static boolean isRegistert(String player){
        ResultSet rs = Mysql.getResult("SELECT * FROM UUID WHERE Name='"+player.toLowerCase()+"'");
        try{ if(rs.next())return true; }catch(SQLException e1){ e1.printStackTrace(); }
        return false;
    }
}
