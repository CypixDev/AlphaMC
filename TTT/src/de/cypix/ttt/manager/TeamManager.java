package de.cypix.ttt.manager;

import de.cypix.ttt.Var;
import de.cypix.ttt.api.Title;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.mysql.PlayerStats;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class TeamManager {


    private static ArrayList<Player> traitor = new ArrayList<>();
    private static ArrayList<Player> detectives = new ArrayList<>();

    private static HashMap<Player, String> use = new HashMap<>();

    public static ArrayList<Player> getTraitors() {
        return traitor;
    }

    public static void addTraitor(Player p){
        if(traitor.contains(p))return;
        p.sendMessage("Mit §c@t §7kannst du mit deinen Traitor Kollegen schreiben !");
        traitor.add(p);
    }
    public static boolean isTraitor(Player p){
        if(traitor.contains(p))return true;
        return false;
    }

    public static ArrayList<Player> getDetectives() {
        return detectives;
    }

    public static boolean isDetective(Player p){
        if(detectives.contains(p))return true;
        return false;
    }

    public static void addDetective(Player p){
        if(detectives.contains(p)) return;
        detectives.add(p);
    }

    public static void addToUseList(Player p, String team){
        use.put(p, team);
    }

    public static void setTeams(){
        while(getMaxTraitors() > getTraitors().size()){
            setTraitor();
        }
        while(getMaxDetectives() > getDetectives().size()){
            setDetective();
        }
        TablistManager.updateScoreBoard();
    }

    private static void setTraitor(){
        Random r = new Random();
        ArrayList<Player> list = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!traitor.contains(p) && !detectives.contains(p)){
                list.add(p);
            }
        }
        Player target = list.get(r.nextInt(list.size()));
        target.sendMessage("Mit §c@t §7kannst du mit deinen Traitor Kollegen schreiben !");
        traitor.add(target);
    }
    private static void setDetective(){
        Random r = new Random();
        ArrayList<Player> list = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()){
            if(!traitor.contains(p) && !detectives.contains(p)){
                list.add(p);
            }
        }
        Player target = list.get(r.nextInt(list.size()));
        detectives.add(target);
    }

    public static void RemoveTickets(){
        for(Player p : Main.getInstance().getPlayers()){
            if(use.containsKey(p)){
                PlayerStats ps = new PlayerStats(p);
                if(use.get(p) == "Traitor") ps.removeTraitorPass();
                if(use.get(p) == "Detective") ps.removeDetectivePass();
                use.remove(p);
            }
        }
    }

    public static int getMaxTraitors(){
        int players = Main.getInstance().getPlayers().size();
        if(players == 0) return 0;
        if(players == 1) return 1;
        if(players == 2) return 1;
        if(players == 3) return 1;
        if(players == 4) return 1;
        if(players == 5) return 2;
        if(players == 6) return 2;
        if(players == 7) return 2;
        if(players == 8) return 3;
        if(players == 9) return 3;
        if(players == 10) return 3;
        if(players == 11) return 4;
        if(players == 12) return 4;


        return 1;
    }

    public static int getMaxDetectives(){
        int players = Main.getInstance().getPlayers().size();
        if(players == 0) return 0;
        if(players == 1) return 1;
        if(players == 2) return 1;
        if(players == 3) return 1;
        if(players == 4) return 1;
        if(players == 5) return 1;
        if(players == 6) return 2;
        if(players == 7) return 2;
        if(players == 8) return 2;
        if(players == 9) return 2;
        if(players == 10) return 2;
        if(players == 11) return 2;
        if(players == 12) return 2;


        return 1;
    }

    public static String sayWhatheis(Player p) {
        if(Main.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState){
            return ".....";
        }else {
            if (traitor.contains(p)) return "§cTraitor";
            if (detectives.contains(p)) return "§3Detectives";
            return "§aInnocent";
        }
    }
    public static ArrayList<Player> getListofTeam(Player p){
        if(traitor.contains(p))return traitor;
        if(detectives.contains(p))return detectives;
        ArrayList<Player> list = new ArrayList<>();
        for(Player pp : Main.getInstance().getPlayers()){
            if(!traitor.contains(pp) && !detectives.contains(pp)){
                list.add(pp);
            }
        }
        return list;
    }

    public static ArrayList<Player> getInnocents(){
        ArrayList<Player> list = new ArrayList<>();
        for(Player pp : Main.getInstance().getPlayers()){
            if(!traitor.contains(pp) && !detectives.contains(pp)){
                list.add(pp);
            }
        }
        return list;
    }

    private static int endingsec = 10;

    public static void Check(){
        if(traitor.size() == 0 || (detectives.size() == 0 && getInnocents().size() == 0)){
            Main.getInstance().setHitDisAllow();
            String winners;
            if(traitor.size() == 0){
                winners = "Innocents";
                for(Player p : Main.getInstance().getPlayers()){
                    if(!traitor.contains(p)){
                        PlayerStats ps = new PlayerStats(p);
                        ps.addWin();
                    }
                }
            }else{
                winners = "Traitors";
                for(Player p : Main.getInstance().getPlayers()){
                    if(traitor.contains(p)){
                        PlayerStats ps = new PlayerStats(p);
                        ps.addWin();
                    }
                }
            }

            for(Player p : Bukkit.getOnlinePlayers()){
                Title.sendTitle(p, "Die "+winners, "haben das Spiel Gewonnen", 20, 40 ,20);
            }
            Bukkit.broadcastMessage(Var.pr+"Die "+winners+" haben Gewonnen !");



            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {

                    switch (endingsec){

                        case 10: case 9: case 8: case 7: case 6: case 5: case 4: case 3: case 2:
                            Bukkit.broadcastMessage(Var.pr+"Der Server startet in "+endingsec+" Sekunden neu !");
                            break;

                        case 1:
                            Bukkit.broadcastMessage(Var.pr+"Der Server startet in einer Sekunde neu !");
                        case 0:
                            Bukkit.broadcastMessage(Var.pr+"Der Server startet jetzt neu !");
                            for(Player p : Bukkit.getOnlinePlayers()){
                                sendPlayer(p.getUniqueId() ,"Lobby-1");
                            }
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    Bukkit.shutdown();
                                }
                            },10);
                            break;
                    }
                    endingsec--;
                }
            },  0, 20);
        }
    }

    private static void sendPlayer(UUID uuid, String server) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(uuid);
        PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
        playerExecutorBridge.sendPlayer(cloudPlayer,  server);
    }
    public static void removeFromTeam(Player p){
        if(traitor.contains(p))traitor.remove(p);
        if(detectives.contains(p))detectives.remove(p);
    }

    public static void addGame() {
        for(Player p: Main.getInstance().getPlayers()){
            new PlayerStats(p).addGame();
        }
    }
}
