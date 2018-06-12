package de.Cypix.CityBuild.Manager;

import de.Cypix.CityBuild.File.PlayerFile;
import de.Cypix.CityBuild.File.Var;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class HomeManager {

    Player p;
    PlayerFile file;
    public HomeManager(Player p){
        this.p = p;
        this.file = new PlayerFile(p);
    }



    public void setHome(String name){
        if(getHomes().contains(name.toLowerCase())){

            p.sendMessage(Var.pr+"§7Du hast dein §7Home §b"+name+" §7erneut gesetzt!");
            p.sendMessage(Var.pr+"§7Dein alter §bHome-Punkt §7wurde §c§lgelöscht!");

            file.saveLocation(p.getLocation(), "Home."+name.toLowerCase()+".Loc");

        }else{
            if(cansetnewHome()) {
                p.sendMessage(Var.pr+"§7Du hast dein Home §b"+name+" §7gesetzt!");
                List<String> list = getHomes();
                list.add(name.toLowerCase());
                file.saveLocation(p.getLocation(), "Home."+name.toLowerCase()+".Loc");
                file.save("Home."+name.toLowerCase()+".Name", name);
                file.save("Homes", list);


            }else{
                p.sendMessage(Var.pr+"§7Du benötigst einen höheren §bRang §7um mehr §bHomes §7setzten zu können!");
            }

        }
    }
    public boolean homeexists(String home){
        if(file.getString("Home."+home.toLowerCase()+".Name") != null)return true;
        return false;
    }
    public String getHomeName(String home){
        return file.getString("Home."+home.toLowerCase()+".Name");
    }

    public List<String> getHomes(){
        return file.getList("Homes");
    }
    public boolean cansetnewHome(){
        int homes = getHomes().size();
        int highestperm = 0;
        if(p.hasPermission("citybuild.homes.unlimeted")){
            return true;
        }
        for(int i = 0;i<20;i++){
            if(p.hasPermission("citybuild.homes."+i)){
                highestperm = i;
            }
        }
        if(highestperm >= homes){
            return true;
        }
        return false;
    }
    public Location getHomeLoc(String home){
        return new PlayerFile(p).getLocation("Home."+home.toLowerCase()+".Loc");
    }


}
