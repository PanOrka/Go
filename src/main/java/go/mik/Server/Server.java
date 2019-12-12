package go.mik.Server;

import go.mik.Server.Logic.Game.GameSystem;
import go.mik.Server.Logic.Game.GameSystemInterface;
import go.mik.Server.Logic.PlayerConnector.BotPlayer;
import go.mik.Server.Logic.PlayerConnector.Player;
import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {
    private int socketPort;

    Server(int socketPort) {
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

                while (gameMakerInput.hasNextLine()) {
                    String response = gameMakerInput.nextLine();

                    if (response.startsWith("PLAY:BOT")) {
                        System.out.println("New Player vs Bot Game");
                        GameSystemInterface gameSystem = new GameSystem();

                        response = response.replaceFirst("PLAY:PVP:", "");
                        PlayerConnector playerConnector1 = new Player(connectorSocket, response, 'b', gameSystem);
                        pool.execute(playerConnector1);

                        PlayerConnector playerConnector2 = new BotPlayer();
                        pool.execute(playerConnector2);
                        break;
                    } else if (response.startsWith("PLAY:PVP")) {
                        System.out.println("New Player vs Player Game");
                        GameSystemInterface gameSystem = new GameSystem();

                        response = response.replaceFirst("PLAY:PVP:", "");

                        PlayerConnector playerConnector1 = new Player(connectorSocket, response, 'b', gameSystem);
                        pool.execute(playerConnector1);

                        connectorSocket = listener.accept();
                        gameMakerInput = new Scanner(connectorSocket.getInputStream());

                        String response2 = gameMakerInput.nextLine().replaceFirst("PLAY:PVP:", "");

                        PlayerConnector playerConnector2 = new Player(connectorSocket, response2, 'w', gameSystem);
                        pool.execute(playerConnector2);
                        break;
                    }
                }
            }
        } catch(IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }
}
