package de.cypix.alphaapi.knockback;


import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KnockBackStats {

    private StatsPlayer sp;
    private int kills;
    private int deaths;
    private int points;
    private int killStreak;
    private int boosts;
    private boolean loaded;

    private String uuid;


    public KnockBackStats(StatsPlayer sp){
        this.sp = sp;
        this.uuid = sp.getPlayer().getUniqueId().toString();
        loaded = false;
    }

    public KnockBackStats(String uuid){
        this.uuid = uuid;
        loaded = false;
    }

    public void loadStatsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM KnockBack_Stats WHERE UUID='"+uuid+"'");
            try{
                if(rs.next()) {
                    sp.getPlayer().sendMessage("§b§lDeine Stats wurden Frisch aus der MYSQL geladen !");
                    kills = rs.getInt("Kills");
                    deaths = rs.getInt("Deaths");
                    points = rs.getInt("Points");
                    killStreak = rs.getInt("KillStreak");
                    boosts = rs.getInt("Boosts");
                    return;
                }else{
                    Mysql.update("INSERT INTO KnockBack_Stats(UUID, Kills, Deaths, Points, KillStreak, Boosts) VALUES ('"+uuid+"', '0', '0', '0', '0', '0');");
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }
    public int getBoosts() {
        loadStatsIfNotLoaded();
        return boosts;
    }

    public int getDeaths() {
        loadStatsIfNotLoaded();
        return deaths;
    }

    public int getKills() {
        loadStatsIfNotLoaded();
        return kills;
    }

    public int getPoints() {
        return points;
    }

    public int getKillStreak() {
        loadStatsIfNotLoaded();
        return killStreak;
    }
    public void sendStats(){
        Player p = sp.getPlayer();
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lKnockBack §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lKillStreak §r§l➢ §c§l"+getKillStreak());
        p.sendMessage("▌§b§lBoosts §r§l➢ §c§l"+getBoosts());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }
    public void sendStats(Player p){
        p.sendMessage(" ");
        p.sendMessage("▛▀▀▀ §b§lKnockBack §r§l➣ Stats §r▀▀▀▜");
        p.sendMessage("▌§b§lKills §r§l➢ §c§l"+getKills());
        p.sendMessage("▌§b§lDeaths §r§l➢ §c§l"+getDeaths());
        p.sendMessage("▌§b§lKillStreak §r§l➢ §c§l"+getKillStreak());
        p.sendMessage("▌§b§lBoosts §r§l➢ §c§l"+getBoosts());
        p.sendMessage("▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟");
        p.sendMessage(" ");
    }
}
