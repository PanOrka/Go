package go.mik.Client;

import go.mik.Client.Launcher.Launcher;

public class PlayerStarter {
    private Client client;
    private volatile boolean start = false;

    public static void main(String[] args) {
        new PlayerStarter();
    }

    private PlayerStarter() {
        this.initialize();
        while(!this.start) {
            // WAIT FOR THREAD
        }
        this.client.start();
    }

    private void initialize() {
        new Launcher(this);
    }

    public void clientInit(Client client) {
        this.client = client;
        this.start = true;
    }
}
