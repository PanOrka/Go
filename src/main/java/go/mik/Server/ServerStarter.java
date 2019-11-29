package go.mik.Server;

import go.mik.Server.Launcher.Launcher;
import go.mik.Server.Server.Server;

public class ServerStarter {
    public static void main(String[] args) {
        new ServerStarter();
    }

    private ServerStarter() {
        this.initialize();
    }

    private void initialize() {
        new Launcher();
    }

    public void serverInit(Server server) {

    }
}
