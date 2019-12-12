package go.mik.Bot;

public class Bot implements ServerConnector, Runnable {

    public static void setBot(int socketPort) {
        new Thread(new Bot(socketPort)).start();
    }

    private Bot(int socketPort) {

    }

    @Override
    public void run() {

    }
}
