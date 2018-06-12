package de.cypix.mlgrush.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class HidPlayerListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) ||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem().getType() == Material.BLAZE_ROD){
                p.setItemInHand(new ItemStack(Material.RED_ROSE));
                for(Player target : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(target);
                }
            }
            if(e.getItem().getType() == Material.RED_ROSE){
                p.setItemInHand(new ItemStack(Material.BLAZE_ROD));
                for(Player target : Bukkit.getOnlinePlayers()){
                    p.showPlayer(target);
                }
            }
        }
    }

}
