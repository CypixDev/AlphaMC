package de.Cypix.Survival.Manager;


import de.Cypix.Survival.Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HomeManager {

    public static HashMap<Player, Integer> idelID = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> iedl = new HashMap<Player, Integer>();

    File file;
    FileConfiguration cfg;
    PlayerManager pm;
    Player p;

    public HomeManager(Player p){
        this.pm = new PlayerManager(p);
        this.file = pm.getFile();
        this.cfg = pm.getCfg();
        this.p = p;
    }

    public void saveHome(String name){

        String home = name.toLowerCase();

        cfg.set("Home.Loc."+home+".World", p.getWorld().getName());
        cfg.set("Home.Loc."+home+".X", p.getLocation().getX());
        cfg.set("Home.Loc."+home+".Y", p.getLocation().getY());
        cfg.set("Home.Loc."+home+".Z", p.getLocation().getZ());
        cfg.set("Home.Loc."+home+".Yaw", p.getLocation().getYaw());
        cfg.set("Home.Loc."+home+".Pitch", p.getLocation().getPitch());

        cfg.set("Home.Name."+home+".Name", name);


        List<String> list = getHomes();
        list.add(home);
        cfg.set("Home.Homes", list);

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(String home){
        return cfg.getString("Home.Name."+home.toLowerCase()+".Name");
    }

    public List<String> getHomes(){
        return cfg.getStringList("Home.Homes");
    }

    public void setItem(String home, ItemStack i){
        cfg.set("Home.Item."+home.toLowerCase()+".Item", i);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean homeexists(String home){
        String name = home.toLowerCase();
        if(getHomes().contains(name)) return true;
        return false;
    }

    public Location getLocation(String home){
        World world = Bukkit.getWorld(cfg.getString("Home.Loc."+home+".World"));
        double x = cfg.getDouble("Home.Loc."+home+".X");
        double y = cfg.getDouble("Home.Loc."+home+".Y");
        double z = cfg.getDouble("Home.Loc."+home+".Z");
        float yaw = (float) cfg.getDouble("Home.Loc."+home+".Yaw");
        float pitch = (float) cfg.getDouble("Home.Loc."+home+".Pitch");

        Location loc = new Location(world, x, y ,z ,yaw ,pitch);
        return loc;
    }

    public ItemStack getItem(String home){

        if(cfg.getItemStack("Home.Item."+home+".Item") == null){
            ItemStack i = new ItemStack(Material.GRASS);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(getName(home));
            i.setItemMeta(im);
            i.setAmount(1);
            return i;
        }

        ItemStack i = cfg.getItemStack("Home.Item."+home+".Item");
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(getName(home));
        i.setItemMeta(im);
        i.setAmount(1);

        return i;
    }

    public int getHighestHome(){
        int home = 0;
        if(!p.hasPermission("Survival.Home.amount.unlimeted")) {
            for (int i = 0; i < 100; i++) {
                if (p.hasPermission("Survival.Home.amount." + i)) {
                    home = i;
                    break;
                }
            }

        }else{
            return -2;
        }
        return -1;
    }



}
