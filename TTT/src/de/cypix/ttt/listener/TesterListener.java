package de.cypix.ttt.listener;

import de.cypix.ttt.Var;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.MapManager;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.mysql.PlayerStats;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;

public class TesterListener implements Listener {

    private HashMap<Player, Location> testing = new HashMap();
    int idelID = 0;
    int sec = 20;
    boolean isRunning = false;

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(e.getClickedBlock() != null) {
            if (e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
                Location loc = e.getClickedBlock().getLocation();
                Location locc = MapManager.getTesterButtonLocation(MapManager.currentmap);
                final Player p = e.getPlayer();
                if (!isRunning) {
                    if (loc.getWorld() == locc.getWorld() && loc.getX() == locc.getX() && loc.getY() == locc.getY() && loc.getZ() == locc.getZ()) {
                        p.teleport(MapManager.getTesterSpawnLocation(MapManager.currentmap));
                        new PlayerStats(p).addTest();
                        isRunning = true;
                        Location EntityLoc = MapManager.getTesterButtonLocation(MapManager.currentmap);
                        EntityLoc.setX(EntityLoc.getX() + 1);
                        EntityLoc.setX(EntityLoc.getX() - 0.5);
                        EntityLoc.setY(EntityLoc.getY() - 1);

                        Location saveLoc = MapManager.getTesterSpawnLocation(MapManager.currentmap);
                        saveLoc.setZ(saveLoc.getZ() + 3);

                        final ArmorStand en = (ArmorStand) p.getWorld().spawnEntity(EntityLoc, EntityType.ARMOR_STAND);
                        en.setVisible(false);
                        Bukkit.broadcastMessage(Var.pr + p.getName() + " hat den Tester betreten !");


                        Location locBlock = MapManager.getTesterSpawnLocation(MapManager.currentmap);
                        locBlock.setX(locBlock.getX() - 2);
                        locBlock.setY(locBlock.getY() + 2);
                        locBlock.setZ(locBlock.getZ() + 2);
                        Block b = locBlock.getBlock();
                        b.setType(Material.SPONGE);

                        locBlock.setX(locBlock.getX() + 4);
                        Block b2 = locBlock.getBlock();
                        b2.setType(Material.SPONGE);


                        testing.put(p, MapManager.getTesterSpawnLocation(MapManager.currentmap));

                        idelID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                if (sec == 0) {
                                    Bukkit.getScheduler().cancelTask(idelID);
                                    isRunning = false;
                                    en.remove();
                                    sec = 20;

                                    testing.remove(p);


                                    Location locBlock = MapManager.getTesterSpawnLocation(MapManager.currentmap);
                                    locBlock.setX(locBlock.getX() - 2);
                                    locBlock.setY(locBlock.getY() + 2);
                                    locBlock.setZ(locBlock.getZ() + 2);
                                    Block b = locBlock.getBlock();
                                    if (TeamManager.getTraitors().contains(p)) {
                                        b.setType(Material.REDSTONE_BLOCK);
                                    } else {
                                        b.setType(Material.SLIME_BLOCK);
                                    }

                                    locBlock.setX(locBlock.getX() + 4);
                                    Block b2 = locBlock.getBlock();
                                    if (TeamManager.getTraitors().contains(p)) {
                                        b2.setType(Material.REDSTONE_BLOCK);
                                    } else {
                                        b2.setType(Material.SLIME_BLOCK);
                                    }

                                }
                                Random r = new Random();
                                int random = r.nextInt(7);
                                if (random == 0) {
                                    particel(Effect.CLOUD, p);
                                }
                                if (random == 1) particel(Effect.HEART, p);
                                if (random == 2) particel(Effect.WATERDRIP, p);
                                if (random == 3) particel(Effect.FLAME, p);
                                if (random == 4) particel(Effect.LAVADRIP, p);
                                if (random == 5) particel(Effect.LAVA_POP, p);
                                if (random == 6) particel(Effect.HAPPY_VILLAGER, p);
                                if (random == 7) particel(Effect.VILLAGER_THUNDERCLOUD, p);
                                sec--;
                                for (Entity entity : en.getNearbyEntities(5, 5, 5)) {
                                    if (entity instanceof Player) {
                                        Player target = (Player) entity;
                                        if (target != p) {


                                            double Ax = en.getLocation().getX();
                                            double Ay = en.getLocation().getY();
                                            double Az = en.getLocation().getZ();

                                            double Bx = target.getLocation().getX();
                                            double By = target.getLocation().getY();
                                            double Bz = target.getLocation().getZ();

                                            double x = Bx - Ax;
                                            double y = By - Ay;
                                            double z = Bz - Az;

                                            Vector v = new Vector(x, y, z).normalize().multiply(0.5D).setY(0.0D);
                                            target.setVelocity(v);

                                        }
                                    }
                                }
                            }
                        }, 0, 5);
                    }
                } else {
                    Bukkit.broadcastMessage("ka für eine Message !");
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Location loc1 = e.getPlayer().getLocation();
        Location loc2 = testing.get(e.getPlayer());

    }

    private void particel(Effect EffectType, Player p){
        World w = p.getWorld();
        w.playEffect(p.getLocation(), Effect.LARGE_SMOKE, 10, 10);
        
        Location loc = p.getLocation();
        loc.setX(loc.getX()+1);
        Effect ef = EffectType;
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+2);
        w.playEffect(loc, ef, 10, 10);

        loc.setZ(loc.getZ()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+2);
        w.playEffect(loc, ef, 10, 10);


        loc.setZ(loc.getZ()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+2);
        w.playEffect(loc, ef, 10, 10);



        loc.setZ(loc.getZ()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+2);
        w.playEffect(loc, ef, 10, 10);


        loc.setZ(loc.getZ()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-3);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()-1);
        w.playEffect(loc, ef, 10, 10);
        loc.setX(loc.getX()+2);
        w.playEffect(loc, ef, 10, 10);

    }

}
