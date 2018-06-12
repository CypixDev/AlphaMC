package de.Cypix.Survival.MYSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MYSQL {

    private static String host;
    private static int port;
    private static String database;
    private static String passwd;
    private static String user;

    private static Connection con;

    public MYSQL(String host, int port, String database, String user, String passwd){
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.passwd = passwd;
    }

    public static boolean isConnected(){
        try {
            if(con.isValid(2)){
                return true;
            }else{
                Connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void Connect(){
        if(!isConnected()) {
            try{
                con = DriverManager.getConnection("jdbc:mysql://" + host+ ":" + port + "/" + database, user, passwd);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void createTable(){
        if(isConnected()){
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Survival (Player VARCHAR(100), UUID VARCHAR(100), Kills INT(100), Deaths(100));");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void update(String qry){
        if(isConnected()){
            try {
                con.createStatement().executeUpdate(qry);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static ResultSet getResultSet(String qry){
        if(isConnected()){
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
