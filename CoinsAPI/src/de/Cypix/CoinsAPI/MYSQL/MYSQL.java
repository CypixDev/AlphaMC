package de.Cypix.CoinsAPI.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class MYSQL {

    private static String host;
    private static String user;
    private static String passwd;
    private static int port;
    private static String database;
    private static Connection con;


    public MYSQL(String host,int port, String user, String passwd, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.passwd = passwd;
        this.database = database;

    }
    public MYSQL() {
		/*this.host = data;
		this.port = data;
		this.user = data;
		this.passwd = data;
		this.database = data;*/

    }

    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host+ ":" + port + "/" + database, user, passwd);
            Bukkit.getConsoleSender().sendMessage("§aDie Verbindung zur MYSQL datenbank wurde Erfolgreich Hergestellt !");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static boolean isConnecte(){
        try {
            if(con.isValid(2)){
                return true;
            }else{
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void createTable() {
        if(isConnecte()) {
            try {

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Coins (Player VARCHAR(100), UUID VARCHAR(100), Coins INT (100));");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void update(String qry) {
        if(isConnecte()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            connect();
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String qry) {
        if(isConnecte()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            connect();
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
