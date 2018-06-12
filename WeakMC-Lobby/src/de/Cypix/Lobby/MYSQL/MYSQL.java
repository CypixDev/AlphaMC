package de.Cypix.Lobby.MYSQL;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MYSQL {

    private static String host, database, user, passwd;
    private static int port;

    private static Connection con;


    public MYSQL(String host, int port, String database, String user, String passwd){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.passwd = passwd;

    }

    public static void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://"+ host +":"+port+"/"+database, user, passwd);
            Bukkit.getConsoleSender().sendMessage("mysql verbindung aufgebaut ");
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }

    public void createTables(){
        if(isConnected()){
            try {

                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS loc (UUID VARCHAR (100), World VARCHAR (100), X VARCHAR (100), Y VARCHAR (100), Z VARCHAR (100), Yaw VARCHAR (100), Pitch VARCHAR (100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS animation (UUID VARCHAR(100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS scoreboard (UUID VARCHAR(100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS animation_layout (UUID VARCHAR(100), layout INT(100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS shop_pets (UUID VARCHAR (100), Pig VARCHAR(100), Sheep VARCHAR (100), Cow VARCHAR (100), Chicken VARCHAR (100), Wolf VARCHAR (100), Mooshroom VARCHAR (100), Ocelot VARCHAR (100), Horse VARCHAR (100), Rabbit VARCHAR (100), Villager VARCHAR (100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS pets (UUID VARCHAR (100), Pet VARCHAR (100), Name VARCHAR (100), Level INT (100), Color VARCHAR (100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Shop (UUID VARCHAR (100), ID INT(100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS effect(UUID VARCHAR (100));");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS teleport(UUID VARCHAR (100))");
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS save(UUID VARCHAR (100), Head INT (100), Boots INT (100), Pet INT (100), GadGet INT (100));");

            }catch(SQLException e2){
                e2.printStackTrace();
            }


        }
    }

    public static ResultSet getResult(String qry){
        if(isConnected()){
            try{
                return con.createStatement().executeQuery(qry);
            }catch(SQLException e3){
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static void update(String qry){
        if(isConnected()){
            try{
                con.createStatement().executeUpdate(qry);
                Bukkit.getConsoleSender().sendMessage("Update bei MYSQL ausgeführt !");
            }catch (SQLException e4){
                e4.printStackTrace();
            }
        }
    }


    public static boolean isConnected(){
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



}
