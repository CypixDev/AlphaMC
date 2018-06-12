package de.cypix.flash.main;

import de.cypix.flash.cmd.*;
import de.cypix.flash.gamestates.GameState;
import de.cypix.flash.gamestates.GameStateManager;
import de.cypix.flash.listener.*;
import de.cypix.flash.manager.VoteManager;
import de.cypix.flash.mysql.MYSQL;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {
    private static Main instace;
    private GameStateManager gameStatemanager;
    private ArrayList<Player> players;
    public static String pr = "§b§lFlash §7-> ", noperm = "§cDazu hast du keine Rechte !";

    @Override
    public void onEnable() {
        System.out.println("Plugin wird geladen !");
        CloudServer.getInstance().setMaxPlayers(10);
        CloudServer.getInstance().setMotdAndUpdate("Voting");
        instace = this;
        gameStatemanager = new GameStateManager();
        players = new ArrayList<>();
        gameStatemanager.setGameState(GameState.LOBBY_STATE);
        VoteManager.PutVotes();
        register();
        System.out.println("Das Plguin hat Erfolgreich gestartet !");

        MYSQL my = new MYSQL("localhost", 3306, "Stats", "Stats", "NAklfz324");
        my.connect();
        my.createTable();

    }
    private void register(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerConnectionListener(gameStatemanager), this);
        pm.registerEvents(new SomeListener(), this);
        pm.registerEvents(new VoteListener(), this);
        pm.registerEvents(new CheckPointListener(), this);
        pm.registerEvents(new MapListener(), this);
        this.getCommand("start").setExecutor(new CMDStart());
        this.getCommand("build").setExecutor(new CMDBuild());
        this.getCommand("flash").setExecutor(new CMDFlash());
        this.getCommand("troll").setExecutor(new CMDTroll());
        //this.getCommand("vote").setExecutor(new CMDVote());
    }

    public static Main getInstace(){
        return instace;
    }

    public GameStateManager getGameStatemanager() {
        return gameStatemanager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
