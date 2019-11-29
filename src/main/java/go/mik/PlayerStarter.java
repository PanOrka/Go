package go.mik;

import go.mik.Client.Client;
import go.mik.Launcher.Launcher;

import java.net.Socket;

public class PlayerStarter {
    public static void main(String[] args) {
        new PlayerStarter();
    }

    private PlayerStarter() {
        this.initialize();
    }

    private void initialize() {
        Launcher launcher = new Launcher(this);
    }

    public void clientInit(Client client, String nickName, Socket socket) {

    }
}
