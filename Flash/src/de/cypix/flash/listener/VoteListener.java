package de.cypix.flash.listener;

import de.cypix.flash.countdowns.LobbyCountdown;
import de.cypix.flash.gamestates.LobbyState;
import de.cypix.flash.main.Main;
import de.cypix.flash.manager.MapManager;
import de.cypix.flash.manager.VoteManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class VoteListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase("Vote")){
                e.setCancelled(true);
                //
                Player p = (Player)e.getWhoClicked();
                VoteManager.vote(MapManager.getrealName(e.getCurrentItem().getItemMeta().getDisplayName()), p);

            }
        }catch(NullPointerException e1){

        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(VoteManager.ItemName)){
            LobbyState ls = (LobbyState) Main.getInstace().getGameStatemanager().getCurrentGameState();
            if(ls.getLobbyCountdown().getSeconds() <= 10){
                e.getPlayer().sendMessage(Main.pr+"Die Voting phase ist vorbei !");
            }else {
                e.getPlayer().openInventory(VoteManager.getVoteInv());
            }
        }
    }


}
