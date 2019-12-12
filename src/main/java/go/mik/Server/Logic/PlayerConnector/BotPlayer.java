package go.mik.Server.Logic.PlayerConnector;

import go.mik.Server.Logic.Game.GameSystemInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BotPlayer extends PlayerConnector {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;

    public BotPlayer(Socket socket, char color, GameSystemInterface gameSystemInterface) {
        this.socket = socket;
        this.nickName = "BOTTO";
        this.color = color;
        this.gameSystemInterface = gameSystemInterface;
    }

    @Override
    public void run() {
        try {
            this.setup();
            this.getInput();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (opponent != null) {
                sendMsg("CHAT:" + this.nickName + " Has left the game (SOMEHOW)");
            }
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    @Override
    void setup() throws Exception {
        this.input = new Scanner(this.socket.getInputStream());
        this.output = new PrintWriter(this.socket.getOutputStream(), true);

        this.opponent = this.gameSystemInterface.getCurrentPlayer();
        this.opponent.opponent = this;
        this.sendMsg("CHAT:" + this.nickName + " Joined the game");

        this.sendMsg("CHAT:Your move");
    }

    @Override
    void takeMsg(String msg) {

    }

    @Override
    void move(String command) {

    }

    @Override
    void sendMsg(String msg) {

    }

    @Override
    void getInput() {

    }
}
