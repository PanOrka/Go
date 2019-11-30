package go.mik.Client;

import go.mik.Client.Launcher.Launcher;
import go.mik.Client.Logic.Client;

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
        System.out.println("Go on");
        this.client.start();
    }

    private void initialize() {
        Thread launcher = new Thread(new Launcher(this));
        launcher.start();
    }

    public void clientInit(Client client) {
        this.client = client;
        this.start = true;
    }
}
