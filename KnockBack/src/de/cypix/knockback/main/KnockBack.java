package de.cypix.knockback.main;

import de.cypix.knockback.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KnockBack extends JavaPlugin {

    private static KnockBack instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;
        gameManager = new GameManager();
        init();
    }

    @Override
    public void onDisable() {

    }

    private void init(){
        PluginManager pm = Bukkit.getPluginManager();

    }

    public static KnockBack getInstance() {
        return instance;
    }
}
