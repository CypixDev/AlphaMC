package de.cypix.alphaapi.coins;

import de.cypix.alphaapi.mysql.Mysql;
import de.cypix.alphaapi.stats.StatsPlayer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Coins {

    private StatsPlayer sp;
    private int coins;
    private boolean loaded = false;

    public Coins(StatsPlayer sp) {
        this.sp = sp;
    }

    public void loadCoinsIfNotLoaded(){
        if(!loaded){
            loaded = true;
            ResultSet rs = Mysql.getResult("SELECT * FROM Coins WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
            try{
                if(rs.next()) {
                    coins = rs.getInt("Coins");
                }else{
                    Mysql.update("INSERT INTO Coins(UUID, Coins) VALUES ('"+sp.getPlayer().getUniqueId()+"', '0')");
                    coins = 0;
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        }
    }

    public int getCoins() {
        loadCoinsIfNotLoaded();
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int coins){
        this.coins = this.coins+coins;
    }
    public void removeCoins(int coins){
        this.coins = this.coins-coins;
    }

    public void saveCoinsInMySQL(){
        Mysql.update("UPDATE Coins SET Coins='"+coins+"' WHERE UUID='"+sp.getPlayer().getUniqueId()+"'");
        sp.getPlayer().sendMessage("§b§lDeine Coins wurden in der Mysql Datenbank gespeichert !");
    }

    public void sendCoins() {
        sp.getPlayer().sendMessage("Coins: "+getCoins());
    }
}
