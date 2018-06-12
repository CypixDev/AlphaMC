package de.cypix.ttt.listener;

import de.cypix.ttt.Var;
import de.cypix.ttt.main.Main;
import de.cypix.ttt.manager.TeamManager;
import de.cypix.ttt.manager.TicketManager;
import de.cypix.ttt.mysql.PlayerStats;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TicketListener implements Listener {

    @EventHandler
    public void onOpenInv(PlayerInteractEvent e){
        try{
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) ||e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(TicketManager.InvItemName)){
                    Player p = e.getPlayer();
                    p.openInventory(TicketManager.OpenTicketInv(p));
                }
            }
        }catch(NullPointerException e1){

        }
    }

    @EventHandler
    public void onTicketClick(InventoryClickEvent e){
        try{
            if(e.getClickedInventory().getTitle().equalsIgnoreCase(TicketManager.InvName)){
                e.setCancelled(true);
                if(e.getCurrentItem() != null){
                    Player p = (Player)e.getWhoClicked();
                    PlayerStats ps = new PlayerStats(p);
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(TicketManager.InInvTraoitorTicketName)){
                        if(ps.hasTraitorpass()){
                            if(TeamManager.getMaxTraitors() > TeamManager.getTraitors().size()){
                                p.sendMessage(Var.pr+"Du wirst Traitor sein !");
                                p.closeInventory();
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 10 , 10);
                                TeamManager.addTraitor(p);
                                TeamManager.addToUseList(p, "Traitor");
                            }else{
                                p.sendMessage(Var.pr+ "Für die Spieler anzahl kannst du kein Ticket mehr einlösen");
                                p.closeInventory();
                                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10 ,10);
                            }
                        }else{
                            p.sendMessage(Var.pr+"Du hast keine Traitor-Ticket mehr !");
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10 ,10);
                        }
                        return;
                    }
                    if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(TicketManager.InInvDetectiveTicketName)){
                        if(ps.hasDetectivepass()){
                            if(TeamManager.getMaxDetectives() > TeamManager.getDetectives().size()){
                                p.sendMessage(Var.pr+"Du wirst §2Detective sein !");
                                TeamManager.addDetective(p);
                                TeamManager.addToUseList(p, "Detective");
                                p.closeInventory();
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 10 , 10);

                            }else{
                                p.sendMessage(Var.pr+"Für die Spieler anzahl kannst du kein Ticket mehr einlösen");
                                p.closeInventory();
                                p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10 ,10);
                            }
                        }else{
                            p.sendMessage(Var.pr+"Du hast keine Detecktive-Ticket mehr !");
                            p.closeInventory();
                            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10 ,10);
                        }
                        return;
                    }

                }
            }
        }catch (NullPointerException e1){

        }
    }
}
