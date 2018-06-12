package de.cypix.alphaapi.gungame;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GunGameStats {


    private StatsPlayer sp;
    private int kills;
    private int deaths;
    private int points;
    private int level;
    private int levelRecord;

    private boolean loaded;

    private String uuid;


    public GunGameStats(StatsPlayer sp){
        this.sp = sp;
        this.uuid = sp.getPlayer().getUniqueId().toString();
        loaded = false;
    }

    public GunGameStats(String uuid){
        this.uuid = uuid;
        loaded = false;
    }

    public void loadStatsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM GunGame_Stats WHERE UUID='"+uuid+"'");
            try{
                if(rs.next()) {
                    sp.getPlayer().sendMessage("§b§lDeine Stats wurden Frisch aus der MYSQL geladen !");
                    kills = rs.getInt("Kills");
                    deaths = rs.getInt("Deaths");
                    points = rs.getInt("Points");
                    level = rs.getInt("Level");
                    levelRecord = rs.getInt("LevelRecord");
                    return;
                }else{
                    Mysql.update("INSERT INTO GunGame_Stats(UUID, Kills, Deaths, Points, Level, LevelRecord) VALUES ('"+uuid+"', '0', '0', '0', '0', '0');");
                    kills = 0;
                    deaths = 0;
                    points = 0;
                    level = 0;
                    levelRecord = 0;
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    public void saveStatsInMysql(){
        Mysql.update("UPDATE GunGame_Stats SET Kills='"+kills+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE GunGame_Stats SET Deaths='"+deaths+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE GunGame_Stats SET Points='"+points+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE GunGame_Stats SET Level='"+level+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        Mysql.update("UPDATE GunGame_Stats SET LevelRecord='"+levelRecord+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        System.out.println("Stats updated !"+sp.getPlayer().getName());
    }

    public int getPoints() {
        loadStatsIfNotLoaded();
        return points;
    }

    public int getDeaths() {
        loadStatsIfNotLoaded();
        return deaths;
    }

    public int getKills() {
        loadStatsIfNotLoaded();
        return kills;
    }

    public int getLevel() {
        loadStatsIfNotLoaded();
        return level;
    }

    public int getLevelRecord() {
        loadStatsIfNotLoaded();
        return levelRecord;
    }

    public void addPoints(int points){
        this.points = this.points+points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLevelRecord(int levelRecord) {
        this.levelRecord = levelRecord;
    }

    public void addLevel(){
        this.level = level+1;
    }

    public void addDeath(){
        this.deaths = deaths+1;
    }

    public void addKill(){
        this.kills = kills+1;
    }


    public void sendStats(){
        Player p = sp.getPlayer();
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lGunGame §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lPoints §r§l➢ §c§l"+getPoints());
        p.sendMessage("▌§b§lLevel §r§l➢ §c§l"+getLevel());
        p.sendMessage("▌§b§lLevel-Record §r§l➢ §c§l"+getLevelRecord());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }
    public void sendStats(Player p){
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lGunGame §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lPoints §r§l➢ §c§l"+getPoints());
        p.sendMessage("▌§b§lLevel §r§l➢ §c§l"+getLevel());
        p.sendMessage("▌§b§lLevel-Record §r§l➢ §c§l"+getLevelRecord());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }

}
