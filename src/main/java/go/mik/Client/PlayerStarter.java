package go.mik.Client;

public class PlayerStarter {
    private ServerConnector serverConnector;
    private boolean playWithBot = false;

    public static void main(String[] args) {
        new PlayerStarter();
    }

    private PlayerStarter() {
        this.initialize();
        this.serverConnector.start(playWithBot);
    }

    private void initialize() {
        //new Launcher(this);
        try {
            this.clientInit(new Player("test", "127.0.0.1", 1111));
        } catch (Exception ex) {
            // nothing
        }
    }

    public void clientInit(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
    }

    public void setPlayWithBot(boolean playWithBot) {
        this.playWithBot = playWithBot;
    }
}
