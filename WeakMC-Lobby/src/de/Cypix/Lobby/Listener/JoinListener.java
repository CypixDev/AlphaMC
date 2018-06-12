package de.Cypix.Lobby.Listener;

import de.Cypix.Lobby.CMD.CMDFly;
import de.Cypix.Lobby.MYSQL.MYSQL;
import de.Cypix.Lobby.MYSQL.MYSQLPlayer;
import de.Cypix.Lobby.Main.main;
import de.Cypix.Lobby.Manager.*;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setBoots(new ItemStack(Material.AIR));
        p.getInventory().setHelmet(new ItemStack(Material.AIR));
        p.getInventory().setHeldItemSlot(4);
        p.setAllowFlight(false);

        e.setJoinMessage("");
        CloudServer.getInstance().updateNameTags(p);

        e.getPlayer().setMaxHealth(20);
        MYSQLPlayer mp = new MYSQLPlayer(e.getPlayer());
        if(!mp.isinlist("loc")){
            if(SpawnManager.exists()) {
                MYSQL.update("INSERT INTO loc (UUID, World, X, Y, Z, Yaw, Pitch) VALUES ('" + p.getUniqueId() + "', '"+SpawnManager.getLocation().getWorld().getName()+"', '" + SpawnManager.getLocation().getX() + "', '" + SpawnManager.getLocation().getY() + "', '" + SpawnManager.getLocation().getZ() + "', '" + SpawnManager.getLocation().getYaw() + "', '" + SpawnManager.getLocation().getPitch() + "')");
            }
        }

        ShopManager.loadShopItems(p);

        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 15, 60));
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {

            @Override
            public void run() {

                if(OptionManager.isAllowScoreBoard(p)) ScoreBoardManager.sendScoreBoard(p);
                CloudServer.getInstance().updateNameTags(p);
                if(mp.isinlist("loc")){
                    p.teleport(mp.getLocation());
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 20, 20);
                }else{
                    p.teleport(SpawnManager.getLocation());
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 20, 20);
                }

            }
        }, 10);


        p.getInventory().clear();
        p.getInventory().setContents(InventoryManager.getDefaultInv(e.getPlayer()).getContents());

        Bukkit.getScheduler().runTaskLaterAsynchronously(main.getInstance(), new Runnable() {
            public void run() {
                CloudServer.getInstance().updateNameTags(e.getPlayer());
            }
        }, 3L);


        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.setHealth(2);
                p.setFoodLevel(2);
                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        p.setHealth(4);
                        p.setFoodLevel(4);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                p.setHealth(6);
                                p.setFoodLevel(6);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        p.setHealth(8);
                                        p.setFoodLevel(8);
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                            @Override
                                            public void run() {
                                                p.setHealth(10);
                                                p.setFoodLevel(10);
                                                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        p.setHealth(12);
                                                        p.setFoodLevel(12);
                                                        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                p.setHealth(14);
                                                                p.setFoodLevel(14);
                                                                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        p.setHealth(16);
                                                                        p.setFoodLevel(16);
                                                                        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                p.setHealth(18);
                                                                                p.setFoodLevel(18);
                                                                                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        p.setHealth(20);
                                                                                        p.setFoodLevel(20);
                                                                                    }
                                                                                },3L);
                                                                            }
                                                                        },3L);
                                                                    }
                                                                },3L);
                                                            }
                                                        },3L);
                                                    }
                                                },2L);
                                            }
                                        },3L);
                                    }
                                },3L);
                            }
                        },3L);
                    }
                },3L);
            }
        },3L);

        //SaveItemManager.setItem(e.getPlayer());

    }


    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        e.setQuitMessage("");
        MYSQLPlayer mp = new MYSQLPlayer(e.getPlayer());
        mp.saveSpawnLocation();
    }
}
