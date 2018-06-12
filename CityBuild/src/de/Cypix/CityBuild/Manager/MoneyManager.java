package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.File.PlayerFile;
import org.bukkit.entity.Player;

public class MoneyManager {

    Player p;
    PlayerFile pf;

    public MoneyManager(Player p){
        this.p = p;
        this.pf = new PlayerFile(p);
    }

    public void setBalace(int Balance){
        pf.save("Money", Balance);
    }
    public boolean remove(int toremove){
        if(toremove <= getBalance()){
            setBalace(getBalance()-toremove);
            return true;
        }
        return false;
    }
    public void add(int toadd){
        setBalace(getBalance()+toadd);
    }

    public int getBalance(){
        return pf.getInt("Money");
    }


}
