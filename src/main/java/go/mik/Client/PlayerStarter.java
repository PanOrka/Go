package go.mik.Client;

import go.mik.Client.Launcher.Launcher;
import go.mik.Client.Logic.Client;

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
