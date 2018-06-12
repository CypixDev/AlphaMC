package de.Cypix.CityBuild.File;

import de.Cypix.CityBuild.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlayerFile {

    Player p;
    File file;
    FileConfiguration cfg;

    public PlayerFile(Player p){
        this.p = p;
        this.file = new File(main.getInstance().getDataFolder()+"/Players", p.getUniqueId()+".yml");
        this.cfg = YamlConfiguration.loadConfiguration(file);
    }

    /*public void setHome(String name){
        if(getHomes().contains(name.toLowerCase())){
            p.sendMessage("Das Home wurde umgesetzt !");
            cfg.set("Home."+name.toLowerCase()+".Name", name);

                    saveLocation(p.getLocation(), "Home."+name.toLowerCase()+".Loc");

            try { cfg.save(file); } catch (IOException e) {e.printStackTrace();}

        }else{
            if(cansetnewHome()) {
                p.sendMessage("Das Home wurde gesetzt !");
                List<String> list = getHomes();
                list.add(name.toLowerCase());
                saveLocation(p.getLocation(), "Home."+name.toLowerCase()+".Loc");
                cfg.set("Home."+name.toLowerCase()+".Name", name);
                cfg.set("Homes", list);

                try { cfg.save(file); } catch (IOException e) {e.printStackTrace();}

            }else{
                p.sendMessage("Du hast schon alle Homes gesetzt die du Setzten kannst !");
            }

        }
    }
    public String getHomeName(String home){
        return cfg.getString("Home."+home.toLowerCase()+".Name");
    }

    public List<String> getHomes(){
        return cfg.getStringList("Homes");
    }
    public boolean cansetnewHome(){
        int homes = getHomes().size();

        return true;
    }*/

    public int getInt(String path) {return cfg.getInt(path);}


    public static int getInt(String uuid, String path) {
        File file = new File(main.getInstance().getDataFolder()+"/Players", uuid+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getInt(path);
    }

    public void save(String path, Object obj){
        cfg.set(path, obj);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void save(String uuid, String path, Object obj){
        File file = new File(main.getInstance().getDataFolder()+"/Players", uuid+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set(path, obj);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Object get(String path){
        return cfg.get(path);
    }
    public String getString(String path){
        return cfg.getString(path);
    }
    public static String getString(String uuid, String path){
        File file = new File(main.getInstance().getDataFolder()+"/Players", uuid+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString(path);
    }
    public List<String> getList(String path){
        return cfg.getStringList(path);
    }
    public boolean getBoolean(String path){
        return cfg.getBoolean(path);
    }
    public static boolean isRegistert(String uuid){
        File file = new File(main.getInstance().getDataFolder()+"/Players", uuid+".yml");
        return file.exists();
    }

    public void saveLocation(Location loc, String path){

        cfg.set(path+".World", loc.getWorld().getName());
        cfg.set(path+".X", loc.getX());
        cfg.set(path+".Y", loc.getY());
        cfg.set(path+".Z", loc.getZ());
        cfg.set(path+".Yaw", loc.getYaw());
        cfg.set(path+".Pitch", loc.getPitch());


        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(String path){

        World world = Bukkit.getWorld(cfg.getString(path+".World"));
        double x = cfg.getDouble(path+".X");
        double y = cfg.getDouble(path+".Y");
        double z = cfg.getDouble(path+".Z");
        float yaw = (float) cfg.getDouble(path+".Yaw");
        float pitch = (float) cfg.getDouble(path+".Pitch");

        Location loc = new Location(world, x, y ,z ,yaw, pitch);

        return loc;
    }


}
