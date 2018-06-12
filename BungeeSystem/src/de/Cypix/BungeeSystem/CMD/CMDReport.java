package de.Cypix.BungeeSystem.CMD;

import de.Cypix.BungeeSystem.Manager.ReportManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CMDReport extends Command {

    private static String pr = "§8➤ §bReport ";

    public CMDReport() {
        super("report");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer p =(ProxiedPlayer)sender;
        if(p.hasPermission("weakmc.login")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("login")){
                    if(ReportManager.addPlyertoList(p)){
                        p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Du wurdest §beingeloggt").create());
                    }else{
                        p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Du wurdest §bausgeloggt").create());
                    }
                    return;
                    //
                }
                if(args[0].equalsIgnoreCase("list")){
                    ReportManager.sendReported(p);
                    return;
                }
            }
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("accept")){
                    if(ProxyServer.getInstance().getPlayer(args[1]) != null){
                        if(ReportManager.acceptReport(p, ProxyServer.getInstance().getPlayer(args[1]))){
                            p.connect(ProxyServer.getInstance().getPlayer(args[1]).getServer().getInfo());
                            p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Du wurdest §bteleportiert").create());
                        }else{
                            p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Dieser §bReport §7wird bereits §bbearbeitet").create());
                        }
                    }else{
                        p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Dieser §bSpieler §7ist §bOffline").create());
                    }
                    return;
                }
            }
            p.sendMessage(new ComponentBuilder(pr+"§bBenutze §8➤ §7/report login §8| §7accept Spieler").create());

        }else{
            if(args.length == 2){
                if(ProxyServer.getInstance().getPlayer(args[0]) != null){
                    ReportManager.reportPlayer(ProxyServer.getInstance().getPlayer(args[0]), p, args[1]);
                    p.sendMessage(new ComponentBuilder(pr+"§8➤ §7Danke für deinen §bReport").create());
                }
                return;
            }
            p.sendMessage(new ComponentBuilder(pr+"bBenutze §8➤ §7/report §bSpieler §8| §bGrund").create());
        }
    }
}
