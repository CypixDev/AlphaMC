package de.cypix.flash.manager;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.cypix.flash.countdowns.IngameCountdown;
import de.cypix.flash.main.Main;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreBoardManager {

    private static int animations = 0;

    public static int layout = 0;

    /*public static void sendScoreBoard(Player p){

        Scoreboard board = getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("aaa", "bbb");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(getDisplayName(layout));

        for(int i = 0;i<13;i++){
            Team team = board.registerNewTeam(String.valueOf(i));
            team.addEntry(get(i));
            team.setPrefix(getAnimation(p, layout).get(i));
            obj.getScore(get(i)).setScore(i);
        }

        p.setScoreboard(board);
    }*/

    /*public static void updateScoreBoard(Player p){

        Scoreboard board = p.getScoreboard();

        Objective obj = board.getObjective(DisplaySlot.SIDEBAR);
        obj.setDisplayName(getDisplayName(layout));

        for(int i = 0;i<13;i++){
            board.getTeam(String.valueOf(i)).setPrefix(getAnimation(p, layout).get(i));
        }

    }*/



    public static String get(int i) {
        if(i == 0) return ChatColor.AQUA.toString();
        if(i == 1) return ChatColor.DARK_AQUA.toString();
        if(i == 2) return ChatColor.BLUE.toString();
        if(i == 3) return ChatColor.DARK_BLUE.toString();
        if(i == 4) return ChatColor.GREEN.toString();
        if(i == 5) return ChatColor.DARK_GREEN.toString();
        if(i == 6) return ChatColor.GRAY.toString();
        if(i == 7) return ChatColor.DARK_GRAY.toString();
        if(i == 8) return ChatColor.RED.toString();
        if(i == 9) return ChatColor.DARK_RED.toString();
        if(i == 10) return ChatColor.DARK_PURPLE.toString();
        if(i == 11) return ChatColor.LIGHT_PURPLE.toString();
        if(i == 12) return ChatColor.ITALIC.toString();
        if(i == 13) return ChatColor.GOLD.toString();
        if(i == 14) return ChatColor.MAGIC.toString();
        if(i == 15) return ChatColor.UNDERLINE.toString();
        if(i == 16) return ChatColor.YELLOW+""+ChatColor.WHITE;
        if(i == 17) return ChatColor.STRIKETHROUGH+""+ChatColor.WHITE;
        if(i == 18) return ChatColor.BOLD+""+ChatColor.WHITE;
        if(i == 19) return ChatColor.WHITE+""+ChatColor.WHITE;
        if(i == 20) return ChatColor.RESET+""+ChatColor.WHITE;

        return null;
    }

    private static List<String> getAnimation(Player p, int i){
        if(i == 0){
            List<String> list = new ArrayList<String>();
            list.add("§e ");
            list.add("§8➥ §b§l"+ IngameCountdown.getTime());
            list.add("§7Zeit");
            list.add("§e ");
            list.add("§8➥ §b"+ Main.getInstace().getPlayers().size());
            list.add("§7Spieler");
            list.add("§e ");
            list.add("§8➥ §b"+MapManager.currentmap);
            list.add("§7MAP");
            list.add("§b ");
            list.add("§8➥ §b§l"+CloudAPI.getInstance().getServerId());
            list.add("§7Server");
            list.add("§7 ");
            return list;
        }



        return null;
    }



    private static String getDisplayName(int i){
        return "§8× §b§lFLASH §8×";
    }

    private static String getRank(Player p){
        return CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getName();
    }
    private static String getCoins(Player p){
        if(Bukkit.getPluginManager().getPlugin("CoinsAPI") != null) {
            return String.valueOf(Coins.getCoins(p));
        }
        return "COINSAPI NOT FOUND";
    }
    private static String getServer(Player p){
        return CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()).getServer();
    }
    private static String getPlayersonLobby(){
        return String.valueOf(CloudAPI.getInstance().getOnlineCount("Lobby"));
    }
    private static String getPlayersServerPlayers(Player p){
        return String.valueOf(Bukkit.getOnlinePlayers().size());
    }


    public static void sendScoreBoard(Player p){
        /*Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.getObjective("aaa") != null ? board.getObjective("aaa") : board.registerNewObjective("aaa", "bbb");

        obj.setDisplayName(getDisplayName(1));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        for(int i = 0;i<13;i++){
            Team team = board.registerNewTeam(String.valueOf(i));
            team.setPrefix(getAnimation(p, 0).get(i));
            team.addEntry(get(i));
            obj.getScore(get(i)).setScore(i);
        }

        p.setScoreboard(board);
        boards.put(board, p);*/







        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("aaa", "bbb");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(getDisplayName(1));



        Team line0 = board.registerNewTeam("line0");
        line0.setPrefix(getAnimation(p, 0).get(0));
        line0.addEntry(ChatColor.AQUA.toString());

        Team line1 = board.registerNewTeam("line1");
        line1.setPrefix(getAnimation(p, 0).get(1));
        line1.addEntry(ChatColor.BLACK.toString());

        Team line2 = board.registerNewTeam("line2");
        line2.setPrefix(getAnimation(p, 0).get(2));
        line2.addEntry(ChatColor.BLUE.toString());

        Team line3 = board.registerNewTeam("line3");
        line3.setPrefix(getAnimation(p, 0).get(3));
        line3.addEntry(ChatColor.DARK_AQUA.toString());

        Team line4 = board.registerNewTeam("line4");
        line4.setPrefix(getAnimation(p, 0).get(4));
        line4.addEntry(ChatColor.DARK_BLUE.toString());

        Team line5 = board.registerNewTeam("line5");
        line5.setPrefix(getAnimation(p, 0).get(5));
        line5.addEntry(ChatColor.DARK_GRAY.toString());

        Team line6 = board.registerNewTeam("line6");
        line6.setPrefix(getAnimation(p, 0).get(6));
        line6.addEntry(ChatColor.DARK_GREEN.toString());

        Team line7 = board.registerNewTeam("line7");
        line7.setPrefix(getAnimation(p, 0).get(7));
        line7.addEntry(ChatColor.DARK_PURPLE.toString());

        Team line8 = board.registerNewTeam("line8");
        line8.setPrefix(getAnimation(p, 0).get(8));
        line8.addEntry(ChatColor.DARK_RED.toString());

        Team line9 = board.registerNewTeam("line9");
        line9.setPrefix(getAnimation(p, 0).get(9));
        line9.addEntry(ChatColor.GOLD.toString());

        Team line10 = board.registerNewTeam("line10");
        line10.setPrefix(getAnimation(p, 0).get(10));
        line10.addEntry(ChatColor.GRAY.toString());

        Team line11 = board.registerNewTeam("line11");
        line11.setPrefix(getAnimation(p, 0).get(11));
        line11.addEntry(ChatColor.GREEN.toString());

        Team line12 = board.registerNewTeam("line12");
        line12.setPrefix(getAnimation(p, 0).get(12));
        line12.addEntry(ChatColor.LIGHT_PURPLE.toString());

        /*Team line13 = board.registerNewTeam("line13");
        line13.addEntry(ChatColor.DARK_GRAY.toString());
        line13.setPrefix(getAnimation(p, 0).get(13));*/






        obj.getScore(ChatColor.AQUA.toString()).setScore(0);
        obj.getScore(ChatColor.BLACK.toString()).setScore(1);
        obj.getScore(ChatColor.BLUE.toString()).setScore(2);
        obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(3);
        obj.getScore(ChatColor.DARK_BLUE.toString()).setScore(4);
        obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(5);
        obj.getScore(ChatColor.DARK_GREEN.toString()).setScore(6);
        obj.getScore(ChatColor.DARK_PURPLE.toString()).setScore(7);
        obj.getScore(ChatColor.DARK_RED.toString()).setScore(8);
        obj.getScore(ChatColor.GOLD.toString()).setScore(9);
        obj.getScore(ChatColor.GRAY.toString()).setScore(10);
        obj.getScore(ChatColor.GREEN.toString()).setScore(11);
        obj.getScore(ChatColor.LIGHT_PURPLE.toString()).setScore(12);
        //obj.getScore(ChatColor.DARK_GRAY.toString()).setScore(13);

        boards.put(board, p);
        p.setScoreboard(board);

        /*Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("aaa", "bbb");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§b§lFFA");

        Team coins = board.registerNewTeam("coins");
        coins.setSuffix(Coins.getCoins(p)+"");
        coins.setPrefix("Coins: ");
        coins.addEntry(ChatColor.AQUA.toString());

        obj.getScore(ChatColor.AQUA.toString()).setScore(0);

        boards.put(board, p);

        p.setScoreboard(board);*/

    }

    public static void updateScoreBoard() {
        /*if (p.getScoreboard() != null) {
            Objective obj = p.getScoreboard().getObjective("aaa") != null ? p.getScoreboard().getObjective("aaa") : p.getScoreboard().registerNewObjective("aaa", "bbb");



            for(int i = 0;i<13;i++){
                obj.getScore(getAnimation(p ,0).get(i)).setScore(i);
            }
        }*/
        /*for(Scoreboard board : boards.keySet()){
            Player p = boards.get(board);
            for(int i = 0;i<13;i++){
                board.getTeam(String.valueOf(i)).setPrefix(getAnimation(p, 0).get(i));
            }
        }*/






        for(Scoreboard board : boards.keySet()){
            Player p = boards.get(board);
            board.getTeam("line0").setPrefix(getAnimation(p, 0).get(0));
            board.getTeam("line1").setPrefix(getAnimation(p, 0).get(1));
            board.getTeam("line2").setPrefix(getAnimation(p, 0).get(2));
            board.getTeam("line3").setPrefix(getAnimation(p, 0).get(3));
            board.getTeam("line4").setPrefix(getAnimation(p, 0).get(4));
            board.getTeam("line5").setPrefix(getAnimation(p, 0).get(5));
            board.getTeam("line6").setPrefix(getAnimation(p, 0).get(6));
            board.getTeam("line7").setPrefix(getAnimation(p, 0).get(7));
            board.getTeam("line8").setPrefix(getAnimation(p, 0).get(8));
            board.getTeam("line9").setPrefix(getAnimation(p, 0).get(9));
            board.getTeam("line10").setPrefix(getAnimation(p, 0).get(10));
            board.getTeam("line11").setPrefix(getAnimation(p, 0).get(11));
            board.getTeam("line12").setPrefix(getAnimation(p, 0).get(12));
           //board.getTeam("line13").setPrefix(getAnimation(p, 0).get(13));

        }

        /*for(Scoreboard board : boards.keySet()){
            board.getTeam("coins").setSuffix("Hey");
            }*/

    }

    public static HashMap<Scoreboard, Player> boards = new HashMap<>();

}

