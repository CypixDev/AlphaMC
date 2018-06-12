package de.Cypix.Survival.FileManager;

import de.Cypix.Survival.Main.main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class Var {


    public static String pr = "§7[§6Survival§7] ";
    public static String noperm = "§cDazu fehlen dir die Rechte !";
    public static String offline = "§cDieser Spieler ist Offline !";

    File file;
    FileConfiguration cfg;

    public Var(String file){
        this.file = new File(main.getInstance().getDataFolder()+file+".yml");
        this.cfg = YamlConfiguration.loadConfiguration(this.file);
    }

    public String getString(String path){
        if(cfg.getString(path) != null) return ChatColor.translateAlternateColorCodes('&', cfg.getString(path));

        return null;
    }
    public int getInt(String path){
        return cfg.getInt(path);
    }
    public boolean getBoolean(String path){
        return cfg.getBoolean(path);
    }
    public List<String> getStringList(String path){
        return cfg.getStringList(path);
    }





    public static String getString(String folder, String path){
        File file = new File(main.getInstance().getDataFolder()+folder+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if(cfg.getString(path) != null) return ChatColor.translateAlternateColorCodes('&', cfg.getString(path));
        return null;
    }

    public static int getInt(String folder, String path){
        File file = new File(main.getInstance().getDataFolder()+folder+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getInt(path);
    }
    public static boolean getBoolean(String folder, String path){
        File file = new File(main.getInstance().getDataFolder()+folder+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getBoolean(path);
    }
    public static List<String> getStringList(String folder, String path){
        File file = new File(main.getInstance().getDataFolder()+folder+".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getStringList(path);
    }



}
