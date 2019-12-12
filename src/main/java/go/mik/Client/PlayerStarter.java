package go.mik.Client;

import go.mik.Client.Launcher.Launcher;


public class PlayerStarter {
    private volatile ServerConnector serverConnector;
    private volatile boolean playWithBot;
    private volatile boolean start;

    public static void main(String[] args) {
        new PlayerStarter();
    }

    private PlayerStarter() {
        this.playWithBot = false;
        this.start = false;
        this.initialize();
        while (!this.start) {
            // WAIT FOR LAUNCHER
        }
        this.serverConnector.start(playWithBot);
    }

    private void initialize() {
        new Launcher(this);
    }

    public void clientInit(ServerConnector serverConnector, boolean playWithBot) {
        this.serverConnector = serverConnector;
        this.playWithBot = playWithBot;
        this.start = true;
    }
}
