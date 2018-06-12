package de.Cypix.Test.CMD;

import de.Cypix.Test.LaserTower;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CMDTest implements CommandExecutor , Listener{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Player p = (Player)sender;
        if(args[0].equalsIgnoreCase("bom")){
            p.getWorld().createExplosion(p.getLocation(), 5, true);
            p.getWorld().playEffect(p.getLocation(), Effect.EXPLOSION_HUGE, 1000, 10000);
        }
        if(args[0].equalsIgnoreCase("getgranate")){
            ItemStack i = new ItemStack(Material.getMaterial(373), (byte)16428, (short) 16428);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName("Granate");
            i.setItemMeta(im);
            p.getInventory().addItem(i);
        }
        if(args[0].equalsIgnoreCase("laser")){
            LaserTower lt = new LaserTower(p.getLocation(), 10, 10);
            lt.spawn();
        }

        return false;
    }

    @EventHandler
    public void onBoom(BlockExplodeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDropPotion(PotionSplashEvent e){
        if(e.getPotion().getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Granate")){
            e.getEntity().getWorld().createExplosion(e.getPotion().getLocation(), 3, true);
            e.getEntity().getWorld().playEffect(e.getPotion().getLocation(), Effect.EXPLOSION_HUGE, 1000);
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("Granate")){
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){

            }else{
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void playerdeath(EntityDeathEvent e){
        Entity ent = e.getEntity();
        EntityDamageEvent ede = ent.getLastDamageCause();
        EntityDamageEvent.DamageCause dc = ede.getCause();
        if(dc.equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)){
            Bukkit.broadcastMessage(ent.getName()+"ist wegen bumm gestorben !");
        }
    }
}