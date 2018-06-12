package de.Cypix.CityBuild.Listener;

import de.Cypix.CityBuild.File.PlayerFile;
import de.Cypix.CityBuild.File.Var;
import de.Cypix.CityBuild.Manager.KitManager;
import de.Cypix.CityBuild.Manager.MoneyManager;
import de.Cypix.CityBuild.Manager.SpawnManager;
import de.Cypix.CityBuild.Manager.VanishManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        for(Player pp : VanishManager.getInvisebels()){
            p.hidePlayer(pp);
        }

        if(!VanishManager.isvisible(p)){
            VanishManager.setinvisible(p);
            p.sendMessage(Var.pr+"§7Du bist §bunsichtbar!");
        }else{
            e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
            e.setJoinMessage(Var.pr+e.getPlayer().getName()+" hat §bCityBuild §7betreten.");
        }

        if(!p.hasPlayedBefore()){
            p.teleport(SpawnManager.getSpawn());
            KitManager.setDefaultinv(p);
        }
        if(!PlayerFile.isRegistert(p.getUniqueId().toString())){
            new MoneyManager(p).setBalace(500);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(VanishManager.isvisible(p)) {
            e.setQuitMessage(Var.pr + p.getName() + " hat §bCityBuild §7verlassen.");
            VanishManager.setvisible(p);
        }
        e.setQuitMessage("");
    }

}
