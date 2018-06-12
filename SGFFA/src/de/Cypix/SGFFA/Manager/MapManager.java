package de.Cypix.SGFFA.Manager;

import de.Cypix.SGFFA.API.ActionBar;
import de.Cypix.SGFFA.File.Var;
import de.Cypix.SGFFA.Main.main;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
                    MapManager.teleportallrandom();
                    CloudServer.getInstance().setMotdAndUpdate(currentMap);


                    //MapManager.getLocation(currentMap).getWorld().setDifficulty(Difficulty.PEACEFUL);

                    //MapManager.getLocation(currentMap).getWorld().setTime(6000);
                    //MapManager.getLocation(currentMap).getWorld().setGameRuleValue("doDaylightCycle", "false");

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


    public static Location teleportRandom(Player p){
        Random r = new Random();
        int i = r.nextInt(getSpawns(currentMap).size());
        p.teleport(getSpawns(currentMap).get(i));

        return getSpawns(currentMap).get(i);
    }

    public static void teleportallrandom(){
        List<Location> list = getSpawns(currentMap);
        List<Player> players = new ArrayList<Player>();
        for(Player p : Bukkit.getOnlinePlayers()){
            players.add(p);
        }
        for(int i = 0;i<players.size();i++){
            if(i>list.size()){
                players.get(i).teleport(list.get(i-i+1));
                if(players.get(i).hasPermission("Weakmc.sgffa")){
                    players.get(i).sendMessage("Bitte setzte mehr Spawns !");
                }
            }
            players.get(i).teleport(list.get(i));
        }

    }

    public static int addMapSpawn(String name, Location loc){
        int num = getSpawns(name).size();
        lm.saveLocation(loc, "Map.Loc."+name+"."+num);
        return num;
    }

    public static boolean mapexists(String name){
        if(getMaps().contains(name))return true;
        return false;
    }

    public static List<Location> getSpawns(String name){
        List<Location> list = new ArrayList<Location>();
        for(int i = 0;i<100;i++){
            if(cfg.getString("Map.Loc."+name+"."+i+".World") != null){
                list.add(lm.getLocation("Map.Loc."+name+"."+i));
            }else{
                return list;
            }
        }
        return list;
    }

    public static int getSpawnss(String name){
        int num = 0;
        for(int i = 0;i<100;i++){
            if(cfg.getString("Map.Loc."+name+"."+i+".World") != null){
                num++;
            }else{
                return num;
            }
        }
        return num;
    }

    public static ItemStack getItem(String map){
        if(cfg.getItemStack("Map.Item."+map+".Item") == null) return new ItemStack(Material.STAINED_GLASS_PANE, (byte) 0, (short)123);

        ItemStack i = cfg.getItemStack("Map.Item."+map+".Item");
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(map);
        i.setItemMeta(im);
        return i;
    }


    public static void setItem(String name, ItemStack i){
        cfg.set("Map.Item."+name+".Item", i);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

