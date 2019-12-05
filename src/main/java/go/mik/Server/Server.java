package go.mik.Server;

import go.mik.Server.Logic.Game.GameSystemInterface;
import go.mik.Server.Logic.PlayerConnector.Player;
import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends GameSystemInterface {
    private int socketPort;

    public Server(int socketPort) {
        super();
        this.socketPort = socketPort;
    }

    void start() {
        try (ServerSocket listener = new ServerSocket(socketPort)) {
            System.out.println("Server is Running");
            ExecutorService pool = Executors.newFixedThreadPool(2);

            PlayerConnector playerConnector1 = new Player(listener.accept(), 'b', this);
            PlayerConnector playerConnector2 = new Player(listener.accept(), 'w', this);

            pool.execute(playerConnector1);
            pool.execute(playerConnector2);
        } catch(IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }
}
