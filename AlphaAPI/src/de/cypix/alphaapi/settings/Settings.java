package de.cypix.alphaapi.settings;

import de.cypix.alphaapi.stats.StatsPlayer;

public class Settings {

    private StatsPlayer sp;
    private Generally generally;
    private Lobby lobby;

    public Settings(StatsPlayer sp){
        this.sp = sp;
        this.lobby = new Lobby(sp);
        this.generally = new Generally(sp);
        sp.getPlayer().sendMessage("§b§lDeine Einstellungen wurden geladen !");
    }

    public Lobby getLobbySettings() {
        return lobby;
    }

    public Generally getGenerallySettings() {
        return generally;
    }
}