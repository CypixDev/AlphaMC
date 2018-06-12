package de.Cypix.Survival.FileManager;

import de.Cypix.Survival.Main.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private static File file = new File(main.getInstance().getDataFolder()+"/Config.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);


    public static void createDefaults(){
        addDefault("MYSQL.Enable", false);
        addDefault("MYSQL.Host", "localhost");
        addDefault("MYSQL.Port", 3306);
        addDefault("MYSQL.Database", "Survival");
        addDefault("MYSQL.User", "Survival");
        addDefault("MYSQL.Passwd", "Secret");


        cfg.options().copyDefaults(true);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addDefault(String path, Object value){
        cfg.addDefault(path, value);


}



}
