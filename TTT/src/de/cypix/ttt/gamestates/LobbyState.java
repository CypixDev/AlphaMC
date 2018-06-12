package de.cypix.ttt.gamestates;

import de.cypix.ttt.countdown.LobbyCountdown;

public class LobbyState extends GameState {

    public static final int MIN_PLAYERS = 3, MAX_PLAYERS = 12;

    private LobbyCountdown lobbyCountdown;


    @Override
    public void start() {
        lobbyCountdown = new LobbyCountdown();
        lobbyCountdown.idle();
    }

    @Override
    public void stop() {

    }

    public LobbyCountdown getLobbyCountdown() {
        return lobbyCountdown;
    }
}
