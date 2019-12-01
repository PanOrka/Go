package go.mik.Server;

import go.mik.Server.Logic.GameAdapter;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int socketPort;

    public Server(int socketPort) {
        this.socketPort = socketPort;
    }

    void start() {
        try (ServerSocket listener = new ServerSocket(socketPort)) {
            System.out.println("Server is Running");
            ExecutorService pool = Executors.newFixedThreadPool(2);

            GameAdapter game = new GameAdapter();
            pool.execute(game.new Player(listener.accept(), 'B'));
            pool.execute(game.new Player(listener.accept(), 'W'));
        } catch(IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }
}
