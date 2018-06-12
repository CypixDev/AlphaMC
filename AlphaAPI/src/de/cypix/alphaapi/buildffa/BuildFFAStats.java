package de.cypix.alphaapi.buildffa;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuildFFAStats {

    private StatsPlayer sp;
    private String uuid;

    private int kills;
    private int deaths;
    private int points;
    private int killStreak;
    private int blocks;

    private boolean loaded = false;

    public BuildFFAStats(StatsPlayer sp){
        this.sp = sp;
        this.uuid = sp.getPlayer().getUniqueId().toString();
    }

    public BuildFFAStats(String uuid){
        this.uuid = uuid;
    }

    private void loadStatsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM BuildFFA_Stats WHERE UUID='"+uuid+"'");
            try{
                if(rs.next()){
                    kills = rs.getInt("Kills");
                    deaths = rs.getInt("Deaths");
                    points = rs.getInt("Points");
                    killStreak = rs.getInt("KillStreak");
                    blocks = rs.getInt("Blocks");
                }else{
                    Mysql.update("INSERT INTO BuildFFA_Stats(UUID, Kills, Deaths, Points, KillStreak, Blocks) VALUES ('"+uuid+"', '0', '0', '0', '0', '0')");
                    kills = 0;
                    deaths = 0;
                    points = 0;
                    killStreak = 0;
                    blocks = 0;
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }
    public void saveStatsInMysql(){
        Mysql.update("UPDATE BuildFFA_Stats SET Kills='"+kills+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE BuildFFA_Stats SET Deaths='"+deaths+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE BuildFFA_Stats SET Points='"+points+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE BuildFFA_Stats SET KillStreak='"+killStreak+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE BuildFFA_Stats SET Blocks='"+blocks+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        System.out.println("Stats updated !"+sp.getPlayer().getName());
    }

    public int getKillStreak() {
        loadStatsIfNotLoaded();
        return killStreak;
    }

    public int getKills() {
        loadStatsIfNotLoaded();
        return kills;
    }

    public int getDeaths() {
        loadStatsIfNotLoaded();
        return deaths;
    }

    public int getBlocks() {
        loadStatsIfNotLoaded();
        return blocks;
    }

    public int getPoints() {
        loadStatsIfNotLoaded();
        return points;
    }

    public void setBlocks(int blocks) {
        loadStatsIfNotLoaded();
        this.blocks = blocks;
    }

    public void setDeaths(int deaths) {
        loadStatsIfNotLoaded();
        this.deaths = deaths;
    }

    public void setKills(int kills) {
        loadStatsIfNotLoaded();
        this.kills = kills;
    }

    public void setKillStreak(int killStreak) {
        loadStatsIfNotLoaded();
        this.killStreak = killStreak;
    }

    public void setPoints(int points) {
        loadStatsIfNotLoaded();
        this.points = points;
    }

    public void addPotions(int points){
        loadStatsIfNotLoaded();
        this.points = this.points+points;
    }

    public void addKill(){
        loadStatsIfNotLoaded();
        this.kills = kills+1;
    }

    public void addDeath(){
        loadStatsIfNotLoaded();
        this.deaths = this.deaths+1;
    }
    public void addBlock(){
        loadStatsIfNotLoaded();
        this.blocks = this.blocks+1;
    }


    public void sendStats(){
        Player p = sp.getPlayer();
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lBuildFFA §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lPoints §r§l➢ §c§l"+getPoints());
        p.sendMessage("▌§b§lKillStreak §r§l➢ §c§l"+getKillStreak());
        p.sendMessage("▌§b§lBlocks §r§l➢ §c§l"+getBlocks());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }
    public void sendStats(Player p){
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lBuildFFA §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lPoints §r§l➢ §c§l"+getPoints());
        p.sendMessage("▌§b§lKillStreak §r§l➢ §c§l"+getKillStreak());
        p.sendMessage("▌§b§lBlocks §r§l➢ §c§l"+getBlocks());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }

}
