package de.Cypix.Lobby.navigator;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Main.main;
import de.Cypix.Lobby.Manager.LocationManager;
import de.Cypix.Lobby.navigator.NaviManager;
import de.Cypix.Lobby.Manager.OptionManager;
import de.Cypix.Lobby.Manager.SpawnManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NaviListener implements Listener {

    @EventHandler
    public void onCLick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(Var.Navigator)){
                if(e.getAction().equals(InventoryAction.PICKUP_ALL)) {
                    Player p = (Player) e.getWhoClicked();
                    if(e.getCurrentItem() != null){
                        Item item = Item.valueOfDisplayName(e.getCurrentItem().getItemMeta().getDisplayName());
                        if(item != null){
                            TeleportPlayer(LocationManager.getLocation(item.getServerGroup()), p);
                        }
                        e.setCancelled(true);
                    }
                }
            }
            e.setCancelled(true);
        }catch(NullPointerException e1){

        }
    }

    public static void TeleportPlayer(Location loc, Player p){
        Vector v = p.getLocation().getDirection();
        v.setY(0.8);
        v.multiply(2);
        p.setVelocity(v);
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 10));
        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
        //Hey
        p.closeInventory();
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.teleport(loc);
                p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 10);
                p.setVelocity(new Vector());
                p.removePotionEffect(PotionEffectType.SPEED);
            }
        },20);

    }

    /*public static Inventory getInvWhithServers(ItemStack item, String groupname){
        int l = 0;
        Inventory inv = Bukkit.createInventory(null, 5*9, "§8➥ §7Server von §b"+groupname);
        for(ServerInfo serverinfo : CloudAPI.getInstance().getServers(groupname)){
            l++;
            ItemStack i = item;
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(groupname+"-"+l);
            List<String> lore = new ArrayList<String>();
            lore.add("§8➤ §7Online Spieler §8× §b"+serverinfo.getOnlineCount()+"§7/§b"+serverinfo.getMaxPlayers());
            lore.add("§8➤ §7Map §8× "+serverinfo.getMotd());
            im.setLore(lore);
            i.setItemMeta(im);

            inv.addItem(i);
        }
        return inv;
    }

    @EventHandler
    public void onServerConnect(InventoryClickEvent e){
        try {
            if (e.getClickedInventory().getTitle().startsWith("§8➥ §7Server")) {
                sendPlayer(e.getWhoClicked().getUniqueId(), e.getCurrentItem().getItemMeta().getDisplayName());
            }
        }catch(NullPointerException e1){

        }
    }
    public void sendPlayer(UUID uuid, String server) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(uuid);
        PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
        playerExecutorBridge.sendPlayer(cloudPlayer,  server);
    }*/


}
