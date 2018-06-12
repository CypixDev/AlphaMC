package de.cypix.tsbot.ts;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import de.cypix.tsbot.mc.main.Main;

import java.util.ArrayList;
import java.util.List;

public class TeamSpeakBot {

    public TeamSpeakBot() {
        init();
    }
    private static final TS3Config ts3Config = new TS3Config();;
    private static final TS3Query ts3Query = new TS3Query(ts3Config);;
    private static final TS3Api ts3Api = ts3Query.getApi();
    private static List<Integer> groups = new ArrayList<>();

    private void init() {


        groups.add(3290641);
        groups.add(3290668);
        groups.add(3290642);
        groups.add(3290644);
        groups.add(3290643);

        ts3Config.setHost("62.104.20.205");

        ts3Query.connect();

        ts3Api.login("Vetox-Bot", "wSydRoTt");
        ts3Api.selectVirtualServerByPort(10081);
        ts3Api.setNickname("Vetox-Bot");

        Main.getInstance().getLogger().info("Der Ts-Bot hat den Server betreten !");

        SupportEvents.loadEvents();

        Main.getInstance().getLogger().info("TeamSpeak-Bot Fertig !");
    }

    public static TS3Api getTs3Api() {
        return ts3Api;
    }

    public static TS3Query getTs3Query() {
        return ts3Query;
    }

    public static TS3Config getTs3Config() {
        return ts3Config;
    }

    public static List<Integer> getGroups() {
        return groups;
    }
}