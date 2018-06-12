package de.Cypix.BungeeSystem.CMD;


import de.Cypix.BungeeSystem.Manager.BanManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDUnBan extends Command {
    public CMDUnBan(){
        super("unban");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("weakmc.ban")){
            if(sender instanceof ProxiedPlayer){
                if(args.length == 1) {
                    ProxiedPlayer p = (ProxiedPlayer) sender;
                    if (!BanManager.isBanned(args[0]) && BanManager.isMuted(args[0]))
                        p.sendMessage(new ComponentBuilder("Der SPieler ist nicht gebannt aber gemuted !(/unmute)").create());
                    if (!BanManager.isBanned(args[0]) && BanManager.isMuted(args[0])) return;
                    if (!BanManager.isBanned(args[0]))
                        p.sendMessage(new ComponentBuilder("Der Spieler ist nicht gebannt !").create());
                    if (!BanManager.isBanned(args[0])) return;
                    BanManager.unBan(args[0]);
                    p.sendMessage(new ComponentBuilder("Der Spieler " + args[0] + " wurde entbannt !").create());
                    return;
                }
            }
        }
    }
}
