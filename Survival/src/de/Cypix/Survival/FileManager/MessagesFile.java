package de.Cypix.Survival.FileManager;

import de.Cypix.Survival.Main.main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesFile {

    private static File file = new File(main.getInstance().getDataFolder()+"/Messages.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void createDefaults(){

        addDefault("Message.Join.Chat", "%PLAYER% hat den Server betreten !");
        addDefault("Message.Join.Title", "&aSurvival");
        addDefault("Message.Join.SubTitle", "&aViel Spaß !");




        cfg.options().copyDefaults(true);

        try{

            cfg.save(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void addDefault(String path, Object value){
        cfg.addDefault(path, value);
    }


}
