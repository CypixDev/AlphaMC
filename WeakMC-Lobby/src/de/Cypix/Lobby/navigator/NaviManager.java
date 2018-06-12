package de.Cypix.Lobby.navigator;

import de.Cypix.Lobby.Files.Var;
import de.Cypix.Lobby.Main.main;
import de.Cypix.Lobby.Manager.OptionManager;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitScheduler;

import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NaviManager {

    private static HashMap<Player, Integer> schedulers = new HashMap<>();
    private static HashMap<Player, Integer> schedulerint = new HashMap<>();

    public static void openInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 6*9, Var.Navigator);
        p.openInventory(inv);

        for(int i = 0;i<inv.getSize();i++){
            inv.setItem(i, null);
        }

        if(OptionManager.isAllowAnimation(p)) {
            p.openInventory(inv);
            playerAnimation(p, inv, 1);

        }else{
            for(int i = 0; i < inv.getSize();i++){
                inv.setItem(i, SlotHolder(15, 1));
            }
            for(Item item : Item.getAllItems()){
                inv.setItem(item.getSlot(), item.getItem());
            }

            p.updateInventory();
        }



    }
    @Deprecated
    public static void playerAnimation(final Player p,final Inventory inv, int i){
        if (i == 2) {
            schedulerint.put(p, 0);
            schedulers.put(p, Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    int slot = schedulerint.get(p);
                    if(p.getOpenInventory().getTitle().equals(inv.getTitle())){
                        Item item = Item.valueOfSlot(slot);
                        if(item != null){
                            inv.setItem(slot, item.getItem());
                        }else{
                            inv.setItem(slot, SlotHolder(15, 1));
                        }
                    }else{
                        schedulerint.remove(p);
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                    }
                    p.updateInventory();
                    schedulerint.put(p, slot+1);
                    if(inv.getSize() == schedulerint.get(p)){
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                        schedulerint.remove(p);
                    }
                }
            }, 0, 0));
        }
        if (i == 1) {
            schedulerint.put(p, 0);
            schedulers.put(p, Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    int slot = schedulerint.get(p);
                    if(p.getOpenInventory().getTitle().equals(inv.getTitle())){
                        Item item = Item.valueOfSlot(slot);
                        if(item != null){
                            inv.setItem(slot, item.getItem());
                        }else{
                            inv.setItem(slot, SlotHolder(15, 1));
                        }
                    }else{
                        schedulerint.remove(p);
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                    }
                    p.updateInventory();

                    if(slot == 0) schedulerint.put(p, slot+9);
                    if(slot == 9) schedulerint.put(p, slot+9);
                    if(slot == 18) schedulerint.put(p, slot+9);
                    if(slot == 27) schedulerint.put(p, slot+9);
                    if(slot == 36) schedulerint.put(p, slot+9);
                    if(slot == 45) schedulerint.put(p, slot+1);
                    if(slot == 46) schedulerint.put(p, slot+1);
                    if(slot == 47) schedulerint.put(p, slot+1);
                    if(slot == 48) schedulerint.put(p, slot+1);
                    if(slot == 49) schedulerint.put(p, slot+1);
                    if(slot == 50) schedulerint.put(p, slot+1);
                    if(slot == 51) schedulerint.put(p, slot+1);
                    if(slot == 52) schedulerint.put(p, slot+1);
                    if(slot == 53) schedulerint.put(p, slot-9);
                    if(slot == 44) schedulerint.put(p, slot-9);
                    if(slot == 35) schedulerint.put(p, slot-9);
                    if(slot == 26) schedulerint.put(p, slot-9);
                    if(slot == 17) schedulerint.put(p, slot-9);
                    if(slot == 8) schedulerint.put(p, slot-1);
                    if(slot == 7) schedulerint.put(p, slot-1);
                    if(slot == 6) schedulerint.put(p, slot-1);
                    if(slot == 5) schedulerint.put(p, slot-1);
                    if(slot == 4) schedulerint.put(p, slot-1);
                    if(slot == 3) schedulerint.put(p, slot-1);
                    if(slot == 2) schedulerint.put(p, slot-1);
                    if(slot == 1) schedulerint.put(p, slot+9);
                    if(slot == 10) schedulerint.put(p, slot+9);
                    if(slot == 19) schedulerint.put(p, slot+9);
                    if(slot == 28) schedulerint.put(p, slot+9);
                    if(slot == 37) schedulerint.put(p, slot+1);
                    if(slot == 38) schedulerint.put(p, slot+1);
                    if(slot == 39) schedulerint.put(p, slot+1);
                    if(slot == 40) schedulerint.put(p, slot+1);
                    if(slot == 41) schedulerint.put(p, slot+1);
                    if(slot == 42) schedulerint.put(p, slot+1);
                    if(slot == 43) schedulerint.put(p, slot-9);
                    if(slot == 34) schedulerint.put(p, slot-9);
                    if(slot == 25) schedulerint.put(p, slot-9);
                    if(slot == 16) schedulerint.put(p, slot-1);
                    if(slot == 15) schedulerint.put(p, slot-1);
                    if(slot == 14) schedulerint.put(p, slot-1);
                    if(slot == 13) schedulerint.put(p, slot-1);
                    if(slot == 12) schedulerint.put(p, slot-1);
                    if(slot == 11) schedulerint.put(p, slot+9);
                    if(slot == 20) schedulerint.put(p, slot+9);
                    if(slot == 29) schedulerint.put(p, slot+1);
                    if(slot == 30) schedulerint.put(p, slot+1);
                    if(slot == 31) schedulerint.put(p, slot+1);
                    if(slot == 32) schedulerint.put(p, slot+1);
                    if(slot == 33) schedulerint.put(p, slot-9);
                    if(slot == 24) schedulerint.put(p, slot-1);
                    if(slot == 23) schedulerint.put(p, slot-1);
                    if(slot == 22) schedulerint.put(p, slot-1);


                    if(slot == 21){
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                        schedulerint.remove(p);
                    }
                }
            }, 0, 0));
        }
        if (i == 0) {

            schedulerint.put(p, 0);
            schedulers.put(p, Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    int slot = schedulerint.get(p);
                    if(p.getOpenInventory().getTitle().equals(inv.getTitle())){
                        if(slot == 0) {
                            int s = 0;
                            Item item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 1;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 2;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 3;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 4;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 5;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 6;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 7;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 8;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 9;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 17;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 18;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 26;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 27;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 35;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 36;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 44;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 45;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 46;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 47;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 48;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 49;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 50;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 51;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 52;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 53;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                        }
                        if(slot == 1){
                            int s = 10;
                            Item item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 11;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 12;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 13;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 14;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 15;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 16;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 25;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 34;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 43;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 42;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 41;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 40;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 39;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 38;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 37;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 28;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 19;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }

                        }
                        if(slot == 2){
                            int s = 20;
                            Item item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 21;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 22;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 23;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 24;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 33;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 32;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 31;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 30;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                            s = 29;
                            item = Item.valueOfSlot(s);
                            if (item != null) { inv.setItem(s, item.getItem()); } else { inv.setItem(s, SlotHolder(15, 1)); }
                        }

                    }else{
                        schedulerint.remove(p);
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                    }
                    p.updateInventory();
                    schedulerint.put(p, slot+1);
                    if(schedulerint.get(p) == 3){
                        Bukkit.getScheduler().cancelTask(schedulers.get(p));
                        schedulerint.remove(p);
                    }
                }
            }, 0, 10));

        }
    }

    @Deprecated
    public static ItemStack SlotHolder(int shortid, int amount){
        ItemStack i = new ItemStack(Material.getMaterial(160), 1, (short)shortid);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }
}
