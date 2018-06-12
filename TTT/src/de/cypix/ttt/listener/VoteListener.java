package de.cypix.ttt.listener;

import de.cypix.ttt.Var;
import de.cypix.ttt.gamestates.LobbyState;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.MapManager;
import de.cypix.ttt.manager.VoteManager;
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

                Player p = (Player)e.getWhoClicked();

                VoteManager.vote(MapManager.getrealName(e.getCurrentItem().getItemMeta().getDisplayName()), p);
                e.setCancelled(true);
            }
        }catch(NullPointerException e1){

        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        try {
            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(VoteManager.ItemName)) {
                LobbyState ls = (LobbyState) Main.getInstance().getGameStateManager().getCurrentGameState();
                if (ls.getLobbyCountdown().getSeconds() <= 10) {
                    e.getPlayer().sendMessage(Var.pr + "Die Voting phase ist vorbei !");
                } else {
                    e.getPlayer().openInventory(VoteManager.getVoteInv());
                }
            }
        }catch(NullPointerException e1){

        }
    }


}
