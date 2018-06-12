package de.Cypix.CityBuild.CMD;

import de.Cypix.CityBuild.File.Var;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class CMDHead implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(p.hasPermission("citybuild.head")){
                if(args.length == 1){
                    p.getInventory().addItem(createItemHead(args[0]));
                    return true;
                }
            }else p.sendMessage(Var.noperm);
        }
        return false;
    }

    public static ItemStack createItemHead(String name) {

        ItemStack item = new ItemStack(Material.getMaterial(397), 1, (short)3);
        SkullMeta mitem = (SkullMeta) item.getItemMeta();

        mitem.setOwner(name);
        item.setItemMeta(mitem);
        return item;
    }

}
