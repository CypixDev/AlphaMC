package de.Cypix.Belohnung.Manager;

import de.Cypix.Belohnung.API.EntityFreeze;
import de.Cypix.Belohnung.API.Item;
import de.Cypix.Belohnung.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BelohnungManager {

    public static List<Player> list = new ArrayList<Player>();
    private static File file = new File(main.getInstance().getDataFolder(), "config.yml");
    private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static Inventory getInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 3*9, "§aTägliche beloohnung");

        ItemStack placeholder = new ItemStack(Material.getMaterial(160), (short)7, (byte)7 );
        ItemMeta placeholderm = placeholder.getItemMeta();
        placeholderm.setDisplayName("");
        placeholder.setItemMeta(placeholderm);
        placeholder.setAmount(1);



        for(int i = 0;i<inv.getSize();i++){
            inv.setItem(i, placeholder);
        }

        if(canTake(p)){
            List<String> lore = new ArrayList<String>();

            inv.setItem(13, Item.getItem(Material.PAPER, "§aGönn dir !", lore, 1));
        }else{

            List<String> lore = new ArrayList<String>();
            lore.add("§c"+getRemainingTime(p));
            lore.add("§cWarten");
            inv.setItem(13, Item.getItem(Material.PAPER, "§aDu must noch: ", lore, 0));
        }


        return inv;
    }

    public static boolean canTake(Player p){
        long current = System.currentTimeMillis();
        long millis = cfg.getLong("Belohnung.Player."+p.getUniqueId()+".milli") - current;

        if(millis <= 0){
            return true;
        }

        return false;
    }

    public static String getRemainingTime(Player p){

        long current = System.currentTimeMillis();
        long millis = cfg.getLong("Belohnung.Player."+p.getUniqueId()+".milli") - current;

        int seconds = 0;
        int minuets = 0;
        int hours = 0;
        int days = 0;
        int weeks = 0;
        int month = 0;
        int years = 0;

        while(millis >= 1000){
            millis-=1000;
            seconds++;
        }
        while(seconds >= 60){
            seconds -=60;
            minuets++;
        }
        while(minuets >= 60){
            minuets -=60;
            hours++;
        }
        while(hours >= 24){
            hours-=24;
            days++;
        }
        while(days >= 7){
            days -=7;
            weeks++;
        }
        while(weeks >= 4){
            weeks-=4;
            month++;
        }
        while(month > 12){
            month -=12;
            years++;
        }

        String msg = "";
        if(years != 0) msg = msg+years+" Jahre | ";
        if(month != 0) msg = msg+month+" Monate | ";
        if(weeks != 0) msg = msg+weeks+" Wochen | ";
        if(days != 0) msg = msg+days+" Tage | ";
        if(hours != 0) msg = msg+hours+" Stunden | ";
        if(minuets != 0) msg = msg+minuets+" Minuten | ";
        if(seconds != 0) msg = msg+seconds+" Sekunden ";

        if(msg == "")return millis+"";

        return msg;
    }

    public static void setGet(Player p){
        //86400 == 24 Std !
        long seconds = 86400*1000;
        long current = System.currentTimeMillis();

        long end = seconds+current;

        cfg.set("Belohnung.Player."+p.getUniqueId()+".milli", end);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setVillager(Player p){
        Villager v = (Villager) p.getWorld().spawnCreature(p.getLocation(), EntityType.VILLAGER);
        EntityFreeze.freeze(v);


        v.setCustomName("§aTägliche Belohnung !");
        v.setCustomNameVisible(true);

        saveLocation(p.getLocation());

    }

    private static void saveLocation(Location loc){
        cfg.set("Villager.Location.World", loc.getWorld().getName());
        cfg.set("Villager.Location.X", loc.getX());
        cfg.set("Villager.Location.Y", loc.getY());
        cfg.set("Villager.Location.Z", loc.getZ());
        cfg.set("Villager.Location.Yaw", loc.getYaw());
        cfg.set("Villager.Location.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
