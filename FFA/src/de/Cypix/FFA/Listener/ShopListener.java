package de.Cypix.FFA.Listener;

import de.Cypix.CoinsAPI.Coins.Coins;
import de.Cypix.FFA.Main.main;
import de.Cypix.FFA.Manager.ShopManager;
import de.Cypix.FFA.ShopItem;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ShopListener implements Listener {

    @EventHandler
    public void onInv(InventoryClickEvent e){
        try{
            Player p = (Player)e.getWhoClicked();
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(main.getshopitemname)){
                e.setCancelled(true);
            }
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(main.getShopInvName)){
                e.setCancelled(true);
                    ShopItem item = ShopItem.valueof(e.getCurrentItem().getItemMeta().getDisplayName());
                    if(item.getName().equalsIgnoreCase(e.getCurrentItem().getItemMeta().getDisplayName())){
                        if(Coins.getCoins(p) >= item.getPrice()){
                            Coins.removeCoins(p, item.getPrice());
                            p.getInventory().addItem(item.getItem());
                            p.closeInventory();
                        }else{
                            p.sendMessage("§7Du hast zu wenig §bCoins");
                        }
                        return;
                    }
            }
        }catch (NullPointerException e1){ }
    }

    @EventHandler
    public void onInterace(PlayerInteractEvent e){
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(main.getshopitemname)) {
                e.setCancelled(true);
                ShopManager.openShopInv(e.getPlayer());
            }
        }catch (NullPointerException e1){

        }
    }

    @EventHandler
    public void onBoom(BlockExplodeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDropPotion(PotionSplashEvent e){
        if(e.getPotion().getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ShopItem.GRANATE.getName())){
            e.getEntity().getWorld().createExplosion(e.getPotion().getLocation(), 3, true);
            e.getEntity().getWorld().playEffect(e.getPotion().getLocation(), Effect.EXPLOSION_HUGE, 1000);
        }
    }
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ShopItem.GRANATE.getName())) {
                if (!e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
                    e.setCancelled(true);
                    e.getPlayer().updateInventory();
                }
            }
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ShopItem.BOOST.getName())) {
                Player p = e.getPlayer();
                Vector v = p.getEyeLocation().getDirection();
                v.multiply(2.3);
                Vector vv = new Vector();
                vv.setY(1.0);
                v.add(vv);
                p.setVelocity(v);


                e.getPlayer().getInventory().removeItem(e.getItem());
            }
        }catch (NullPointerException e1){

        }
    }

}
