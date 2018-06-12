package de.cypix.api.stats;

import de.cypix.api.mysql.Mysql;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlashStats {

    public static HashMap<Player, ArrayList<String>> stats = new HashMap<>();
    Player p;

    public FlashStats(Player p){
        this.p = p;
    }

    public void loadStats(){
        if(isInList(p)) {
            Mysql.update("UPDATE flash SET Player='"+p.getName()+"' WHERE UUID='"+p.getUniqueId()+"'");

            ResultSet rs = Mysql.getResult("SELECT * FROM flash WHERE UUID='" + p.getUniqueId() + "'");
            ArrayList<String> list = new ArrayList<>();
            try {
                list.add(rs.getString("Player"));
                list.add(rs.getString("UUID"));
                list.add(rs.getInt("Deaths") + "");
                list.add(rs.getInt("Won") + "");
                list.add(rs.getInt("Games") + "");
                list.add(rs.getInt("CheckPoint") + "");
                list.add(rs.getInt("Time") + "");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            stats.put(p, list);
        }
    }
    public void sendStatsMessage(){
        if(stats.containsKey(p)) {
            ArrayList<String> list = stats.get(p);

            p.sendMessage("§7§l┏╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┓");
            p.sendMessage(" ");
            p.sendMessage("§8➤ §7Stats von §b" + list.get(0));
            p.sendMessage(" ");
            p.sendMessage("§8➤ §7Tode: §b " + list.get(2));
            p.sendMessage("§8➤ §7Gewonnene Spiele §b: " + list.get(3));
            p.sendMessage("§8➤ §7Gespielte Spiele §b: " + list.get(4));
            p.sendMessage("§8➤ §7CheckPoints Erreicht: §b " + list.get(5));
            p.sendMessage("§8➤ §7Beste Zeit: §b: " + list.get(6));
            p.sendMessage(" ");
            p.sendMessage("§7§l┗╋§7§l§m----------§7§l◥◣◆◢◤§7§l§m----------§7§l╋┛");


        }else{
            p.sendMessage("Du hast das Spiel noch nicht gespielt !");
        }
    }

    private static boolean isInList(Player p){
        ResultSet set = Mysql.getResult("SELECT * FROM flash WHERE UUID='"+p.getUniqueId()+"'");
        try{
            return set.next();
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return false;

    }

}
