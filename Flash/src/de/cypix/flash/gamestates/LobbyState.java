package de.cypix.flash.gamestates;

import de.cypix.flash.countdowns.LobbyCountdown;

public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = 2,
            MAX_PLAYERS = 5;

    private LobbyCountdown lobbyCountdown;

    @Override
    public void start() {
        System.out.println("Lobby-State");
        lobbyCountdown = new LobbyCountdown();
        lobbyCountdown.idel();
    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
