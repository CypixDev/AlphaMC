package de.cypix.gungame.main;

import de.cypix.gungame.forcemap.CMDForceMap;
import de.cypix.gungame.cmd.CMDNextMap;
import de.cypix.gungame.cmd.CMDSetLevel;
import de.cypix.gungame.forcemap.ForceMapListener;
import de.cypix.gungame.ingame.GameListener;
import de.cypix.gungame.ingame.GameManager;
import de.cypix.gungame.ingame.Level;
import de.cypix.gungame.ingame.StatsListener;
import de.cypix.gungame.setup.CMDSetUp;
import de.cypix.gungame.setup.Setup;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GunGame extends JavaPlugin {

    private static GunGame instance;
    private Setup setup;
    private GameManager gameManager;
    private Level level;

    @Override
    public void onEnable() {
        GunGame.instance = this;
        this.setup = new Setup();
        this.level = new Level();
        init();
    }

    private void init() {
        PluginManager pm = Bukkit.getPluginManager();
        if(!Setup.getMaps().isEmpty()){
            this.gameManager = new GameManager();
            pm.registerEvents(new GameListener(), this);
            pm.registerEvents(new StatsListener(), this);
            pm.registerEvents(new ForceMapListener(), this);
        }

        this.getCommand("setup").setExecutor(new CMDSetUp());
        this.getCommand("setlevel").setExecutor(new CMDSetLevel());
        this.getCommand("nextmap").setExecutor(new CMDNextMap());
        this.getCommand("forcemap").setExecutor(new CMDForceMap());
    }

    public static GunGame getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public Setup getSetup() {
        return setup;
    }

    public Level getLevel() {
        return level;
    }
}
