package go.mik.Server.Logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private int socketPort;

    public Server(int socketPort) {
        this.socketPort = socketPort;
    }

    public void start() {
        try (ServerSocket listener = new ServerSocket(socketPort)) {
            System.out.println("Server is Running");
            while (true) {

            }
        } catch(IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }
}
