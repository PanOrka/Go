package go.mik.Client;

import go.mik.UI.UIWindow;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements ServerConnector {
    private String nickName;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private UIWindow _gameWindow;

    public Player(String nickName, String serverAddress, int socketPort) throws Exception {
        this.nickName = nickName;
        this.socket = new Socket(serverAddress, socketPort);
        this.input = new Scanner(socket.getInputStream());
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this._gameWindow = new UIWindow(this, nickName);
    }

    @Override
    public void start(boolean playWithBot) {
        System.out.println("Player is Running");
        if (playWithBot) {
            output.println("PLAY:BOT:" + nickName);
        } else {
            output.println("PLAY:PVP:" + nickName);
        }

        this.getInput();
    }

    @Override
    public void getInput() {
        String response;
        while (input.hasNextLine()) {
            response = input.nextLine();

            if (response.startsWith("CHAT:")) {
                response = response.replaceFirst("CHAT:", "");
                this.sendToChat(response);
            } else if (response.startsWith("GAME:")) {
                response = response.replaceFirst("GAME:", "");
                this.setStones(response);
                // UI.setStones <= response
            }
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
    public void sendToOpponentChat(String message) {
        this.output.println(message);
    }

    @Override
    //wysylanie z ui do servera info o probie polozenia kloca || Splitter ---> ";"
    public void move(String position) {
        this.output.println("MOVE:" + position);
    }

    @Override
    //odbieranie z serwera mapy z polozeniem klockow
    public void setStones(String gameSet) {
        if(gameSet.startsWith("put:")){
            gameSet = gameSet.replaceFirst("put:", "");
            try{
                String[] str = gameSet.split(";");

                if (str[2].equals("b")){
                    this._gameWindow.getUI_Field().addStoneToList("black", Integer.parseInt(str[0])+1, Integer.parseInt(str[1])+1);
                } else if(str[2].equals("w")){
                    this._gameWindow.getUI_Field().addStoneToList("white", Integer.parseInt(str[0])+1, Integer.parseInt(str[1])+1);
                }
            }catch(NumberFormatException ex){
                System.err.println(ex.getMessage());
            }
        }
        else {
            _gameWindow.getUI_Field().clearStoneList();
            for (int i = 1; i <= _gameWindow.getUI_Field().getVerFieldAmount(); i++) {
                for (int j = 0; j <= _gameWindow.getUI_Field().getVerFieldAmount(); j++) {

                    if (gameSet.charAt(j + (_gameWindow.getUI_Field().getVerFieldAmount() + 1) * (i - 1)) == 'b') {

                        this._gameWindow.getUI_Field().addStoneToList("black", j + 1, i);

                    } else if (gameSet.charAt(j + (_gameWindow.getUI_Field().getVerFieldAmount() + 1) * (i - 1)) == 'w') {

                        this._gameWindow.getUI_Field().addStoneToList("white", j + 1, i);
                    }
                }
            }
        }
    }
}
