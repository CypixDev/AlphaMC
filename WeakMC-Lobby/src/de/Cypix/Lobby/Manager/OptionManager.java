package de.Cypix.Lobby.Manager;

import de.Cypix.Lobby.MYSQL.MYSQL;
import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.settings.LobbyOption;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptionManager {

    public static boolean isAllowTeleportAnimation(Player p){
        if(!AlphaAPI.getInstance().getStatsPlayer(p).getSettings().getLobbySettings().getOptionlist().containsKey(LobbyOption.TeleportAnimation))return true;
        return false;
    }

    public static boolean isAllowAnimation(Player p){
        if(!AlphaAPI.getInstance().getStatsPlayer(p).getSettings().getLobbySettings().getOptionlist().containsKey(LobbyOption.NaigatorAnimation))return true;
        return false;
    }
    public static boolean isAllowScoreBoard(Player p){
        if(!AlphaAPI.getInstance().getStatsPlayer(p).getSettings().getLobbySettings().getOptionlist().containsKey(LobbyOption.ScoreBoard)) return true;
        return false;
    }
    public static boolean isAllowEffect(Player p){
        if(!AlphaAPI.getInstance().getStatsPlayer(p).getSettings().getLobbySettings().getOptionlist().containsKey(LobbyOption.Effects))return true;
        return false;
    }

}
