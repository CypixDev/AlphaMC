package de.Cypix.BungeeSystem.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlphaMysql {
    private static Connection con;

    private static String host;
    private static int port;
    private static String database;
    private static String user;
    private static String passwd;

    public AlphaMysql(String host, int port, String database, String user, String passwd){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.passwd = passwd;
        connect();
    }
    public static void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, passwd);
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }

    public static boolean isConnected(){
        try {
            if(con.isValid(2)){
                return true;
            }else{
                con.close();
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTable(){
        if(isConnected()){
            try {
                //UUIDFatcher
                //TODO: BungeeCord input
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS UUID(Name VARCHAR(100), Player_Name VARCHAR (100), Player_UUID VARCHAR (100));");
                //KnockBack
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS KnockBack_Stats(UUID VARCHAR(100), Kills INT(100), Deaths INT (100), KillStreaks INT (100), Boosts INT (100));");
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS KnockBack_Kits(UUID VARCHAR(100), Kit_ID INT(100));");
                //Coins
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS Coins(UUID VARCHAR(100), Coins INT (100))");
            }catch(SQLException e1){
                e1.printStackTrace();
            }

        }else{
            connect();
        }
    }

    public static ResultSet getResult(String qry){
        if(isConnected()){
            try {
                return con.createStatement().executeQuery(qry);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            connect();
        }
        return null;
    }

    public static void update(String qry){
        if(isConnected()){
            try{
                con.createStatement().executeUpdate(qry);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            connect();
        }
    }
}
