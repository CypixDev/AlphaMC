package de.cypix.alphaapi.stats;

import de.cypix.alphaapi.knockback.KnockBackStats;

public class OfflineStatsPlayer {

    private String uuid;
    private KnockBackStats knockBackStats;

    public OfflineStatsPlayer(String uuid){
        this.uuid = uuid;
        this.knockBackStats = new KnockBackStats(uuid);
    }

    public KnockBackStats getKnockBackStats() {
        return knockBackStats;
    }
}
