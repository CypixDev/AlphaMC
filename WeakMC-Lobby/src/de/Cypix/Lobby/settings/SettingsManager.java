package de.Cypix.Lobby.settings;

import de.cypix.alphaapi.main.AlphaAPI;
import de.cypix.alphaapi.settings.GenerallyOptions;
import de.cypix.alphaapi.settings.LobbyOption;
import de.cypix.alphaapi.stats.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class SettingsManager {

    public static void openInv(Player p){
        Inventory inv = Bukkit.createInventory(null, 6*9, "§b§lSettings");

        StatsPlayer sp = AlphaAPI.getInstance().getStatsPlayer(p);
        HashMap<LobbyOption, Integer> lobbyOptionList = sp.getSettings().getLobbySettings().getOptionlist();
        HashMap<GenerallyOptions, String> generallyOptionslist = sp.getSettings().getGenerallySettings().getOptionlist();


        inv.setItem(10, LobbyOption.NaigatorAnimation.getItemStack());
        inv.setItem(19, item(lobbyOptionList, LobbyOption.NaigatorAnimation, sp));

        inv.setItem(11, LobbyOption.Effects.getItemStack());
        inv.setItem(20, item(lobbyOptionList, LobbyOption.Effects, sp));

        inv.setItem(12, LobbyOption.Pets.getItemStack());
        inv.setItem(21, item(lobbyOptionList, LobbyOption.Pets, sp));

        inv.setItem(13, LobbyOption.ScoreBoard.getItemStack());
        inv.setItem(22, item(lobbyOptionList, LobbyOption.ScoreBoard, sp));

        inv.setItem(14, LobbyOption.TeleportAnimation.getItemStack());
        inv.setItem(23, item(lobbyOptionList, LobbyOption.TeleportAnimation, sp));

        inv.setItem(28, GenerallyOptions.AUTOStats.getItemStack());
        inv.setItem(28+9, item(generallyOptionslist, GenerallyOptions.AUTOStats, sp));


        p.openInventory(inv);
    }

    private static ItemStack item(HashMap<LobbyOption, Integer> map, LobbyOption option, StatsPlayer sp){
        ItemStack i;
        boolean enabled = map.containsKey(option);
        if(!enabled){
            i = new ItemStack(Material.getMaterial(351), 1, (short)10);
        }else{
            i = new ItemStack(Material.getMaterial(351), 1, (short)1);
        }
        ItemMeta im = i.getItemMeta();
        if(!enabled){
            im.setDisplayName("§aEnabled");
        }else{
            im.setDisplayName("§cDisabled");
        }

        i.setItemMeta(im);
        return i;
    }
    private static ItemStack item(HashMap<GenerallyOptions, String> map, GenerallyOptions option, StatsPlayer sp){
        ItemStack i;
        boolean enabled = map.containsKey(option);
        if(!enabled){ i = new ItemStack(Material.getMaterial(351), 1, (short)10); }else{ i = new ItemStack(Material.getMaterial(351), 1, (short)1); }
        ItemMeta im = i.getItemMeta();
        if(!enabled){
            im.setDisplayName("§aEnabled");
        }else{
            im.setDisplayName("§cDisabled");
        }

        i.setItemMeta(im);
        return i;
    }

}
