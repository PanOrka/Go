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
                    response = response.replaceFirst("SET_COLOR:", "");
                    this.setColor(response);

                } else if (response.startsWith("CHAT:")) {
                    response = response.replaceFirst("CHAT:", "");
                    if (!response.equals("")) {
                        this.sendToChat(response);
                    }

                } else if (response.startsWith("GAME:")) {
                    response = response.replaceFirst("GAME:", "");
                    this.setStones(response);
                }
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendToOpponentChat(String message) { // TO NA SERVER z UI
        // TUTAJ musi być
        // CHAT:
        this.output.println("CHAT:" + message);
    }

    @Override
    public void sendToChat(String message) { // TO NA UI
        // SEND TO CHAT UI
        // UI.JTextField <= append.message

        System.out.println(message); // TEST na konsoli
    }

    @Override
    public void setColor(String color) { // TO NA UI
        // SET IN UI
        // UI <= set color

        System.out.println(color); // TEST na konsoli
    }

    @Override
    public void setStones(String gameSet) { // TO NA UI
        // SET IN UI.PANEL
        // UI.setStones <= response // thread na ui ktory przekazuje response albo parsuje i przekazuje response // może tak zrobimy

        System.out.println(gameSet);
    }

    @Override
    public void move(String position) { // TO NA SERVER z UI
        // TUTAJ musi byc
        // MOVE:
        this.output.println("MOVE:" + position);
    }

    @Override
    public void quit() { // TO NA SERVER z UI
        this.output.println("QUIT");
    }
}
