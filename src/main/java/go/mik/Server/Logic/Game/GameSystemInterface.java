package go.mik.Server.Logic.Game;

import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

public abstract class GameSystemInterface {
    volatile private PlayerConnector currentPlayer;
    volatile private Game game;
    volatile private boolean isAvailable;
    volatile private boolean blackPassed;
    volatile private boolean whitePassed;

    protected GameSystemInterface() {
        this.blackPassed = false;
        this.whitePassed = false;
        this.isAvailable = true;
        this.game = new Game();
    }

    synchronized public void setCurrentPlayer(PlayerConnector playerConnector) {
        this.currentPlayer = playerConnector;
    }

    synchronized public PlayerConnector getCurrentPlayer() {
        return this.currentPlayer;
    }

    synchronized public String move(String command, char color) {
        this.blackPassed = false;
        this.whitePassed = false;
        return game.move(command, color);
    }

    synchronized public void setAvailable() {
        this.isAvailable = false;
    }

    synchronized public boolean getAvailable() {
        return !isAvailable;
    }

    synchronized public void playerPass(char color) {
        if (color == 'b'){
            this.blackPassed = true;
        } else {
            this.whitePassed = true;
        }

        if (blackPassed && whitePassed) {
            this.isAvailable = false;
            int blackPoints = this.game.getBlackPoints();
            int whitePoints = this.game.getWhitePoints();

            this.currentPlayer.endGame(blackPoints, whitePoints);
        }
    }

    synchronized public void playerSurr(char color) {
        this.isAvailable = false;
        int blackPoints = 0;
        int whitePoints = 0;
        if (color == 'b') {
            whitePoints = this.game.getWhitePoints();
        } else if (color == 'w') {
            blackPoints = this.game.getBlackPoints();
        }
        this.currentPlayer.endGame(blackPoints, whitePoints);
    }
}
