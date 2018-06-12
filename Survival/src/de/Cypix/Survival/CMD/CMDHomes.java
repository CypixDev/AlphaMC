package de.Cypix.Survival.CMD;

import de.Cypix.Survival.Main.main;
import de.Cypix.Survival.Manager.HomeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CMDHomes implements CommandExecutor {

    int idelID;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            HomeManager hm = new HomeManager(p);
            Inventory inv = Bukkit.createInventory(null, 3*9, "§aHomes");
            HomeManager.iedl.put(p, 0);
            idelID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    if(!HomeManager.idelID.containsKey(p)){
                        HomeManager.idelID.put(p, idelID);
                    }

                    inv.setItem(HomeManager.iedl.get(p), hm.getItem(hm.getHomes().get(HomeManager.iedl.get(p))));

                    p.updateInventory();

                    HomeManager.iedl.put(p, HomeManager.iedl.get(p)+1);

                    if(HomeManager.iedl.get(p) == hm.getHomes().size()){
                        Bukkit.getScheduler().cancelTask(HomeManager.idelID.get(p));
                        HomeManager.iedl.remove(p);
                        HomeManager.idelID.remove(p);
                    }
                }
            },2, 2);

            p.openInventory(inv);
        }


        return false;
    }
}
