package de.cypix.ttt.gamestates;

public abstract class GameState {

    public static final int LOBBY_STATE = 0
            , INGAMESTATE = 1
            , ENDINGSTATE = 2;

    public abstract void start();
    public abstract void stop();

}
