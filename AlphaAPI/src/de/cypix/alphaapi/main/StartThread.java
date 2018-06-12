package de.cypix.alphaapi.main;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.server.ServerState;

public class StartThread extends Thread {

    @Override
    public void run() {
        try {
            sleep(10000);
            CloudServer.getInstance().setServerStateAndUpdate(ServerState.LOBBY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
