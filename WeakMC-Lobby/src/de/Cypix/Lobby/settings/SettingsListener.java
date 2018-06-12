package de.Cypix.Lobby.settings;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.settings.GenerallyOptions;
import de.cypix.alphaapi.settings.LobbyOption;
import de.cypix.alphaapi.settings.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SettingsListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getCurrentItem() != null){
            if(e.getClickedInventory().getTitle().equalsIgnoreCase("§b§lSettings")){
                e.setCancelled(true);
                Player p = (Player)e.getWhoClicked();
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEnabled")){
                    e.getClickedInventory().setItem(e.getSlot(), item(false));
                    p.updateInventory();
                    return;
                }
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDisabled")){
                    e.getClickedInventory().setItem(e.getSlot(), item(true));
                    p.updateInventory();
                    return;
                }
            }
        }
    }

    private static ItemStack item(boolean enabled){
        ItemStack i;
        if(enabled){
            i = new ItemStack(Material.getMaterial(351), 1, (short)10);
        }else{
            i = new ItemStack(Material.getMaterial(351), 1, (short)1);
        }
        ItemMeta im = i.getItemMeta();
        if(enabled){
            im.setDisplayName("§aEnabled");
        }else{
            im.setDisplayName("§cDisabled");
        }

        i.setItemMeta(im);
        return i;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        boolean ok = false;
        if(e.getInventory().getTitle().equalsIgnoreCase("§b§lSettings")){
            Settings settings = AlphaAPI.getInstance().getStatsPlayer((Player) e.getPlayer()).getSettings();
            Inventory inv = e.getInventory();
            for(int i = 0;i < e.getInventory().getSize();i++){
                if(i < 17){
                    //lobby
                    if(inv.getItem(i) != null
                            && !inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§aEnabeld")
                            && !inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cDisabled")){
                        LobbyOption lo = LobbyOption.valueOfDisplayName(inv.getItem(i).getItemMeta().getDisplayName());
                        boolean enabled;
                        enabled = inv.getItem(i + 9).getItemMeta().getDisplayName().equalsIgnoreCase("§aEnabled");
                        if(enabled){
                            if(settings.getLobbySettings().getOptionlist().containsKey(lo)){
                                settings.getLobbySettings().removeOption(lo);
                                ok = true;
                            }
                        }else{
                            if(!settings.getLobbySettings().getOptionlist().containsKey(lo)){
                                settings.getLobbySettings().addOption(lo);
                                ok = true;
                            }
                        }
                    }
                }else if(i > 26){
                    if(inv.getItem(i) != null
                            && !inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§aEnabeld")
                            && !inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§cDisabled")){
                        GenerallyOptions lo = GenerallyOptions.valueOfDisplayName(inv.getItem(i).getItemMeta().getDisplayName());
                        boolean enabled;
                        enabled = inv.getItem(i + 9).getItemMeta().getDisplayName().equalsIgnoreCase("§aEnabled");
                        if(enabled){
                            if(settings.getGenerallySettings().getOptionlist().containsKey(lo)){
                                settings.getGenerallySettings().removeOption(lo);
                                ok = true;
                            }
                        }else{
                            if(!settings.getGenerallySettings().getOptionlist().containsKey(lo)){
                                settings.getGenerallySettings().addOption(lo);
                                ok = true;
                            }
                        }
                    }
                }
            }
            if(ok){
                e.getPlayer().sendMessage("§b§lDeine geänderten Einstellungen wurden gespeichert !");
            }
        }
    }

}
