package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.MYSQL.MYSQL;
import de.Cypix.Lobby.inventar.Extras.Boots;
import de.Cypix.Lobby.inventar.Extras.Gadgets;
import de.Cypix.Lobby.inventar.Extras.Heads;
import de.Cypix.Lobby.inventar.Extras.Pets;
import de.cypix.alphaapi.coins.Coins;
import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.stats.StatsPlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopManager {

    public static HashMap<Player, ArrayList<Integer>> list = new HashMap<>();


    public static void loadShopItems(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM Shop WHERE UUID='"+p.getUniqueId()+"'");
        try{
            ArrayList<Integer> listt = new ArrayList<Integer>();
            while(set.next()){
                listt.add(set.getInt("ID"));
            }
            if(!listt.isEmpty()) list.put(p, listt);
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    }

    public static List<Integer> getMYSQLList(Player p){
        ResultSet set = MYSQL.getResult("SELECT * FROM Shop WHERE UUID='"+p.getUniqueId()+"'");
        try{
            ArrayList<Integer> listt = new ArrayList<Integer>();
            while(set.next()){
                listt.add(set.getInt("ID"));
            }
            return listt;
        }catch(SQLException e1){
            e1.printStackTrace();
        }
        return null;
    }


    public static void saveShopItems(Player p){
        List<Integer> my = getMYSQLList(p);
        if(list.containsKey(p)) {
            for (int i = 0; i < list.get(p).size(); i++) {
                if (!my.contains(list.get(p).get(i))) {
                    MYSQL.update("INSERT INTO Shop(UUID, ID) VALUES ('" + p.getUniqueId() + "', '" + list.get(p).get(i) + "')");
                }
            }
        }
    }

    public static void unloadItems(Player p){
        if(list.containsKey(p)){
            list.remove(p);
        }
    }


    public static boolean BuyBoot(Player p, Boots boot){
        if(!hasbuyed(p, boot)){
            Coins coins = AlphaAPI.getInstance().getStatsPlayer(p).getCoins();
            if(coins.getCoins() >= boot.getPrise()) {
                if (list.containsKey(p)) {
                    list.get(p).add(boot.getID());
                    coins.removeCoins(boot.getPrise());
                    return true;
                } else {
                    ArrayList<Integer> bootid = new ArrayList<>();
                    bootid.add(boot.getID());
                    list.put(p, bootid);
                    coins.removeCoins(boot.getPrise());
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean BuyHead(Player p, Heads head){
        if(!hasbuyed(p, head)){
            Coins coins = AlphaAPI.getInstance().getStatsPlayer(p).getCoins();
            if(coins.getCoins() >= head.getPrise()) {
                if (list.containsKey(p)) {
                    list.get(p).add(head.getID());
                    coins.removeCoins(head.getPrise());
                    return true;
                } else {
                    ArrayList<Integer> bootid = new ArrayList<>();
                    bootid.add(head.getID());
                    list.put(p, bootid);
                    coins.removeCoins(head.getPrise());
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;



    }


    public static boolean BuyPet(Player p, Pets pet){
        if(!hasbuyed(p, pet)){
            Coins coins = AlphaAPI.getInstance().getStatsPlayer(p).getCoins();
            if(coins.getCoins() >= pet.getPrise()) {
                if (list.containsKey(p)) {
                    list.get(p).add(pet.getID());
                    coins.removeCoins(pet.getPrise());
                    return true;
                } else {
                    ArrayList<Integer> petid = new ArrayList<>();
                    petid.add(pet.getID());
                    list.put(p, petid);
                    coins.removeCoins(pet.getPrise());
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }

    public static boolean BuyGadget(Player p, Gadgets gd){
        if(!hasbuyed(p, gd)){
            Coins coins = AlphaAPI.getInstance().getStatsPlayer(p).getCoins();
            if(coins.getCoins() >= gd.getPrise()) {
                if (list.containsKey(p)) {
                    list.get(p).add(gd.getID());
                    coins.removeCoins(gd.getPrise());
                    return true;
                } else {
                    ArrayList<Integer> petid = new ArrayList<>();
                    petid.add(gd.getID());
                    list.put(p, petid);
                    coins.removeCoins(gd.getPrise());
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
    }




    public static boolean hasbuyed(Player p, Boots boot) {
        if(p.hasPermission("weakmc.inventory"))return true;
        if (list.containsKey(p)) {
            if (list.get(p).contains(boot.getID())) {
                return true;
            }
        }
        return false;
    }


    public static boolean hasbuyed(Player p, Gadgets gd) {
        if(p.hasPermission("weakmc.inventory"))return true;
        if (list.containsKey(p)) {
            if (list.get(p).contains(gd.getID())) {
                return true;
            }
        }
        return false;
    }


    public static boolean hasbuyed(Player p, Pets pet) {
        if(p.hasPermission("weakmc.inventory"))return true;
        if (list.containsKey(p)) {
            if (list.get(p).contains(pet.getID())) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasbuyed(Player p, Heads head) {
        if(p.hasPermission("weakmc.inventory"))return true;
        if (list.containsKey(p)) {
            if (list.get(p).contains(head.getID())) {
                return true;
            }
        }
        return false;
    }


}
