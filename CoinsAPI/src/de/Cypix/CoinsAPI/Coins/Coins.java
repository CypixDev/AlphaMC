package de.Cypix.CoinsAPI.Coins;

import de.Cypix.CoinsAPI.Main.main;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.entity.Player;

import de.Cypix.CoinsAPI.MYSQL.Manager;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Coins {


    private Player p;
    private Manager m;


    public Coins(Player p) {
        this.p = p;
        this.m = new Manager(p);
    }

    public int getCoins() {
        return m.getCoins();
    }

    public void addCoins(int coins) {
        m.setCoins((m.getCoins()+coins));
    }
    public void setCoins(int coins) {
        m.setCoins(coins);
    }
    public void removeCoins(int coins) {
        m.setCoins((m.getCoins()-coins));
    }



    public static void ne(){};

    public static void setCois(Player p, int Coins) {
        Manager m = new Manager(p);


        m.setCoins(Coins);
        Manager.coins.put(p, Coins);

    }
    public static void setCois(String player, int Coins) {
        Manager.setCoinsPlayer(player, Coins);
    }



    public static int getCoins(Player p) {
        return Manager.coins.get(p);
    }

    public static int getCoins(String player) {
        return Manager.getCoinsPlayer(player);
    }


    public static void addCoins(Player p, int Coins) {
        Manager m = new Manager(p);
        m.setCoins((m.getCoins()+Coins));
        Manager.coins.put(p, Manager.coins.get(p)+Coins);
    }
    public static void addCoins(String player, int Coins) {
        Manager.setCoinsPlayer(player, Manager.getCoinsPlayer(player)+Coins);
    }

    public static void removeCoins(Player p, int Coins) {
        Manager m = new Manager(p);
        m.setCoins((m.getCoins()-Coins));
        Manager.coins.put(p, (Manager.coins.get(p)-Coins));

    }
    public static void removeCoins(String player, int Coins) {
        Manager.setCoinsPlayer(player, (Manager.getCoinsPlayer(player)-Coins));
    }

    public static void sendToBungeeCord(Player p, String channel, String information){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(stream);

        try {
            output.writeUTF(channel);
            output.writeUTF(information);
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.sendPluginMessage(main.getInstance(), "BungeeCord", stream.toByteArray());
    }





}
