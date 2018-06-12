package de.cypix.ttt.manager;

import de.cypix.ttt.mysql.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {

    public static String InvItemName = "§bTickets", InInvTraoitorTicketName = "Traitor-Ticket !", InInvDetectiveTicketName = "Detectivie-Ticket !";
    public static String InvName = "Tickets !!!";

    public static void setTicketItem(Player p){
        ItemStack i = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta im = i.getItemMeta();
        List<String> lore = new ArrayList<>();
        PlayerStats ps = new PlayerStats(p);
        lore.add("§4Traitor-Tickets == "+ps.getTraitorPasses());
        lore.add("§1Detective-Tickets == "+ps.getdetectivepasses());
        im.setLore(lore);
        im.setDisplayName(InvItemName);
        i.setItemMeta(im);

        p.getInventory().setItem(4, i);
    }


    public static Inventory OpenTicketInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 3*9, InvName);
        List<String> lore1 = new ArrayList<>();
        List<String> lore2 = new ArrayList<>();
        PlayerStats ps = new PlayerStats(p);
        lore1.add("Du hast noch "+ps.getTraitorPasses()+" Traitor Pässe");
        lore2.add("Du hast noch "+ps.getdetectivepasses()+" Detective Pässe");
        inv.setItem(11, Item(Material.REDSTONE_TORCH_ON, InInvTraoitorTicketName, lore1, ps.getTraitorPasses()));
        inv.setItem(15, Item(Material.REDSTONE_BLOCK, InInvDetectiveTicketName, lore2, ps.getdetectivepasses()));


        return inv;
    }

    private static ItemStack Item(Material mat, String display, List<String> lore, int amount){
        ItemStack i = new ItemStack(mat);
        ItemMeta im = i.getItemMeta();
        im.setLore(lore);
        im.setDisplayName(display);
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }
}
