package de.Cypix.Lobby.Manager;

import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InfoBookManager {

    public static void openBook(Player p){
        openBook(book("§b§lUpdate-Log", "Cypix", "§bWeakMC §7× §aNetWork \n"
                + "\n"
                + "§7× §b+ 100 Coins"
                + "\n"
                + "\n"
                + "§7× §bShop -50 % Sale"
                + "\n"
                + "\n"
                + "§7× §3GunFight §7× §aBeta"), p.getPlayer());

    }

    private static void openBook(ItemStack book, Player p) {
        int slot = p.getInventory().getHeldItemSlot();
        ItemStack old = p.getInventory().getItem(slot);
        p.getInventory().setItem(slot, book);
        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(Unpooled.EMPTY_BUFFER));
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        p.getInventory().setItem(slot, old);

    }

    private static ItemStack book(String title, String author, String... pages) {
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_8_R3.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        for(String text : pages) {
            bp.add(new NBTTagString(text));
        }
        bd.set("pages", bp);
        nmsis.setTag(bd);
        is = CraftItemStack.asBukkitCopy(nmsis);
        return is;
    }



}
