package de.Cypix.Belohnung.Main;

import de.Cypix.Belohnung.CMD.CMDBelohnung;
import de.Cypix.Belohnung.Listener.InteractListener;
import de.Cypix.Belohnung.Listener.InventoryListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main Instance;

    @Override
    public void onEnable() {

        Instance = this;


        this.getCommand("belohnung").setExecutor(new CMDBelohnung());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);

    }

    @Override
    public void onDisable() {

    }

    public static main getInstance() {
        return Instance;
    }

}
