package go.mik;

import go.mik.Client.Client;
import go.mik.Launcher.Launcher;

public class PlayerStarter {
    private Client client;

    public static void main(String[] args) {
        new PlayerStarter();
    }

    private PlayerStarter() {
        this.initialize();
    }

    private void initialize() {
        new Launcher(this);
    }

    public void clientInit(Client client) {
        this.client = client;
    }
}
