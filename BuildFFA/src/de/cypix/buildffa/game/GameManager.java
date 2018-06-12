package de.cypix.buildffa.game;

import de.cypix.buildffa.main.BuildFFA;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    private BuildFFA instance;
    private int sec;
    private Map currentmap;
    private final int MAPSECONDS = 3900;
    private List<Player> death;

    public GameManager(BuildFFA instance){
        this.instance = instance;
        this.sec = MAPSECONDS;
        this.death = new ArrayList<>();
        this.currentmap = new Map("noname");
        startScheduler();
    }


    public void startScheduler(){
        if(BuildFFA.getMaps().size() < 2){
            Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
                @Override
                public void run() {
                    Bukkit.broadcastMessage("Es müssen noch maps hinzugefügt werden !");
                }
            },800, 800);
        }else{
            Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
                @Override
                public void run() {
                    sec--;
                    //updaten
                    //ScoreBoard

                    for(Player p : Bukkit.getOnlinePlayers()){
                        sendActionBar(p, getTime());
                        ScoreBoardManager.updateScoreBoard();
                    }
                    if(sec == 0){
                        sec = MAPSECONDS;
                        choosNewMap();
                    }
                }
            },0, 20);
        }
    }

    private void choosNewMap(){
        List<String> maps = BuildFFA.getMaps();
        Random r = new Random();
        //currentmap.getVillagerEntity().remove();
        int random = r.nextInt(maps.size());
        currentmap = new Map(maps.get(random));
    }

    public List<Player> getDeath() {
        return death;
    }
    public void addDeath(Player p){
        death.add(p);
    }

    public String getTime(){
        String time = "";
        int sec = this.sec;
        int h = 0;
        int m = 0;
        while(sec > 3600){
            h++;
            sec-=3600;
        }
        while(sec > 60){
            m++;
            sec-=60;
        }
        if(h != 0) time = h+":";
        if(m != 0) time = time+m+":";
        time = time+sec;
        return time;
    }

    public Map getCurrentmap() {
        return currentmap;
    }


    public static void sendActionBar(Player player, String Nachricht) {
        final String NachrichtNeu = Nachricht.replace("_", " ");
        String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
