package de.Cypix.Lobby.CMD;

import de.Cypix.Lobby.MYSQL.MYSQL;
import de.Cypix.Lobby.Main.main;
import de.Cypix.Lobby.inventar.Extras.Boots;
import de.Cypix.Lobby.navigator.NaviManager;
import de.Cypix.Lobby.Manager.OptionManager;
import de.Cypix.Lobby.Manager.PetsManager;
import de.Cypix.Lobby.Manager.ShopManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;

public class CMDTest implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args[0].equalsIgnoreCase("navi")){
                NaviManager.openInv(p);
            }
            if(args[0].equalsIgnoreCase("pet")){
                new PetsManager().createPet(p, EntityType.CHICKEN);
            }
            if(args[0].equalsIgnoreCase("putlist")){
                Random r = new Random();
                MYSQL.update("INSERT INTO Shop(UUID, item) VALUES ('"+p.getUniqueId()+"', '"+r.nextInt(100)+"')");
            }
            if(args[0].equalsIgnoreCase("getlist")){
                p.sendMessage(ShopManager.list.toString());
            }
            if(args[0].equalsIgnoreCase("buyBoots")){
                ShopManager.BuyBoot(p, Boots.ANGRY);
                p.sendMessage("ok !");
            }
            if(args[0].equalsIgnoreCase("v")){
                Vector v = p.getLocation().getDirection();
                v.setY(2.0);
                v.multiply(3);
                p.setVelocity(v);
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 10));
            }
            if(args[0].equalsIgnoreCase("name")){
                CloudPlayer cp = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                cp.setName(args[1]);
            }
            if(args[0].equals("bomb")){
                final FallingBlock fb = p.getWorld().spawnFallingBlock(p.getLocation().add(0, 3, 0), 1, (byte)10);
                fb.setVelocity(fb.getVelocity().add(new Vector(0, 0, 10)));
                fb.setDropItem(false);
                fb.setHurtEntities(false);
                Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        fb.getLocation().getBlock().setType(Material.AIR);
                    }
                }, 40);
            }
        }
        return false;
    }
}
