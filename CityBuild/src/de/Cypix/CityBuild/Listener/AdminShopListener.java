package de.Cypix.CityBuild.Listener;

import de.Cypix.CityBuild.Manager.AdminShopManager;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class AdminShopListener implements Listener{

    @EventHandler
    public void onSignChange(SignChangeEvent e){
        Player p = e.getPlayer();
        if(e.getLine(0).equalsIgnoreCase("[adminshop]")){
            p.sendMessage("Schilderkannt !");
            p.sendMessage("Schild wird gespeichert !");
            List<String> list = new ArrayList<>();
            list.add("HEy");
            list.add("Du");
            list.add("Nudel");
            list.add("lol");
            AdminShopManager.saveSignLoc(e.getBlock().getLocation(), list);
        }
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            if(e.getClickedBlock().getState() instanceof Sign){
                Sign s = (Sign)e.getClickedBlock().getState();
                if(AdminShopManager.isAdminSign(s.getLocation())){
                    int signid = AdminShopManager.getSignId(s.getLocation());
                    p.getInventory().addItem(AdminShopManager.getItemtoBuy(signid));
                }
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(AdminShopManager.ShopInvName)){
                e.setCancelled(true);
            }
        }catch(NullPointerException e1){

        }
    }

    private static boolean issameLocation(Location loc1, Location loc2){
        if(loc1.getX() == loc2.getX() && loc1.getZ() == loc2.getZ() && loc1.getY() == loc2.getY() && loc1.getWorld().getName() == loc2.getWorld().getName()){
            return true;
        }
        return false;
    }

}
