package de.Cypix.SGFFA.Manager;

import de.Cypix.SGFFA.File.Var;
import de.Cypix.SGFFA.MYSQL.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class KillStreakManager {

    public static HashMap<Player, Integer> list = new HashMap<Player, Integer>();

    public static int getKillStreak(Player p){
        if(list.containsKey(p)) {
            return list.get(p);
        }else{
            list.put(p, 0);
            return list.get(p);
        }
    }

    public static void addPlayer(Player p){
        list.put(p, 0);
    }

    public static void addAllPlayer(){
        for(Player p : Bukkit.getOnlinePlayers()){
            list.put(p, 0);
        }
    }
    public static void resetKillStreak(Player p){
        list.put(p, 0);
        ScoreBoardManager.updateScoreBoard();
    }
    public static void addKillStreak(Player p){
        list.put(p, list.get(p)+1);

        switch(list.get(p)){
            case 5: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b5");
                p.sendMessage(Var.pr+"Du hast §b25 Coins §7dazu bekommen!");
                break;
            case 10: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b10");
                p.sendMessage(Var.pr+"Du hast §b50 Coins §7dazu bekommen!");
                break;
            case 15: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b15");
                p.sendMessage(Var.pr+"Du hast §b75 Coins §7dazu bekommen!");
                break;
            case 20: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b20");
                p.sendMessage(Var.pr+"Du hast §b100 Coins §7dazu bekommen!");
                break;
            case 30: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b30");
                p.sendMessage(Var.pr+"Du hast §b150 Coins §7dazu bekommen!");
                break;
            case 40: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b40");
                p.sendMessage(Var.pr+"Du hast §b200 Coins §7dazu bekommen!");
                break;
            case 50: Bukkit.broadcastMessage(Var.pr+p.getName()+" hat einen §bKillStreak §7von §b50");
                p.sendMessage(Var.pr+"Du hast §b250 Coins §7dazu bekommen!");
                break;

        }

        if(getKillStreak(p) >= new PlayerStats(p).getKillStreak()) new PlayerStats(p).setKillstreak(getKillStreak(p));
        ScoreBoardManager.updateScoreBoard();
    }


}
