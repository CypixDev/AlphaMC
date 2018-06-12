package de.cypix.ttt.main;

import de.cypix.ttt.cmd.CMDBuild;
import de.cypix.ttt.cmd.CMDStart;
import de.cypix.ttt.cmd.CMDTTT;
import de.cypix.ttt.gamestates.GameState;
import de.cypix.ttt.gamestates.GameStateManager;
import de.cypix.ttt.listener.*;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.manager.VoteManager;
import de.cypix.ttt.mysql.MYSQL;
import de.cypix.ttt.mysql.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static Main Instance;
    private GameStateManager gameStateManager;
    private ArrayList<Player> players;
    private ArrayList<Player> specs;
    private boolean hit = false;

    @Override
    public void onEnable() {
        this.Instance = this;
        this.gameStateManager = new GameStateManager();
        players = new ArrayList<>();
        specs = new ArrayList<>();
        VoteManager.PutVotes();
        gameStateManager.setGameState(GameState.LOBBY_STATE);
        init();
        Bukkit.getConsoleSender().sendMessage("§4TTT §7wurde gestartet !");
        MYSQL my = new MYSQL("localhost", 3306, "Stats", "Stats", "NAklfz324");
        my.connect();
        my.createTable();
        VoteManager.PutVotes();
    }

    @Override
    public void onDisable() {
        PlayerStats.saveallplayersStats();
    }

    private void init(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerConnectionListener(gameStateManager), this);
        pm.registerEvents(new VoteListener(), this);
        pm.registerEvents(new TraitorChatListener(), this);
        pm.registerEvents(new TicketListener(), this);
        pm.registerEvents(new SomeListener(), this);
        pm.registerEvents(new ChestListener(), this);
        pm.registerEvents(new StatsListener(), this);
        pm.registerEvents(new TesterListener(), this);
        pm.registerEvents(new SpectatorListener(), this);

        this.getCommand("ttt").setExecutor(new CMDTTT());
        this.getCommand("build").setExecutor(new CMDBuild());
        this.getCommand("start").setExecutor(new CMDStart());
    }

    public static Main getInstance(){
        return Instance;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getSpecs() {
        return specs;
    }


    public boolean isHittingAllowed() {
        return hit;
    }
    public void setHitAllow() {hit = true;}
    public void setHitDisAllow() {hit = false;}

}
