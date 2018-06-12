package de.Cypix.Lobby.Manager;

import de.cypix.alphaapi.main.AlphaAPI;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getScoreboardManager;

public class ScoreBoardManager {

    private static int animations = 6;



    public static int layout;
    public static void sendScoreBoard(Player p){

        Scoreboard board = getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("aaa", "bbb");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(getDisplayName(6));

        /*for(int i = 0;i<13;i++){
            Team team = board.registerNewTeam(String.valueOf(i));
            team.addEntry(get(i));
            team.setPrefix(getAnimation(p).get(i));
            obj.getScore(get(i)).setScore(i);


        }*/
        for(int i = 0;i<13;i++){

            obj.getScore(getAnimation(p).get(i)).setScore(i);

        }
        if(OptionManager.isAllowScoreBoard(p)) {
            p.setScoreboard(board);
        }
    }


    public static String get(int i) {
        if(i == 0) return String.valueOf(ChatColor.WHITE);
        if(i == 1) return String.valueOf(ChatColor.RESET);
        if(i == 2) return String.valueOf(ChatColor.AQUA);
        if(i == 3) return String.valueOf(ChatColor.BLACK);
        if(i == 4) return String.valueOf(ChatColor.BLUE);
        if(i == 5) return String.valueOf(ChatColor.RED);
        if(i == 6) return String.valueOf(ChatColor.GREEN);
        if(i == 7) return String.valueOf(ChatColor.DARK_RED);
        if(i == 8) return String.valueOf(ChatColor.DARK_AQUA);
        if(i == 9) return String.valueOf(ChatColor.DARK_BLUE);
        if(i == 10) return String.valueOf(ChatColor.DARK_GRAY);
        if(i == 11) return String.valueOf(ChatColor.DARK_GREEN);
        if(i == 12) return String.valueOf(ChatColor.DARK_PURPLE);
        if(i == 13) return String.valueOf(ChatColor.LIGHT_PURPLE);
        if(i == 14) return String.valueOf(ChatColor.YELLOW);
        if(i == 15) return String.valueOf(ChatColor.GRAY);

        return null;
    }

    private static List<String> getAnimation(Player p){
        List<String> list = new ArrayList<String>();

        list.add("§e ");
        list.add("§8× §bweb.AlphaMC.de  ");
        list.add("§7Website");
        list.add("§4 ");
        list.add("§8× §bAlphaMC.de ");
        list.add("§7TeamSpeak");
        list.add("§l ");
        list.add("§8× §b"+getCoins(p));
        list.add("§7Coins");
        list.add("§8 ");
        list.add("§8× "+getRank(p));
        list.add("§7Dein Rang");
        list.add("§7 ");

        return list;
    }

    private static String getDisplayName(int i){
        /*if(i == 0)return "§8➤ §b§3WeakMC";
        if(i == 1)return "§8➤ §3W§be§3akMC";
        if(i == 2)return "§8➤ §3We§ba§3kMC";
        if(i == 3)return "§8➤ §3Wea§bk§3MC";
        if(i == 4)return "§8➤ §3WeakM§b§3C";
        if(i == 5)return "§8➤ §3WeakM§bC";*/
        /*if(i == 0)return "§8» §3WeakMC §8«";
        if(i == 1)return "§8» §bW§3eakMC §8«";
        if(i == 2)return "§8» §bWe§3akMC §8«";
        if(i == 3)return "§8» §bWea§3kMC §8«";
        if(i == 4)return "§8» §bWeak§3MC §8«";
        if(i == 5)return "§8» §bWeakM§3C §8«";
        if(i == 6)return " ";
        if(i == 7)return "§8» §bWeakMC §8«";
        if(i == 8)return " ";
        if(i == 9)return "§8» §bWeakM§3C §8«";
        if(i == 10)return "§8» §bWeak§3MC §8«";
        if(i == 11)return "§8» §bWea§3kMC §8«";
        if(i == 12)return "§8» §bWe§3akMC §8«";
        if(i == 13)return "§8» §bW§3eakMC §8«";
        if(i == 14)return "§8» §3WeakMC §8«";*/

        if(i == 6)return "§b§lAlphaMC";

        return " ";
    }

    private static String getRank(Player p){
        String rank = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getPrefix();
        rank = rank.replace("×", " ");
        rank = ChatColor.translateAlternateColorCodes('&', rank);
        return rank;
    }
    private static String getCoins(Player p){
        if(Bukkit.getPluginManager().getPlugin("AlphaAPI") != null) {
            return String.valueOf(AlphaAPI.getInstance().getStatsPlayer(p).getCoins().getCoins()+"");
        }
        return "AlphaAPI";
    }
    private static String getServer(Player p){
        return CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getServer()+" ";
    }
    private static String getPlayersonLobby(){
        return String.valueOf(CloudAPI.getInstance().getOnlineCount("Lobby")+" ");
    }
    private static String getPlayersServerPlayers(Player p){
        return String.valueOf(Bukkit.getOnlinePlayers().size()+ " ");

    }



}
