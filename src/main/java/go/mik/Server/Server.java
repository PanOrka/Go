package go.mik.Server;

import go.mik.Bot.BotFactory;
import go.mik.Server.Logic.Game.GameSystem;
import go.mik.Server.Logic.Game.GameSystemInterface;
import go.mik.Server.Logic.PlayerConnector.BotPlayer;
import go.mik.Server.Logic.PlayerConnector.Player;
import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {
    private int socketPort;
    private int playerCounter;
    private GameSystemInterface gameSystemForPVP;

    Server(int socketPort) {
        this.socketPort = socketPort;
        this.playerCounter = 0;
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

                        response = response.replaceFirst("PLAY:BOT:", "");
                        PlayerConnector playerConnector1 = new Player(connectorSocket, response, 'b', gameSystem);
                        pool.execute(playerConnector1);

                        BotFactory.setBot("127.0.0.1", socketPort, BotFactory.Difficulty.NORMAL);
                        connectorSocket = listener.accept();
                        PlayerConnector playerConnector2 = new BotPlayer(connectorSocket, 'w', gameSystem);
                        pool.execute(playerConnector2);
                        break;
                    } else if (response.startsWith("PLAY:PVP")) {
                        if (this.playerCounter == 0 || !this.gameSystemForPVP.getAvailable()) {
                            System.out.println("New Player vs Player Game");
                            this.playerCounter = 0;
                            this.gameSystemForPVP = new GameSystem();
                            response = response.replaceFirst("PLAY:PVP:", "");

                            PlayerConnector playerConnector1 = new Player(connectorSocket, response, 'b', this.gameSystemForPVP);
                            pool.execute(playerConnector1);
                            this.playerCounter++;
                            this.playerCounter%=2;
                        } else {
                            response = response.replaceFirst("PLAY:PVP:", "");

                            PlayerConnector playerConnector1 = new Player(connectorSocket, response, 'w', this.gameSystemForPVP);
                            pool.execute(playerConnector1);
                            this.playerCounter++;
                            this.playerCounter%=2;
                        }
                        break;
                    }
                }
            }
        } catch(Exception ex) {
            System.out.println("Server problem");
            System.err.println(ex.getMessage());
        }
    }
}
