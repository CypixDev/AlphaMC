package de.cypix.tsbot.mc.main;

import de.cypix.tsbot.ts.TeamSpeakBot;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private TeamSpeakBot teamSpeakBot;
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("§cYES IT LOADS !!!!");
        teamSpeakBot = new TeamSpeakBot();
    }

    public static void main(String[] args){

    }

    public static Main getInstance() {
        return instance;
    }
}
