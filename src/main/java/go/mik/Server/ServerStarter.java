package go.mik.Server;

import go.mik.Server.Launcher.Launcher;

public class ServerStarter {
    public static void main(String[] args) {
        new ServerStarter();
    }

    private ServerStarter() {
        new Launcher();
    }
}
