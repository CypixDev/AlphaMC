package de.Cypix.BungeeSystem.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MYSQL {

    private static Connection con;

    private static String host;
    private static int port;
    private static String database;
    private static String user;
    private static String passwd;

    public MYSQL(String host, int port, String database, String user, String passwd){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.passwd = passwd;
    }
    public static void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, passwd);
            //.getInstance().getI.getConsoleSender().sendMessage("§aDie Verbindung zur MYSQL datenbank wurde Erfolgreich Hergestellt !");
        }catch(SQLException e1){
            e1.printStackTrace();
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

    public void createTable(){
        if(isConnected()){
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Ban (Player VARCHAR(100), UUID VARCHAR(100) ,Id INT(100) , Type INT(100) , Time VARCHAR (100), Operator VARCHAR(100), Reason VARCHAR (100));");
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

                