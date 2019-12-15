package go.mik.Server.Logic.PlayerConnector;

import go.mik.Server.Logic.Game.GameSystemInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player extends PlayerConnector {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public Player(Socket socket, String nickName, char color, GameSystemInterface gameSystemInterface) {
        this.socket = socket;
        this.nickName = nickName;
        this.color = color;
        this.gameSystemInterface = gameSystemInterface;
    }

    @Override
    public void run() {
        try {
            this.setup();
            this.getInput();
        } catch(Exception ex) {
            System.out.println("Player problem");
            System.err.println(ex.getMessage());
        } finally {
            if (this.opponent != null) {
                this.sendMsg("CHAT:" + this.nickName + " Has left the game");
                this.sendMsg("QUIT");
            }
            this.gameSystemInterface.setAvailable();
            try {
                this.socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    void setup() throws Exception { // setup
        this.input = new Scanner(this.socket.getInputStream());
        this.output = new PrintWriter(this.socket.getOutputStream(), true);

        this.takeMsg("CHAT:WELCOME " + this.nickName);
        if (color == 'b') {
            this.gameSystemInterface.setCurrentPlayer(this);
            this.takeMsg("CHAT:You are playing as black");
            this.takeMsg("CHAT:Waiting for opponent to connect...");

        } else {
            this.opponent = this.gameSystemInterface.getCurrentPlayer();
            this.opponent.opponent = this;
            this.sendMsg("CHAT:" + this.nickName + " Joined the game");

            this.takeMsg("CHAT:You are playing as white");
            this.takeMsg("CHAT:You are playing against " + this.opponent.nickName);

            this.sendMsg("CHAT:Your move");
        }
    }

    @Override
    void takeMsg(String msg) {
        this.output.println(msg);
    }

    @Override
    void sendMsg(String msg) {
        if (opponent != null) {
            this.opponent.takeMsg(msg);
        }
    }

    @Override
    void getInput() { // pobieranie komend od UI / Clienta
        String command;
        while (input.hasNextLine()) { // to blokuje i oczekuje na input z socketa
            command = input.nextLine();

            if (command.startsWith("MOVE:")) {
                command = command.replaceFirst("MOVE:", "");
                this.move(command);

            } else if (command.startsWith("CHAT:")) {
                command = command.replaceFirst("CHAT:", "");
                this.sendMsg("CHAT:" + nickName + ": " + command);

            } else if (command.equals("PASS")) {
                this.pass();
            }
        }
    }

    @Override
    void move(String command) {
         if (this.gameSystemInterface.getAvailable()) {
            this.takeMsg("CHAT:Game has ended");
            return;
        } if (this != this.gameSystemInterface.getCurrentPlayer()) {
            this.takeMsg("CHAT:Not your turn");
            return;
        } else if (this.opponent == null) {
            this.takeMsg("CHAT:Wait for your opponent");
            return;
        }

        String response = this.gameSystemInterface.move(command, this.color);

        if (response.startsWith("GAME:")) {
            this.takeMsg(response);
            this.sendMsg(response);
            this.sendMsg("TURN");
            this.gameSystemInterface.setCurrentPlayer(this.opponent);
        } else if (response.startsWith("CHAT:")) {
            this.takeMsg(response);
        }
    }
}