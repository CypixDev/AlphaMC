package de.Cypix.Survival.Manager;

import de.Cypix.Survival.Main.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerManager {

    Player p;
    File file;
    FileConfiguration cfg;

    public PlayerManager(Player p){
        this.p = p;
        this.file = new File(main.getInstance().getDataFolder(), "Players/"+p.getUniqueId()+".yml");
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public void addPlayedMinuet(){
        long min = cfg.getLong("PlayedTime.Time");
        min = min+1;
        cfg.set("PlayedTime.Time", min);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getFile(){
        return this.file;
    }
    public FileConfiguration getCfg(){
        return this.cfg;
    }

    public String getPlayedTime(){

        long min = cfg.getLong("PlayedTime.Time");

        int hours = 0;
        int days = 0;
        int weeks = 0;
        int month = 0;
        int year = 0;


        while(min >= 60){
            min-=60;
            hours++;
        }
        while (hours >= 60){
            hours-=60;
            weeks++;
        }
        while(weeks >= 4){
            weeks-=4;
            month++;
        }
        while(month > 12){
            month-=12;
            year++;
        }
        String msg = "";

        if(min != 0)msg = msg+min+"Minuten |";
        if(hours != 0)msg = msg+hours+" Stunden |";
        if(days != 0)msg = msg+days+" Tage | ";
        if(weeks != 0)msg = msg+weeks+" Wochen | ";
        if(month != 0)msg = msg+month+" Monate | ";
        if(year != 0)msg = msg+year+" Jahre";

        return msg;
    }




}
