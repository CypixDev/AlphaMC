package de.cypix.ttt.manager;

import com.mojang.authlib.GameProfile;
import de.cypix.ttt.main.Main;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.Collection;

public class TablistManager {

    public static void setScoreBoard(){

    }

    public static void updateScoreBoard(){
        for(Player p : Bukkit.getOnlinePlayers()) {
            
            if(TeamManager.getTraitors().contains(p)){
                for(Player pp : Bukkit.getOnlinePlayers()){
                    if(TeamManager.getTraitors().contains(pp)){
                        setName(p, pp, "§c"+p.getName());
                    }else{
                        setName(p, pp, "§a"+p.getName());
                    }
                }
            }else if(TeamManager.getDetectives().contains(p)){
                for(Player pp : Bukkit.getOnlinePlayers()){
                    setName(p, pp, "§3"+p.getName());
                }
            }else{
                for(Player pp : Bukkit.getOnlinePlayers()){
                    setName(p, pp, "§a"+p.getName());
                }
            }

            if(TeamManager.getTraitors().contains(p)){
                for(Player pp : Bukkit.getOnlinePlayers()){
                    if(TeamManager.getTraitors().contains(pp)){
                        setArmor(p, Color.RED, pp);
                    }else{
                        setArmor(p, Color.GREEN, pp);
                    }
                }
            }else{
                if(TeamManager.getDetectives().contains(p)){
                    for(Player pp : Bukkit.getOnlinePlayers()){
                        setArmor(p, Color.BLUE, pp);
                    }
                }else{
                    for(Player pp : Bukkit.getOnlinePlayers()){
                        setArmor(p, Color.GREEN, pp);
                    }
                }
            }

        }
    }


    @SuppressWarnings("deprecation")
    public static void setName(final Player p, final Player forr, String name) {

        EntityPlayer other = ((CraftPlayer) p).getHandle();

        final CraftPlayer pp = ((CraftPlayer) forr).getHandle().playerConnection.getPlayer();

        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, other);

        pp.getHandle().playerConnection.sendPacket(packet);


        MinecraftServer server = MinecraftServer.getServer();
        WorldServer worldserver = (WorldServer) server.getWorld();
        GameProfile profil = new GameProfile(p.getUniqueId(), name);
        PlayerInteractManager interact = new PlayerInteractManager(
                server.getWorld());

        final EntityPlayer lui = new EntityPlayer(server, worldserver, profil,
                interact);

        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                final CraftPlayer pp = ((CraftPlayer) forr).getHandle().playerConnection.getPlayer();

                final PacketPlayOutPlayerInfo packet2 = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, lui);
                pp.getHandle().playerConnection.sendPacket(packet2);

            }

        }, 5);
    }

    private static void setArmor(Player p, Color c, Player reciever) {
        int id = p.getEntityId();
        ItemStack armor = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
        meta.setColor(c);
        armor.setItemMeta(meta);
        net.minecraft.server.v1_8_R3.ItemStack istack = CraftItemStack.asNMSCopy(armor);
        PacketPlayOutEntityEquipment packet = new PacketPlayOutEntityEquipment(id, 2, istack);
        ((CraftPlayer) reciever).getHandle().playerConnection.sendPacket(packet);
    }



}
