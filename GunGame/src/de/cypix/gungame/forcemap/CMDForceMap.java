package de.cypix.gungame.forcemap;

import de.cypix.gungame.main.GunGame;
import de.cypix.gungame.setup.Setup;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class CMDForceMap implements CommandExecutor {

    File file = new File(GunGame.getInstance().getDataFolder(), "Maps.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("GunGame.forcemap")){
                if(!GunGame.getInstance().getGameManager().isMapPreferred()){
                    if(args.length == 0){
                        Inventory inv = Bukkit.createInventory(null, 9, "Maps");
                        int l = 0;
                        for(String map : Setup.getMaps()){
                            ItemStack item = cfg.getItemStack("Map."+map+".Item");
                            ItemMeta im = item.getItemMeta();
                            im.setDisplayName(Setup.getMaps().get(l));
                            item.setItemMeta(im);
                            inv.setItem(l, item);
                            l++;
                        }
                        p.openInventory(inv);
                    }
                }else p.sendMessage("Es wurde bereits eine Map ausgesucht !");
            }
        }
        return false;
    }
}
