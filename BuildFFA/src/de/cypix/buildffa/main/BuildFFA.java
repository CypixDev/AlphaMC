package de.cypix.buildffa.main;

import de.cypix.buildffa.cmd.CMDNetxMap;
import de.cypix.buildffa.game.GameListener;
import de.cypix.buildffa.game.GameManager;
import de.cypix.buildffa.game.StatsListener;
import de.cypix.buildffa.inventory.VillagerListener;
import de.cypix.buildffa.setup.CMDSetup;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA instance;
    private GameManager gameManager;
    private static File file;
    private static FileConfiguration cfg;

    @Override
    public void onEnable() {
        BuildFFA.instance = this;
        file = new File(BuildFFA.getInstance().getDataFolder(), "Maps.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        init();
    }

    private void init() {
        PluginManager pm = Bukkit.getPluginManager();
        if(cfg.getStringList("Maps").size() > 1){
            this.gameManager = new GameManager(this);
            pm.registerEvents(new GameListener(), this);
            pm.registerEvents(new VillagerListener(), this);
            pm.registerEvents(new StatsListener(), this);
        }

        this.getCommand("setup").setExecutor(new CMDSetup());
        this.getCommand("nextmap").setExecutor(new CMDNetxMap());
    }

    public static BuildFFA getInstance() {
        return instance;
    }
    public static List<String> getMaps(){
        return cfg.getStringList("Maps");
    }
    public static boolean mapexists(String map){
        for(String maps : getMaps()){
            if(maps.equalsIgnoreCase(map)) return true;
        }
        return false;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
