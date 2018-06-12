package de.cypix.ttt.mysql;

import de.cypix.ttt.Var;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.TicketManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PlayerStats {

    public static HashMap<Player, Integer> won = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> played = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> kills = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> detectivepasses = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> traitorpasses = new HashMap<>();
    public static HashMap<Player, Integer> test = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> karma = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> tickets = new HashMap<Player, Integer>();


    Player p;

    public PlayerStats(Player p){
        this.p = p;
    }



    public void addDeath(){ deaths.put(p, deaths.get(p)+1);}
    public void addKill() {kills.put(p, getKills(p)+1);}
    public void addGame(){played.put(p, played.get(p)+1);}
    public void addWin(){won.put(p, won.get(p)+1);}
    public void addTest(){test.put(p, test.get(p)+1);}
    public void addTicket() {tickets.put(p, tickets.get(p)+1);}
    int ticketsss = 0;
    public void addKarma(int karm){
        for(int i = 0;i<=karma.get(p)+karm;i+=500){
            ticketsss++;
            p.sendMessage(i+" ");
            if(i >= getKarma()){
                if(i <= getKarma()+karm){
                    if(getTickets() < ticketsss) {
                        p.sendMessage(Var.pr + "Du bekommst ein Traitor und Detective Ticket !");
                        addTest();
                        addTraitorPass();
                        addDetectivePass();
                    }
                }
            }
        }
        karma.put(p, karma.get(p)+karm);
    }
    public void removeKarma(int karm){karma.put(p, karma.get(p)-karm);}
    public void addTraitorPass(){traitorpasses.put(p, traitorpasses.get(p)+1);}
    public void addDetectivePass(){detectivepasses.put(p, detectivepasses.get(p)+1);}

    public void removeTraitorPass(){traitorpasses.put(p, (traitorpasses.get(p)-1));}
    public void removeDetectivePass(){detectivepasses.put(p, (detectivepasses.get(p)-1));}



    public int getWonGames(){return won.get(p);}
    public int getPlayedGames(){return played.get(p);}
    public int getKills(){return kills.get(p);}
    public int getDeaths(){return deaths.get(p);}
    public int getKarma(){return karma.get(p);}
    public int getdetectivepasses(){return detectivepasses.get(p);}
    public int getTraitorPasses(){return traitorpasses.get(p);}
    public int getTests(){return test.get(p);}
    public int getTickets(){return tickets.get(p);}


    public boolean hasTraitorpass() {
        if(getTraitorPasses() > 0)return true;
        return false;
    }
    public boolean hasDetectivepass() {
        if(getdetectivepasses() > 0)return true;
        return false;
    }


    public static void saveStats(Player p){
        if(MYSQL.isConnected()){
            MYSQL.update("UPDATE ttt SET Won='"+won.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Deaths='"+deaths.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Kills='"+kills.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Games='"+played.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Tests='"+test.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Karma='"+karma.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET TraitorPasses='"+traitorpasses.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET DetectivePasses='"+detectivepasses.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");
            MYSQL.update("UPDATE ttt SET Tickets='"+tickets.get(p)+"' WHERE UUID='"+p.getUniqueId()+"'");

        }
    }

    public static void saveallplayersStats(){
        for(Player p : Main.getInstance().getPlayers()){
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
            MYSQL.update("INSERT INTO ttt (Player , UUID, Won, Deaths, Kills, Games, Karma, TraitorPasses, DetectivePasses, Tests, Tickets)" +
                    " VALUES ('"+p.getName()+"', '"+p.getUniqueId()+"', '0', '0', '0', '0', '0', '0', '0', '0', '0')");
        }else{

        }
    }

    public static void loadStats(Player p){

        won.put(p, getWonGames(p));
        played.put(p, getWonGames(p));
        kills.put(p, getKills(p));
        deaths.put(p, getDeaths(p));
        test.put(p, getTests(p));
        detectivepasses.put(p, getDetectivePasses(p));
        traitorpasses.put(p, getTraitorPasses(p));
        karma.put(p, getKarma(p));
        tickets.put(p, getTickets(p));


        p.sendMessage("§8➤ §7Deine §bStats §7wurden geladen");
    }
    private static int getDeaths(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Deaths");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getTickets(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Tickets");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getKills(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Kills");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getKarma(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Karma");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getTraitorPasses(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("TraitorPasses");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getDetectivePasses(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("DetectivePasses");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }
    private static int getTests(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            while(set.next()){
                return set.getInt("Kills");
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return 0;
    }

    private static int getplayedGames(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
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
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
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
        ResultSet set = MYSQL.getResult("SELECT * FROM ttt WHERE UUID='"+p.getUniqueId()+"'");
        try{
            return set.next();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return false;

    }

    private static void updateName(Player p){
        MYSQL.update("UPDATE ttt SET Player='"+p.getName()+"' WHERE UUID='"+p.getUniqueId()+"'");
    }
}