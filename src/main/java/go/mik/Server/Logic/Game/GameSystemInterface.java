package go.mik.Server.Logic.Game;

import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

public abstract class GameSystemInterface {
    volatile private PlayerConnector currentPlayer;
    volatile private Game game;
    volatile private boolean isAvailable;

    protected GameSystemInterface() {
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
        return game.move(command, color);
    }

    synchronized public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    synchronized public boolean getAvailable() {
        return isAvailable;
    }
}
