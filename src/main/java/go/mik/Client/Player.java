package go.mik.Client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Client {
    private String nickName;
    private Scanner input;
    private PrintWriter output;

    public Player(String nickName, String serverAddress, int socketPort) throws Exception {
        this.nickName = nickName;
        Socket socket = new Socket(serverAddress, socketPort);
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);

        // COS TAM KURWA UI
        new TestSender(this.nickName, this); // TEST OF SENDING CLIENT -> SERVER -> CLIENT
    }

    @Override
    public void start() {
        System.out.println("Player is Running");
        try {
            output.println(nickName);

            String response; // WELCOME ...
            while (input.hasNextLine()) {
                response = input.nextLine();
                System.out.println(response);
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    void send(String text) {
        this.output.println(text);
    }
}
