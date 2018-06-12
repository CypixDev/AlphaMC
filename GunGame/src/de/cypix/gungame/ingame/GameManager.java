package de.cypix.gungame.ingame;

import de.cypix.gungame.main.GunGame;
import de.cypix.gungame.setup.Setup;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Random;

public class GameManager {

    private Map currentmap;
    private String preferredMap;
    private final int RESET_SECONDS = 80;
    private int sec;
    private boolean isRunning;
    private boolean preferred;

    public GameManager(){
        isRunning = false;
        preferred = false;
        currentmap = new Map(Setup.getMaps().get(0));
        sec = 80;
        startScheduler();
    }

    private void startScheduler(){
        if(Setup.getMaps().size() > 1){
            isRunning = true;
            Bukkit.getScheduler().scheduleSyncRepeatingTask(GunGame.getInstance(), new Runnable() {
                @Override
                public void run() {
                    sec--;

                    for(Player p : Bukkit.getOnlinePlayers()){
                        sendActionBar(p, "§b§lZeit bis zur nächsten Map §7-> §r§l"+sec);
                    }

                    if(sec <= 0){
                        sec = RESET_SECONDS;
                        if(!preferred){
                            choosNewMap();
                        }else{
                            currentmap = new Map(preferredMap);
                        }
                        preferred = false;
                        ScoreBoardManager.updateScoreBoard();
                    }

                }
            }, 0, 20);
        }
    }

    public void setSec(int sec){
        this.sec = sec;
    }

    private void choosNewMap() {
        Random r = new Random();
        currentmap = new Map(Setup.getMaps().get(r.nextInt(Setup.getMaps().size())));
    }

    public boolean isMapPreferred() {
        return preferred;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getSec() {
        return sec;
    }

    public int getRESET_SECONDS() {
        return RESET_SECONDS;
    }

    public Map getCurrentmap() {
        return currentmap;
    }

    public void setPreferredMap(String preferredMap) {
        preferred = true;
        this.preferredMap = preferredMap;
    }

    public static void sendActionBar(Player player, String Nachricht) {
        final String NachrichtNeu = Nachricht.replace("_", " ");
        String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
    }
}
