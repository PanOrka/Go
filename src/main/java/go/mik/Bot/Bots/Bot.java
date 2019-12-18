package go.mik.Bot.Bots;

import go.mik.Bot.Logics.BotLogic;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public abstract class Bot implements ServerConnector, Runnable {
    private String serverAddress;
    private int socketPort;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private BotLogic logic;

    protected Bot(String serverAddress, int socketPort) {
        this.serverAddress = serverAddress;
        this.socketPort = socketPort;

    }

    protected void setBotLogic(BotLogic logic) {
        this.logic = logic;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            this.socket = new Socket(serverAddress, socketPort);
            this.input = new Scanner(socket.getInputStream());
            this.output = new PrintWriter(socket.getOutputStream(), true);

            this.getInput();
        } catch(Exception ex) {
            System.out.println("Bot thread problem");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void getInput() {
        String response;
        while (input.hasNextLine()) {
            response = input.nextLine();

            if (response.startsWith("GAME:")) {
                response = response.replaceFirst("GAME:", "");
                this.setStones(response);
            } else if (response.equals("TURN")) {
                this.logic.makeTurn();
            } else if (response.equals("PASS")) {
                this.sendToServer("PASS");
            } else if (response.equals("QUIT")) {
                this.sendToServer("QUIT");
                break;
            }
        }
    }

    @Override
    public void sendToServer(String msg) {
        this.output.println(msg);
    }

    @Override
    public void move(String position) {
        this.sendToServer("MOVE:" + position);
    }

    @Override
    public void setStones(String gameSet) {
        if (gameSet.startsWith("put:")) {
            gameSet = gameSet.replaceFirst("put:", "");
            try {
                String[] str = gameSet.split(";");

                if (str[2].equals("b")) {
                    this.logic.setField('b', Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                } else if (str[2].equals("w")) {
                    this.logic.setField('w', Integer.parseInt(str[0]), Integer.parseInt(str[1]));
                }
            } catch(NumberFormatException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            char color;
            for (int i=0; i<19; i++) {
                for (int k=0; k<19; k++) {
                    color = gameSet.charAt(i*19 + k);
                    if (this.logic.getFieldColor(k, i) == 'w' && color == 'o') {
                        this.logic.setDead(k, i);
                    }
                    this.logic.setField(color, k, i);
                }
            }
        }
    }
}
