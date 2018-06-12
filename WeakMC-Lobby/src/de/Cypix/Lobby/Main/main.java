package de.Cypix.Lobby.Main;

import de.Cypix.Lobby.CMD.*;
import de.Cypix.Lobby.Listener.*;
import de.Cypix.Lobby.MYSQL.MYSQL;
import de.Cypix.Lobby.Manager.*;
import de.Cypix.Lobby.inventar.InventarListener;
import de.Cypix.Lobby.navigator.NaviListener;
import de.Cypix.Lobby.settings.SettingsListener;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        this.instance = this;

        MYSQL my = new MYSQL("localhost", 3306, "Lobby", "Lobby", "x2gabdac");
        my.connect();


        my.createTables();

        registerListener();

        registerCMD();

        startscheduler();

        onReload();


    }


    @Override
    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()){
            ShopManager.saveShopItems(p);
            p.kickPlayer("§7Der §bServer §7startet neu! \n §bGrund§7 : §7Update");
        }
    }
    private void onReload(){
        if(Bukkit.getOnlinePlayers().size() != 0){
            for(Player p : Bukkit.getOnlinePlayers()){
                ShopManager.loadShopItems(p);
                CloudServer.getInstance().updateNameTags(p);

            }
        }
        World world = Bukkit.getWorld("world");
        world.setAnimalSpawnLimit(0);
        world.setMonsterSpawnLimit(0);
    }


    public void registerListener(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new BuildListener(), this);
        pm.registerEvents(new ElseListener(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new NaviListener(), this);
        pm.registerEvents(new PetsListener(), this);
        pm.registerEvents(new InventarListener(), this);
        pm.registerEvents(new BootsListener(), this);
        pm.registerEvents(new SettingsListener(), this);
    }

    public void registerCMD(){
        this.getCommand("set").setExecutor(new CMDSet());
        this.getCommand("build").setExecutor(new CMDBuild());
        this.getCommand("test").setExecutor(new CMDTest());
        this.getCommand("fly").setExecutor(new CMDFly());
        this.getCommand("actionbar").setExecutor(new CMDActionbar());
    }

    public static main getInstance(){
        return instance;
    }

    public void startscheduler(){
        ActionBarManager.startScheduler();
        //NaviManager.start();
    }


}
