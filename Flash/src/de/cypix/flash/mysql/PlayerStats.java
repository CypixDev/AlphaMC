package de.cypix.flash.mysql;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerStats {

    public static HashMap<Player, Integer> won = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> played = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> checkpoints = new HashMap<Player, Integer>();


    Player p;

    public PlayerStats(Player p){
        this.p = p;
    }



    public void addDeath(){ deaths.put(p, deaths.get(p)+1);}
    public void addCheckPoint(){checkpoints.put(p, getCheckPoints(p)+1);}
    public void addGame(){played.put(p, played.get(p)+1);}
    public void addWin(){won.put(p, won.get(p)+1);}

    public int getWonGames(){return won.get(p);}
    public int getPlayedGames(){return played.get(p);}
    public int getCheckPoints(){return checkpoints.get(p);}
    public int getDeaths(){return deaths.get(p);}





    public static void saveStats(Player p){
        if(MYSQL.isConnected()){
            MYSQL.update("UPDATE flash SET Won='"+won.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE flash SET Deaths='"+deaths.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE flash SET CheckPoint='"+checkpoints.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE flash SET Games='"+played.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
        }
    }

    public static void saveallplayersStats(){
        for(Player p : Bukkit.getOnlinePlayers()){
            saveStats(p);
        }
    }

    public static void loadallplayersStats(){
        for(Player p : Bukkit.getOnlinePlayers()){
            loadStats(p);
        }
    }


    public static void addPlayer(Player p){
        if(!isInList(p)){
            MYSQL.update("INSERT INTO flash (Player , UUID, Won, Games, Deaths, CheckPoint, Time) VALUES ('"+p.getName()+"', '"+p.getUniqueId()+"', '0', '0', '0', '0', '0')");
        }else{

        }
    }

    public static void loadStats(Player p){

        won.put(p, getWonGames(p));
        played.put(p, getWonGames(p));
        checkpoints.put(p, getCheckPoints(p));
        deaths.put(p, getDeaths(p));
        p.sendMessage("§8➤ §7Deine §bStats §7wurden geladen");
    }
    private static int getDeaths(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Deaths");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getCheckPoints(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("CheckPoint");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }

    private static int getplayedGames(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Games");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getWonGames(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Won");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }

    private static boolean isInList(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            return set.next();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return false;

    }

    private static void updateName(Player p){
        MYSQL.update("UPDATE flash SET Player='"+p.getName()+"' WHERE UUID='"+p.getUniqueId()+"'");
    }
}