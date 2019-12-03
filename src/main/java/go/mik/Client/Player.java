package go.mik.Client;

import go.mik.UI.UIWindow;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Client {
    private String nickName;
    private Scanner input;
    private PrintWriter output;
    private UIWindow _gameWindow;
    private _playerColour pickedColour;

    private enum _playerColour {
        BLACK,
        WHITE
    }

    public Player(String nickName, String serverAddress, int socketPort) throws Exception {
        this.nickName = nickName;
        Socket socket = new Socket(serverAddress, socketPort);
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this._gameWindow = new UIWindow(this, nickName);

        //new TestSender(this.nickName, this); // TEST OF SENDING CLIENT -> SERVER -> CLIENT
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
                    if(response.contains("white")){
                        setColor("white");
                    }
                    else if(response.contains("black")) {
                        setColor("black");
                    }
                } else if (response.startsWith("CHAT:")) {
                    response = response.replaceFirst("CHAT:", "");
                    this.sendToChat(response);
                } else if (response.startsWith("GAME:")) {
                    // UI.setStones <= response // thread na ui ktory przekazuje response albo parsuje i przekazuje response // może tak zrobimy
                }
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getPlayerName(){
        return this.nickName;
    }

    @Override
    public void sendToChat(String message) {
        _gameWindow.getMessageForChat(message + "\n");
    }

    @Override
    public void setColor(String color) {
        switch(color) {
            case "black":
                this.pickedColour = _playerColour.BLACK;
                break;
            case "white":
                this.pickedColour = _playerColour.WHITE;
                break;
        }
    }

    @Override
    public void setStones(String gameSet) {

    }

    @Override
    public void sendToOpponentChat(String message) {
        this.output.println(message);
    }

    @Override
    public void move(String position) {

    }

    @Override
    public void quit() {

    }
}
