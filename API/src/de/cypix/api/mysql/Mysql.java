package de.cypix.api.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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


