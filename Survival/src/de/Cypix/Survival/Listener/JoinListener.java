package de.Cypix.Survival.Listener;

import de.Cypix.Survival.API.Title;
import de.Cypix.Survival.CMD.CMDVanish;
import de.Cypix.Survival.FileManager.Var;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener  implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(Var.getString("Messages", "Message.Join.Chat"));
        Title.sendTitle(e.getPlayer(), 20, 40, 20 , Var.getString("Messages","Message.Join.Title"), Var.getString("Messages", "Message.Join.SubTitle"));
        Player p = e.getPlayer();

        //vanish

        for(Player pp : Bukkit.getOnlinePlayers()){
            pp.showPlayer(p);
        }
        for(Player pp : Bukkit.getOnlinePlayers()){
            p.showPlayer(pp);
        }

        for(int i = 0;i<CMDVanish.list.size();i++){
            if(!p.hasPermission("Survival.vanish.see")) {
                p.hidePlayer(CMDVanish.list.get(i));
            }
        }

        //vanish//

    }


    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player p = e.getPlayer();

        for(Player pp : Bukkit.getOnlinePlayers()){
            pp.showPlayer(p);
        }
        if(CMDVanish.list.contains(p)){
            CMDVanish.list.remove(p);
        }

    }

}
