package de.cypix.alphaapi.stats;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import de.cypix.alphaapi.buildffa.BuildFFAInv;
import de.cypix.alphaapi.buildffa.BuildFFAStats;
import de.cypix.alphaapi.coins.Coins;
import de.cypix.alphaapi.gungame.GunGameStats;
import de.cypix.alphaapi.knockback.KnockBackKits;
import de.cypix.alphaapi.knockback.KnockBackStats;
import de.cypix.alphaapi.settings.Settings;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StatsPlayer {

    private Player player;
    private KnockBackStats knockBackStats;
    private KnockBackKits  knockBackKits;
    private BuildFFAInv buildFFAInv;
    private BuildFFAStats buildFFAStats;
    private GunGameStats gunGameStats;
    private Settings settings;
    private Coins coins;

    public StatsPlayer(Player p){
        player = p;
        knockBackStats = new KnockBackStats(this);
        knockBackKits = new KnockBackKits(this);
        buildFFAInv = new BuildFFAInv(this);
        buildFFAStats = new BuildFFAStats(this);
        gunGameStats = new GunGameStats(this);
        coins = new Coins(this);
        settings = new Settings(this);
    }
    public StatsPlayer(String uuid){
        player = Bukkit.getPlayer(uuid);
    }

    public KnockBackStats getKnockBackStats() {
        return knockBackStats;
    }

    //TODO: like KnockBack Stats
    public KnockBackKits getKnockBackKits() {
        return knockBackKits;
    }

    public BuildFFAInv getBuildFFAInv() {
        return buildFFAInv;
    }

    public BuildFFAStats getBuildFFAStats() {
        return buildFFAStats;
    }

    public Coins getCoins() {
        return coins;
    }

    public GunGameStats getGunGameStats() {
        return gunGameStats;
    }

    public Player getPlayer() {
        return player;
    }

    public Settings getSettings() {
        return settings;
    }

    public CloudPlayer getCloudPlayer(){
        return CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
    }

    public ServerInfo getServerInfo() {
        return CloudAPI.getInstance().getServerInfo(CloudAPI.getInstance()
                .getOnlinePlayer(player.getUniqueId()).getServer());
    }
}
