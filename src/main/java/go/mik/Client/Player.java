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

            String response;
            while (input.hasNextLine()) {
                response = input.nextLine();
                System.out.println(response);

                if (response.startsWith("SET_COLOR:")) {
                    // UI <= set color
                } else if (response.startsWith("CHAT:")) {
                    // UI.JTextField <= append.response
                } else if (response.startsWith("GAME:")) {
                    // UI.setStones <= response // thread na ui ktory przekazuje response albo parsuje i przekazuje response // może tak zrobimy
                }
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void send(String text) {
        this.output.println(text);
    }
}
