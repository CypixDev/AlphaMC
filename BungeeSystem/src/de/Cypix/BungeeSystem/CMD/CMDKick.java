package de.Cypix.BungeeSystem.CMD;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDKick extends Command {
    public CMDKick() {
        super("kick");
    }

    String usage = "§8➤ §bBenutze: §7/kick Spieler Grund §7Info | §b_ §7für Lücke §b§ §7für Farben";
    String kicked = "§8➤ §7Der §bSpieler wurde §aerfolgreich §7gekickt !";
    String pr = "§7[§bKick§7] ";

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer pp = (ProxiedPlayer)sender;
            if(pp.hasPermission("weakmc.kick")){
                if(args.length == 0){
                    pp.sendMessage(new ComponentBuilder(usage).create());
                    return;
                }
                if(args.length == 1){
                    ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
                    if(target != null){
                        target.disconnect(new ComponentBuilder("§8➤ §7Du wurdest von §b"+pp.getDisplayName()+" §7gekickt").create());
                        pp.sendMessage(new ComponentBuilder(kicked).create());
                    }else{
                        pp.sendMessage(new ComponentBuilder("§8➤ §7Der §bSpieler §7ist nicht Online ").create());
                    }
                    return;
                }
                if(args.length == 2){
                    ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
                    if(target != null){
                        String reason = args[1];
                        reason = reason.replace("§", "§");
                        reason = reason.replace("_", " ");
                        target.disconnect(new ComponentBuilder("§8➤ §7Du wurdest von §b"+pp.getDisplayName()+" §7gekickt \n §7Grund: §b"+reason).create());
                        pp.sendMessage(new ComponentBuilder(kicked).create());
                    }else{
                        pp.sendMessage(new ComponentBuilder("§8➤ §7Der §bSpieler §7ist nicht Online ").create());
                    }
                    return;
                }
            }
        }
    }
}
