package de.Cypix.FFA.Manager;

import de.Cypix.FFA.API.ActionBar;
import de.Cypix.FFA.File.Var;
import de.Cypix.FFA.Main.main;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MapManager {

    public static String currentMap;
    private static int maptime = 500;
    public static int sec = 1;
    public static String forceMap;

    private static File file = new File(main.getinstance().getDataFolder(), "maps.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    private static LocationManager lm = new LocationManager(file, cfg);


    public static void startMaps(){
        if(getMaps().size() == 0) return;
        currentMap = getMaps().get(0);
        setNewForceMap();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getinstance(), new Runnable() {
            @Override
            public void run() {
                    CloudServer.getInstance().setMotdAndUpdate(currentMap);
                    sec--;
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        ScoreBoardManager.updateScoreBoard();
                        ActionBar.sendActionBar(p, "§7Nächste Map in §b" + sec + " §7Sekunden");
                    }
                    if (sec <= 0) {
                        sec = maptime;
                        currentMap = forceMap;
                        setNewForceMap();
                        TeleportPlayers();
                        CloudServer.getInstance().setMotdAndUpdate(currentMap);
                        MapManager.getLocation(currentMap).getWorld().setDifficulty(Difficulty.PEACEFUL);

                        MapManager.getLocation(currentMap).getWorld().setTime(6000);
                        MapManager.getLocation(currentMap).getWorld().setGameRuleValue("doDaylightCycle", "false");

                        return;
                    }

                    switch (sec) {

                        case 1:
                            Bukkit.broadcastMessage(Var.pr + "§7Map wechselt in §b1 §7Sekunde!");
                            break;
                        case 2:
                            Bukkit.broadcastMessage(Var.pr + "§7Map wechselt in §b2 §7Sekunden!");
                            break;
                        case 3:
                            Bukkit.broadcastMessage(Var.pr + "§7Map wechselt in §b3 §7Sekunden!");
                            break;
                    }

            }
        },20, 20);
    }


    public static void setNewForceMap() {
        Random r = new Random();

        if(getMaps().size() == 0){
            forceMap = currentMap;
        }
        String ok = getMaps().get(r.nextInt(getMaps().size()));
        if(ok == currentMap){
            setNewForceMap();
        }else{
            forceMap = ok;
        }

    }

    public static List<String> getMaps(){
        return cfg.getStringList("Maps");
    }


    public static void createMap(String name){
        List<String> maps = getMaps();
        maps.add(name);
        cfg.set("Maps", maps);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void TeleportPlayers(){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.teleport(getLocation(currentMap));
            p.setHealth(20);
        }
    }

    public static void setMapSpawn(String name, Location loc){
        lm.saveLocation(loc, "Map.Loc."+name);
    }
    public static boolean isSPawnset(String name){
        if(cfg.getString("Map.Loc."+name+".World") != null) return true;
        return false;
    }

    public static boolean mapexists(String name){
        if(getMaps().contains(name))return true;
        return false;
    }

    public static ItemStack getItem(String map){
        if(cfg.getItemStack("Map.Item."+map+".Item") == null) return new ItemStack(Material.STAINED_GLASS_PANE, (byte) 0, (short)123);

        ItemStack i = cfg.getItemStack("Map.Item."+map+".Item");
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(map);
        i.setItemMeta(im);
        return i;
    }


    public static boolean isReady(String name){
        if(getMaps().contains(name) && cfg.getString("Map.Loc."+name+".World") != null){
            return true;
        }
        return false;
    }

    public static void setItem(String name, ItemStack i){
        cfg.set("Map.Item."+name+".Item", i);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Location getLocation(String name){
        return lm.getLocation("Map.Loc."+name);
    }

}
