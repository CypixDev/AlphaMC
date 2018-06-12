package de.Cypix.CityBuild.Main;

import de.Cypix.CityBuild.CMD.*;
import de.Cypix.CityBuild.CMD.Money.CMDMoney;
import de.Cypix.CityBuild.CMD.Money.CMDPay;
import de.Cypix.CityBuild.CMD.Times.CMDDay;
import de.Cypix.CityBuild.CMD.Times.CMDNight;
import de.Cypix.CityBuild.CMD.Tp.*;
import de.Cypix.CityBuild.CMD.Warps.CMDFarmwelt;
import de.Cypix.CityBuild.CMD.Warps.CMDWarp;
import de.Cypix.CityBuild.CMD.Gamamode.CMDGm;
import de.Cypix.CityBuild.CMD.Gamamode.CMDGmc;
import de.Cypix.CityBuild.CMD.Home.CMDHome;
import de.Cypix.CityBuild.CMD.Home.CMDHomes;
import de.Cypix.CityBuild.CMD.Home.CMDSetHome;
import de.Cypix.CityBuild.CMD.Spawn.CMDSetSpawn;
import de.Cypix.CityBuild.CMD.Spawn.CMDSpawn;
import de.Cypix.CityBuild.Listener.AdminShopListener;
import de.Cypix.CityBuild.Listener.JoinListener;
import de.Cypix.CityBuild.Listener.WarpListener;
import de.Cypix.CityBuild.Manager.AdminShopManager;
import de.Cypix.CityBuild.Manager.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCMDs();
        registerListeners();
        VanishManager.setallvisible();
        AdminShopManager.loadSigns();
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new WarpListener(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new AdminShopListener(), this);
    }

    private void registerCMDs() {
        this.getCommand("day").setExecutor(new CMDDay());
        this.getCommand("night").setExecutor(new CMDNight());
        this.getCommand("warp").setExecutor(new CMDWarp());
        this.getCommand("home").setExecutor(new CMDHome());
        this.getCommand("sethome").setExecutor(new CMDSetHome());
        this.getCommand("homes").setExecutor(new CMDHomes());
        this.getCommand("spawn").setExecutor(new CMDSpawn());
        this.getCommand("setspawn").setExecutor(new CMDSetSpawn());
        this.getCommand("gm").setExecutor(new CMDGm());
        this.getCommand("gmc").setExecutor(new CMDGmc());
        this.getCommand("farmwelt").setExecutor(new CMDFarmwelt());
        this.getCommand("vanish").setExecutor(new CMDVanish());
        this.getCommand("head").setExecutor(new CMDHead());
        this.getCommand("fly").setExecutor(new CMDFly());
        this.getCommand("tp").setExecutor(new CMDTp());
        //this.getCommand("tphere").setExecutor(new CMDTpHere());
        this.getCommand("tpa").setExecutor(new CMDTpa());
        this.getCommand("tpaccept").setExecutor(new CMDTpAccept());
        this.getCommand("tpdeny").setExecutor(new CMDTpDeny());
        this.getCommand("cc").setExecutor(new CMDCc());
        this.getCommand("invsee").setExecutor(new CMDInvSee());
        this.getCommand("ec").setExecutor(new CMDEc());
        this.getCommand("sign").setExecutor(new CMDSign());
        this.getCommand("money").setExecutor(new CMDMoney());
        this.getCommand("pay").setExecutor(new CMDPay());
    }

    @Override
    public void onDisable() {
        VanishManager.setallvisible();
    }

    public static main getInstance(){
        return instance;
    }
}
