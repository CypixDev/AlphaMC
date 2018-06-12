package de.Cypix.FFA.MYSQL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerStats {

    public static HashMap<Player, Integer> kills = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> killstreak = new HashMap<Player, Integer>();

    Player p;

    public PlayerStats(Player p){
        this.p = p;
    }

    public int getKills(){
        return kills.get(p);
    }
    public int getDeaths(){
        return deaths.get(p);
    }
    public int getKillStreak(){
        return killstreak.get(p);
    }

    public void addKill(){
        kills.put(p, getKills()+1);
    }
    public void addDeath(){
        deaths.put(p, getDeaths()+1);
    }
    public void setKillstreak(int i){
        killstreak.put(p, i);
    }
    public void setKills(int i){
        kills.put(p, i);
    }
    public void setDeaths(int i){
        deaths.put(p, i);
    }

    public static void saveStats(Player p){
        if(MYSQL.isConnected()){
            MYSQL.update("UPDATE ffa SET Kills='"+kills.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ffa SET Deaths='"+new PlayerStats(p).getDeaths()+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ffa SET KillStreak='"+killstreak.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
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
            MYSQL.update("INSERT INTO ffa (Player , UUID, Kills, Deaths, KillStreak) VALUES ('"+p.getName()+"', '"+p.getUniqueId()+"', '0', '0', '0')");
        }else{

        }
    }

    public static void loadStats(Player p){
        kills.put(p, getKills(p));
        deaths.put(p, getDeaths(p));
        killstreak.put(p, getKillStreak(p));
        p.sendMessage("§8➤ §7Deine §bStats §7wurden geladen");
    }
    private static int getKills(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ffa WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Kills");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getDeaths(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ffa WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Deaths");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getKillStreak(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ffa WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("KillStreak");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }

    private static boolean isInList(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ffa WHERE UUID='"+p.getUniqueId()+"'");
        try{
            return set.next();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return false;

    }

    private static void updateName(Player p){
        MYSQL.update("UPDATE ffa SET Player='"+p.getName()+"' WHERE UUID='"+p.getUniqueId()+"'");
    }
}
