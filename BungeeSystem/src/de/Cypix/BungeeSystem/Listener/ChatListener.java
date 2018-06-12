package de.Cypix.BungeeSystem.Listener;

import de.Cypix.BungeeSystem.Manager.BanManager;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(ChatEvent e){
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        if(BanManager.isMuted(p.getUniqueId())){
            if(e.getMessage().startsWith("/"))return;
            e.setCancelled(true);
            p.sendMessage(new ComponentBuilder("§7§l▛▀▀▀▀▀▀ §b§lWeakMC §7§l▀▀▀▀▀▀").create());
            p.sendMessage(new ComponentBuilder("§7§l▌§7§lDu bist noch für \n§7§l▌§c§l"+BanManager.getRemainingTime(p.getUniqueId())+" §7§lgemuted.").create());
        }else{
            if(e.getMessage().equalsIgnoreCase("7L")){
                e.setCancelled(true);
                ((ProxiedPlayer) e.getSender()).sendMessage(new ComponentBuilder("§b§lChat §r§7➽ Da hat sich wohl jemand vertippt xD").create());
            }
        }
    }
}
