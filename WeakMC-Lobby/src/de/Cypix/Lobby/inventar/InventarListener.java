package de.Cypix.Lobby.inventar;

import de.Cypix.Lobby.Main.main;
import de.Cypix.Lobby.inventar.Extras.Boots;
import de.Cypix.Lobby.inventar.Extras.Gadgets;
import de.Cypix.Lobby.inventar.Extras.Heads;
import de.Cypix.Lobby.inventar.Extras.Pets;
import de.Cypix.Lobby.inventar.InventarManager;
import de.Cypix.Lobby.Manager.PetsManager;
import de.Cypix.Lobby.Manager.ScoreBoardManager;
import de.Cypix.Lobby.Manager.ShopManager;
import de.dytanic.cloudnet.bridge.CloudServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class InventarListener implements Listener {

    static int slot = 9;

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        try {
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(InventarManager.InventarInv_Name)) {
                if(e.getCurrentItem() != null){
                    Player p = (Player)e.getWhoClicked();
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(InventarManager.Boots_Name)){
                        resetInv(e.getClickedInventory(), (Player)e.getWhoClicked());
                        setBoots(e.getClickedInventory(), (Player) e.getWhoClicked());
                        p.playSound(p.getLocation(), Sound.CLICK, 10, 10);

                        return;
                    }
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(InventarManager.Heads_Name)){
                        resetInv(e.getClickedInventory(), (Player)e.getWhoClicked());
                        setHeads(e.getClickedInventory(), (Player)e.getWhoClicked());
                        p.playSound(p.getLocation(), Sound.CLICK, 10, 10);

                        return;
                    }
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(InventarManager.Pets_Name)){
                        resetInv(e.getClickedInventory(), (Player)e.getWhoClicked());
                        setPets(e.getClickedInventory(), (Player) e.getWhoClicked());
                        p.playSound(p.getLocation(), Sound.CLICK, 10, 10);

                        return;
                    }
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(InventarManager.Gadgets_Name)){
                        resetInv(e.getClickedInventory(), (Player)e.getWhoClicked());
                        setGadgets(e.getClickedInventory(), (Player)e.getWhoClicked());
                        p.playSound(p.getLocation(), Sound.CLICK, 10, 10);

                        return;
                    }

                    if(Boots.Bootsexists(e.getCurrentItem().getItemMeta().getDisplayName())){
                        Boots boot = Boots.valueof(e.getCurrentItem().getItemMeta().getDisplayName());
                        if(!ShopManager.hasbuyed(p, boot)){
                            if(ShopManager.BuyBoot(p, boot)){
                                setBoots(e.getClickedInventory(), p);
                                ScoreBoardManager.sendScoreBoard(p);
                                CloudServer.getInstance().updateNameTags(p);
                                p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 20 ,20);
                            }else{
                                p.playSound(p.getLocation(), Sound.BAT_DEATH, 20, 20);
                            }
                        }else{
                            p.getInventory().setBoots(boot.getItemStack());
                        }
                        return;
                    }
                    if(Pets.petexists(e.getCurrentItem().getItemMeta().getDisplayName())){
                        Pets pet = Pets.valueof(e.getCurrentItem().getItemMeta().getDisplayName());

                        if(!ShopManager.hasbuyed(p, pet)){
                            if(ShopManager.BuyPet(p, pet)){
                                setPets(e.getClickedInventory(), p);
                                CloudServer.getInstance().updateNameTags(p);
                                p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 20 ,20);

                                if(PetsManager.list.containsKey(p)){
                                    PetsManager.list.get(p).remove();
                                    PetsManager.list.remove(p);
                                }

                                new PetsManager().createPet(p, pet.getType());
                            }else{
                                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 20, 20);
                            }
                        }else{
                            if(PetsManager.list.containsKey(p)){
                                PetsManager.list.get(p).remove();
                                PetsManager.list.remove(p);
                            }
                            new PetsManager().createPet(p, pet.getType());
                        }
                        return;
                    }
                    if(Heads.headsexists(e.getCurrentItem().getItemMeta().getDisplayName())){
                        Heads head = Heads.valueof(e.getCurrentItem().getItemMeta().getDisplayName());
                        if(!ShopManager.hasbuyed(p, head)){
                            if(ShopManager.BuyHead(p, head)){
                                setHeads(e.getClickedInventory(), p);
                                CloudServer.getInstance().updateNameTags(p);
                                p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 20 ,20);
                            }else{
                                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 20, 20);
                            }
                        }else{
                            p.getInventory().setHelmet(head.getItemStack());
                        }
                        return;
                    }
                    if(Gadgets.gadgetsexists(e.getCurrentItem().getItemMeta().getDisplayName())){
                        Gadgets gd = Gadgets.valueof(e.getCurrentItem().getItemMeta().getDisplayName());
                        if(!ShopManager.hasbuyed(p, gd)){
                            if(ShopManager.BuyGadget(p, gd)){
                                setGadgets(e.getClickedInventory(), p);
                                CloudServer.getInstance().updateNameTags(p);
                                p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 20 ,20);
                            }else{
                                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 20, 20);
                            }
                        }else{
                            p.getInventory().setItem(8, gd.getItemStack());
                            p.closeInventory();
                        }

                    }
                    if(e.getSlot() == 28){
                        p.getInventory().setHelmet(new ItemStack(Material.AIR));
                        e.getCurrentItem().setType(Material.AIR);
                        return;
                    }
                    if(e.getSlot() == 34){
                        p.getInventory().setBoots(new ItemStack(Material.AIR));
                        e.getCurrentItem().setType(Material.AIR);
                        return;
                    }
                    return;
                }
            }
            e.setCancelled(true);
        }catch(NullPointerException e1){

        }
    }

    public static void setGadgets(Inventory inv, Player p){

        List<String> buyed = new ArrayList<>();
        buyed.add("§8➤ §7Diese §bBoots §7besitzt du §bbereits§7!");

        List<String> notbuyed = new ArrayList<>();

        for(Gadgets gd : Gadgets.values()){
            ItemStack i = gd.getItemStack();
            ItemMeta im = i.getItemMeta();
            if(notbuyed.isEmpty())notbuyed.add("§8➤ §b"+gd.getPrise()+" §7Coins");
            notbuyed.set(0, "§8➤ §b"+gd.getPrise()+" §7Coins");
            if(ShopManager.hasbuyed(p, gd)) im.setLore(buyed);
            if(!ShopManager.hasbuyed(p, gd)) im.setLore(notbuyed);
            i.setItemMeta(im);
            inv.setItem(gd.getSlot(), i);
        }
        inv.setItem(41, SlotHolder(5 ,1));


    }


    private static void setPets(Inventory inv, Player p){

        List<String> buyed = new ArrayList<>();
        buyed.add("§8➤ §7Dieses §bPet §7besitzt du §bbereits§7!");

        List<String> notbuyed = new ArrayList<>();

        for(Pets pet : Pets.values()){

            ItemStack i = pet.getItemStack();
            ItemMeta im = i.getItemMeta();
            if(notbuyed.isEmpty())notbuyed.add("§8➤ §b"+pet.getPrise()+" §7Coins");
            notbuyed.set(0, "§8➤ §b"+pet.getPrise()+" §7Coins");
            if(ShopManager.hasbuyed(p, pet)) im.setLore(buyed);
            if(!ShopManager.hasbuyed(p, pet)) im.setLore(notbuyed);
            i.setItemMeta(im);
            inv.setItem(pet.getSlot(), i);

        }

        inv.setItem(39, SlotHolder(5 ,1));

        p.updateInventory();
    }

    private static void resetInv(Inventory inv, Player p){

        setPlaceHolder(inv, p);
        for(int i = 0;i<27;i++){
            inv.setItem(i, new ItemStack(Material.AIR));
        }

    }
    private static void setHeads(Inventory inv, Player p){

        List<String> buyed = new ArrayList<>();
        buyed.add("§8➤ §7Diesen §bKopf §7besitzt du §bbereits§7!");

        List<String> notbuyed = new ArrayList<>();

        for(Heads head : Heads.values()){
            slot++;
            ItemStack i = head.getItemStack();
            ItemMeta im = i.getItemMeta();
            if(notbuyed.isEmpty())notbuyed.add("§8➤ §b"+head.getPrise()+" §7Coins");
            notbuyed.set(0, "§8➤ §b"+head.getPrise()+" §7Coins");
            if(ShopManager.hasbuyed(p, head)) im.setLore(buyed);
            if(!ShopManager.hasbuyed(p, head)) im.setLore(notbuyed);
            i.setItemMeta(im);
            inv.setItem(slot, i);
            if(slot == 15)slot+=3;
            if(slot == 24){
                slot = 9;
                inv.setItem(37, SlotHolder(5,1));
                return;
            }
        }



        p.updateInventory();
    }

    private static void setPlaceHolder(Inventory inv, Player p){

        inv.setItem(36, SlotHolder(7, 1));
        inv.setItem(37, SlotHolder(7, 1));
        inv.setItem(38, SlotHolder(7, 1));
        inv.setItem(39, SlotHolder(7, 1));
        inv.setItem(40, SlotHolder(7, 1));
        inv.setItem(41, SlotHolder(7, 1));
        inv.setItem(42, SlotHolder(7, 1));
        inv.setItem(43, SlotHolder(7, 1));
        inv.setItem(44, SlotHolder(7, 1));



        p.updateInventory();


    }
    public static ItemStack SlotHolder(int shortid, int amount){
        ItemStack i = new ItemStack(Material.getMaterial(160), 1, (short)shortid);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(" ");
        i.setItemMeta(im);
        i.setAmount(amount);
        return i;
    }



    private static void setBoots(Inventory inv, Player p){

        List<String> buyed = new ArrayList<>();
        buyed.add("§8➤ §7Diese §bBoots §7besitzt du §bbereits§7!");

        List<String> notbuyed = new ArrayList<>();

        for(Boots boot : Boots.values()){
            ItemStack i = boot.getItemStack();
            ItemMeta im = i.getItemMeta();
            if(notbuyed.isEmpty())notbuyed.add("§8➤ §b"+boot.getPrise()+" §7Coins");
            notbuyed.set(0, "§8➤ §b"+boot.getPrise()+" §7Coins");
            if(ShopManager.hasbuyed(p, boot)) im.setLore(buyed);
            if(!ShopManager.hasbuyed(p, boot)) im.setLore(notbuyed);
            i.setItemMeta(im);
            inv.setItem(boot.getSlot(), i);
        }

        inv.setItem(43, SlotHolder(5 ,1));

        p.updateInventory();
    }



    @EventHandler
    public void onInteractGadget(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getItem() != null){
                if(!e.getItem().getType().equals(Material.BARRIER)){
                    if(Gadgets.gadgetsexists(e.getItem().getItemMeta().getDisplayName())){
                        Gadgets gd = Gadgets.valueof(e.getItem().getItemMeta().getDisplayName());
                        if(gd == Gadgets.CLAYBOMB){
                            final FallingBlock fb = p.getWorld().spawnFallingBlock(p.getLocation(), 159, (byte)4);
                            fb.setDropItem(false);
                            fb.setHurtEntities(false);
                            fb.setVelocity(fb.getVelocity().add(p.getVelocity()));
                            Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    if(fb.getLocation().getBlock().getData() == fb.getBlockData()){
                                        fb.getLocation().getBlock().setType(Material.AIR);
                                        Random r = new Random();
                                        final List<FallingBlock> list = new ArrayList<>();
                                        for(int i = 0;i<40;i++){
                                            final Location loc = fb.getLocation();
                                            FallingBlock fb1 = loc.getWorld().spawnFallingBlock(loc, 159, (byte) r.nextInt(15));
                                            fb1.setDropItem(false);
                                            fb1.setHurtEntities(false);
                                            fb1.setVelocity(new Vector((r.nextInt(10)-5), r.nextInt(10), (r.nextInt(10)-5)));
                                            fb1.setFallDistance(10);
                                            list.add(fb1);
                                        }

                                        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getInstance(), new Runnable() {
                                            @Override
                                            public void run() {
                                                for(FallingBlock b : list){
                                                    b.getLocation().getBlock().setType(Material.AIR);
                                                }
                                            }
                                        }, 120);

                                    }
                                }
                            }, 60);
                        }
                    }
                }
            }
        }
    }


}
