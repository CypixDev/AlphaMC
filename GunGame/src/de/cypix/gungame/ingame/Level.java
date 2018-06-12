package de.cypix.gungame.ingame;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.gungame.main.GunGame;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {

    File file;
    FileConfiguration cfg;

    public Level(){
        file = new File(GunGame.getInstance().getDataFolder(), "Level.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
    }
    public void saveLevel(int level, Player p){
        for(int i = 0;i < 8;i++){
            cfg.set("Level."+level+".Items."+i, p.getInventory().getItem(i));
        }

        cfg.set("Level."+level+".Helmet", p.getInventory().getHelmet());
        cfg.set("Level."+level+".Chestplate", p.getInventory().getChestplate());
        cfg.set("Level."+level+".Leggings", p.getInventory().getLeggings());
        cfg.set("Level."+level+".Boots", p.getInventory().getBoots());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ItemStack> getLevel(int level){
        List<ItemStack> list = new ArrayList<>();
        for(int i = 0;i < 8;i++){
            list.add(cfg.getItemStack("Level."+level+".Items."+i));
        }

        list.add(cfg.getItemStack("Level."+level+".Helmet"));
        list.add(cfg.getItemStack("Level."+level+".Chestplate"));
        list.add(cfg.getItemStack("Level."+level+".Leggings"));
        list.add(cfg.getItemStack("Level."+level+".Boots"));

        return list;
    }

    public void setLevel(Player p){
        //clear inv
        p.getInventory().clear();
        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        //set new inv
        int level = AlphaAPI.getInstance().getStatsPlayer(p).getGunGameStats().getLevel();
        List<ItemStack> list = getLevel(level);
        for(int i = 0;i < 8;i++){
            p.getInventory().setItem(i, list.get(i));
        }
        p.getInventory().setHelmet(list.get(8));
        p.getInventory().setChestplate(list.get(9));
        p.getInventory().setLeggings(list.get(10));
        p.getInventory().setBoots(list.get(11));

        p.updateInventory();
    }

}
