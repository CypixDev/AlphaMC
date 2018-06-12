package de.cypix.alphaapi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {

    private static Connection con;

    private static String host;
    private static int port;
    private static String database;
    private static String user;
    private static String passwd;

    public Mysql(String host, int port, String database, String user, String passwd){
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
            System.out.println("Die verbindung zur Mysql wurde hergestellt !");
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }

    public static boolean isConnected(){
        try {
            if(con.isValid(200)){
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
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS KnockBack_Stats(UUID VARCHAR(100), Kills INT(100), Deaths INT (100), Points INT(100), KillStreak INT (100), Boosts INT (100));");
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS KnockBack_Kits(UUID VARCHAR(100), Kit_ID INT(100));");
                //Coins
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS Coins(UUID VARCHAR(100), Coins INT(100))");
                //Options
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS Options_Lobby(UUID VARCHAR(100), Options INT(100), Value INT(100))");
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS Options_Generally(UUID VARCHAR(100), Options INT(100), Value VARCHAR(100))");
                //BuildFFA
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS BuildFFA_Stats(UUID VARCHAR(100), Kills INT(100), Deaths INT(100), Points INT(100), KillStreak INT(100), Blocks INT (100))");
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS BuildFFA_Inv(UUID VARCHAR(100), Item0 INT(100), Item1 INT(100), Item2 INT(100), Item3 INT(100), Item4 INT(100), Item5 INT(100), Item6 INT(100), Item7 INT(100), Item8 INT(100))");
                //GunGame
                con.createStatement().execute("CREATE TABLE IF NOT EXISTS GunGame_Stats(UUID VARCHAR(100), Kills INT(100), Deaths INT(100), Points INT(100), Level INT(100), LevelRecord INT(100))");
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
                System.out.println("Mysql Result -> "+qry);
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
        System.out.println("Update Mysql: "+qry);
        if(isConnected()){
            try{
                con.createStatement().executeUpdate(qry);
            }catch(SQLException e){
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




}

