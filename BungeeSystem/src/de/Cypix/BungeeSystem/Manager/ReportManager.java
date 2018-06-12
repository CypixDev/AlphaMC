package de.Cypix.BungeeSystem.Manager;

import de.Cypix.BungeeSystem.labymod.SendReport;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    private static List<ProxiedPlayer> list = new ArrayList<ProxiedPlayer>();

    private static HashMap<ProxiedPlayer, ArrayList<String>> info = new HashMap<>();

    private static String pr = "§7[§cREPORT§7] ";

    public static boolean addPlyertoList(ProxiedPlayer pp){
        if(list.contains(pp)){
            list.remove(pp);
            return false;
        }else{
            list.add(pp);
            return true;
        }
    }

    public static void reportPlayer(ProxiedPlayer reported, ProxiedPlayer reporter, String reason){
        for(ProxiedPlayer pp : ProxyServer.getInstance().getPlayers()){
            if(isreporting(pp)){

                TextComponent message = new TextComponent(pr+"Der Spieler "+reported.getName()+" wurde von "+reporter.getName()+" reportet.");
                message.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/report accept "+reported.getName()));
                message.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( pr+"§cTeleportiere dich zu dem Spieler und nehme den Report an !" ).create()));

                ArrayList<String> infolist = new ArrayList<>();

                infolist.add(reporter.getName());
                infolist.add(reason);
                info.put(reported, infolist);

                pp.sendMessage(message);
                pp.sendMessage(new ComponentBuilder("§b§lGrund: "+reason).create());
            }
        }


    }

    public static boolean isreporting(ProxiedPlayer pp){
        if(list.contains(pp)){
            return true;
        }
        return false;
    }
    public static boolean acceptReport(ProxiedPlayer p, ProxiedPlayer reported){
        if(info.containsKey(reported)){
            SendReport.sendReport(p, reported.getName(), info.get(reported).get(0), info.get(reported).get(1), reported.getServer().getInfo().getName());
            info.remove(reported);
            return true;
        }
        return false;
    }
    public static boolean isReported(ProxiedPlayer pp){
        if(info.containsKey(pp))return true;
        return false;
    }
    public static void sendReported(ProxiedPlayer tosend){

        for(ProxiedPlayer pp : info.keySet()){
            if(isReported(pp)){
                ArrayList<String> infoo = info.get(pp);
                TextComponent message = new TextComponent(pr+"Spieler "+pp.getName()+" wurde von "+infoo.get(0)+" reported aus dem Grund: "+infoo.get(1));
                message.setClickEvent(new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/report accept "+pp.getName()));
                message.setHoverEvent(new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( pr+"§cTeleportiere dich zu dem Spieler und nehme den Report an !" ).create()));
                tosend.sendMessage(message);
            }
        }

    }
}
