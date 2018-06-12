package de.Cypix.BungeeSystem.Listener;

import de.Cypix.BungeeSystem.MYSQL.CoinsMYSQL;
import de.Cypix.BungeeSystem.Main.main;
import de.Cypix.BungeeSystem.Manager.BanManager;
import de.Cypix.BungeeSystem.Manager.ReportManager;
import de.Cypix.BungeeSystem.labymod.Coins;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class LoginListener implements Listener {


    @EventHandler
    public void onLogin(final LoginEvent e){
        if(BanManager.isBanned(e.getConnection().getName())) {
            BanManager.checkBan(e.getConnection().getUniqueId(), e.getConnection().getName());
            if(BanManager.isBanned(e.getConnection().getName())) {
                e.setCancelled(true);
                e.setCancelReason(new ComponentBuilder(" \n §7§l┏╋§7§m§l---------§r§7§l◥◣◆◢◤§7§m§l---------§r§7§l╋┓§r \n \n §bＷｅａｋＭＣ §8× §7Du wurdest von §b" + BanManager.getOperator(e.getConnection().getUniqueId())
                        + " §7gebannt! \n §7Deine §bBan-Id lautet §8× §b" + BanManager.getbanid(e.getConnection().getName())
                        + " \n  \n §7Unrechter §bBan§7? §7TeamSpeak §8× §bWeakMC.de §7Website §8× §bweb.weakmc.de \n \n §7Grund §8× §b"
                        + BanManager.getReason(e.getConnection().getName()) + " \n \n §r§7Verbleibende §bZeit §8× §7"
                        + BanManager.getRemainingTime(e.getConnection().getName()) + "\n \n §7§l┗╋§7§m§l---------§r§7§l◥◣◆◢◤§7§m§l----------§r§7§l╋┛§r \n").create());
                return;
            }
        }else{
            ProxyServer.getInstance().getScheduler().schedule(main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Title test = ProxyServer.getInstance().createTitle();
                    test.title(new ComponentBuilder("§7Herzlich Willkommen §b"+e.getConnection().getName()).create());
                    test.subTitle(new ComponentBuilder("§f" + "§7Auf §bWeakMC.de").create());
                    test.fadeIn(20*3);
                    test.fadeOut(1*20);
                    test.stay(10);
                    test.send(ProxyServer.getInstance().getPlayer(e.getConnection().getName()));

                    ProxiedPlayer p = ProxyServer.getInstance().getPlayer(e.getConnection().getName());
                    Coins.updatePoints(p, CoinsMYSQL.getCoins(e.getConnection().getName()));
                    //update coins !

                    if(p.hasPermission("report.login")){
                        //
                        ReportManager.addPlyertoList(p);
                        p.sendMessage(new ComponentBuilder("§bReport §8➤ §7Du wurdest §beingeloggt").create());
                    }

                }
            }, 20, TimeUnit.MILLISECONDS);

        }
    }
}
