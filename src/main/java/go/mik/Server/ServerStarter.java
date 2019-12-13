package go.mik.Server;

public class ServerStarter {
    private Server server;

    public static void main(String[] args) {
        new ServerStarter();
    }

    private ServerStarter() {
        this.initialize(new Server(1111));
        this.server.start();
    }

    private void initialize(Server server) {
        this.server = server;
    }
}
