package go.mik.Server;

import go.mik.Server.Launcher.Launcher;
import go.mik.Server.Logic.Server;

public class ServerStarter {
    private Server server;
    private volatile boolean start = false;

    public static void main(String[] args) {
        new ServerStarter();
    }

    private ServerStarter() {
        this.initialize();
        while(!this.start) {
            // WAIT FOR THREAD
        }
        System.out.println("Go on");
        this.server.start();
    }

    private void initialize() {
        new Launcher(this);
    }

    public void serverInit(Server server) {
        this.server = server;
        this.start = true;
    }
}
