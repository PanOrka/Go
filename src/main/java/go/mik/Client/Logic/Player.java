package go.mik.Client.Logic;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Client {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public Player(String nickName, String serverAddress, int socketPort) throws Exception {
        this.socket = new Socket(serverAddress, socketPort);
        this.in = new Scanner(socket.getInputStream());
        this.out = new PrintWriter(socket.getOutputStream(), true);

        // COS TAM KURWA UI
    }

    @Override
    public void start() {
        System.out.println("Player is Running");
        while(true) {
            System.out.println("SHIT");
        }
    }
}
