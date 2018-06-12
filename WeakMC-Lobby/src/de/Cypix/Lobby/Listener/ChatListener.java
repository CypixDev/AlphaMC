package de.Cypix.Lobby.Listener;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){


        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(e.getPlayer().getUniqueId());
        PermissionGroup perms = CloudAPI.getInstance()
                .getPermissionGroup(cloudPlayer.getPermissionEntity()
                        .getGroups().iterator().next().getGroup());

        CloudServer.getInstance().updateNameTags(e.getPlayer());

        String trans = ChatColor.translateAlternateColorCodes('&', perms.getPrefix()+e.getPlayer().getName()+" | ");
        TextComponent message = new TextComponent(trans);
        //message.setClickEvent( new ClickEvent( ClickEvent.Action.OPEN_URL, "http://xyz.de" ) );
        String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
        message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(timeStamp).create() ) );


        e.setCancelled(true);
        if(e.getPlayer().hasPermission("Lobby.color")){
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }
        message.addExtra(e.getMessage());


        for(Player p : Bukkit.getOnlinePlayers()){
            p.spigot().sendMessage(message);
        }
    }

}
