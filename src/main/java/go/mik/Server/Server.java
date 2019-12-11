package go.mik.Server;

import go.mik.Server.Logic.Game.GameSystem;
import go.mik.Server.Logic.Game.GameSystemInterface;
import go.mik.Server.Logic.PlayerConnector.Player;
import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int socketPort;

    public Server(int socketPort) {
        super();
        this.socketPort = socketPort;
    }

    void start() {
        try (ServerSocket listener = new ServerSocket(socketPort)) {
            System.out.println("Server is Running");
            ExecutorService pool = Executors.newFixedThreadPool(200);

            while (true) {

                Socket connectorSocket = listener.accept();
                Scanner gameMakerInput = new Scanner(connectorSocket.getInputStream());

                if (gameMakerInput.nextLine().startsWith("PLAY:BOT")) {
                    GameSystemInterface gameSystem = new GameSystem();

                    PlayerConnector playerConnector1 = new Player(connectorSocket, 'b', gameSystem);
                    pool.execute(playerConnector1);

                    PlayerConnector playerConnector2 = new Player(connectorSocket, 'w', gameSystem);
                    pool.execute(playerConnector2);
                } else if (gameMakerInput.nextLine().startsWith("PLAY:PVP")) {
                    GameSystemInterface gameSystem = new GameSystem();

                    PlayerConnector playerConnector1 = new Player(connectorSocket, 'b', gameSystem);
                    pool.execute(playerConnector1);

                    PlayerConnector playerConnector2 = new Player(connectorSocket, 'w', gameSystem);
                    pool.execute(playerConnector2);
                }
            }
        } catch(IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }
}
