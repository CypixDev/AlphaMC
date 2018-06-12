package de.cypix.ttt.manager;

import de.cypix.ttt.Var;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteManager {

    private static HashMap<String, Integer> map = new HashMap<>();
    private static HashMap<Player, String> voted = new HashMap<>();
    public static String ItemName = "§bVote";

    public static String getVotedMap(){
        return getKeyWithHighestValue();
    }

    public static void vote(String vote, Player p){
        if(voted.containsKey(p)){
            if(voted.get(p).equalsIgnoreCase(vote)){
                p.sendMessage(Var.pr+"Dafür hast du schon abgestimmt !");
            }else{
                int e = map.get(voted.get(p));
                map.put(voted.get(p), e-1);
                voted.put(p, vote);
                map.put(vote, map.get(vote)+1);
                p.sendMessage(Var.pr+"Du hast deine Stimme auf "+vote+" geändert !");
            }
        }else{
            map.put(vote, map.get(vote)+1);
            voted.put(p, vote);
            p.sendMessage(Var.pr+"Du hast für "+vote+" gevoted !");
            p.getInventory().getItemInHand().setAmount(0);
        }
        p.closeInventory();
    }


    public static HashMap<String, Integer> getMap() {
        return map;
    }

    public static String getKeyWithHighestValue() {
        Integer highestValue = null;
        String keyString = null;

        for(Map.Entry<String, Integer> checking : map.entrySet()) {
            if(highestValue == null) {
                highestValue = checking.getValue();
            }
            if(checking.getValue() >= highestValue) {
                highestValue = checking.getValue();
                keyString = checking.getKey();
            }
        }
        return keyString;
    }

    public static void setVoteItem(Player p){
        ItemStack i = new ItemStack(Material.PAPER);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(ItemName);
        i.setItemMeta(im);
        p.getInventory().setItem(1, i);
    }
    public static void removeVoteItem(Player p){
        p.getInventory().setItem(1, new ItemStack(Material.AIR));
    }

    public static Inventory getVoteInv(){
        Inventory inv = Bukkit.createInventory(null, 3*9, "Vote");
        List<String> maps = MapManager.getMaps();
        int in = 0;
        for(String map : maps){
            ItemStack i = MapManager.getItem(map);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(MapManager.getDisplayName(map));
            i.setItemMeta(im);
            if(in == 0) inv.setItem(11, i);
            if(in == 1) inv.setItem(13, i);
            if(in == 2)inv.setItem(15, i);
            in++;
            if(in == 3) in = 0;
        }
        return inv;
    }


    public static void PutVotes() {
        for(String maps : MapManager.getMaps()){
            map.put(maps, 0);
        }
    }
}
