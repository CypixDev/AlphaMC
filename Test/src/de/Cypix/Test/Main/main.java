package de.Cypix.Test.Main;

import de.Cypix.Test.CMD.CMDTest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class main extends JavaPlugin {

    public static main instance;

    @Override
    public void onEnable() {
        this.instance = this;

        this.getCommand("test").setExecutor(new CMDTest());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new CMDTest(), this);

    }

    @Override
    public void onDisable() {



    }

    public static main getinstance(){
        return instance;
    }
}
