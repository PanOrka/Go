package go.mik.Server.Logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GameAdapter extends Game {
    volatile private Player currentPlayer;

    public class Player implements Runnable {
        private char color;
        private Socket socket;

        Scanner input;
        PrintWriter output;
        Player opponent;
        String nickName;

        public Player(Socket socket, char color) {
            this.socket = socket;
            this.color = color;
        }

        @Override
        public void run() {
            try {
                this.setup();
                this.takeCommands();
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
            } finally {
                if (opponent != null && opponent.output != null) {
                    opponent.output.println("CHAT:" + nickName + " Has left the game");
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        private void setup() throws Exception { // setup
            this.input = new Scanner(this.socket.getInputStream());
            this.output = new PrintWriter(this.socket.getOutputStream(), true);

            this.nickName = this.input.nextLine();

            this.output.println("CHAT:WELCOME " + this.nickName);
            if (color == 'B') {
                currentPlayer = this;
                this.output.println("CHAT:You are playing as black");
                this.output.println("SET_COLOR:black");
                this.output.println("CHAT:Waiting for opponent to connect...");
            } else {
                this.opponent = currentPlayer;
                this.opponent.opponent = this;
                this.opponent.output.println("CHAT:" + this.nickName + " Joined the game");
                this.output.println("CHAT:You are playing as white");
                this.output.println("SET_COLOR:white");
                this.output.println("CHAT:You are playing against " + this.opponent.nickName);
                this.opponent.output.println("CHAT:Your move");
            }
        }

        private void takeCommands() { // pobieranie komend od UI / Clienta
            String command;
            while (input.hasNextLine()) { // to blokuje i oczekuje na input z socketa
                command = input.nextLine();

                if (command.equals("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE:")) {
                    command = command.replaceFirst("MOVE:", "");
                    this.prepareMove(command);
                } else if (command.startsWith("CHAT:")) {
                    command = command.replaceFirst("CHAT:", "");
                    this.opponent.output.println("CHAT:" + nickName + ": " + command);
                }
            }
        }

        private void prepareMove(String command) { // Tutaj funkcja ktora odpowiada za game -> server -> client
            if (this != currentPlayer) {
                this.output.println("CHAT:Not your turn");
                return;
            } else if (this.opponent == null) {
                this.output.println("CHAT:Wait for your opponent");
                return;
            }

            String response = move(command); /*   TUTAJ prawdopodobnie bedziemy przekazywac stringa z pozycjami, pojedyncze lub update np: dla planszy 4x4 (spacje dla czytelnosci)
                                             //   { o x x o ;
                                             //     y y x o ;
                                             //     x o o x ;
                                             //     y y y o }
                                             */
            if (response.startsWith("GAME:")) {
                this.output.println(response);
                this.opponent.output.println(response);
                currentPlayer = this.opponent;
            } else if (response.startsWith("CHAT:")) {
                this.output.println(response);
            }
        }
    }
}
