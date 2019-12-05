package go.mik.Server.Logic.Game;

import go.mik.Server.Logic.PlayerConnector.PlayerConnector;

public abstract class GameSystemInterface {
    volatile private PlayerConnector currentPlayer;
    private Game game;

    protected GameSystemInterface() {
        this.game = new Game();
    }

    public void setCurrentPlayer(PlayerConnector playerConnector) {
        this.currentPlayer = playerConnector;
    }

    public PlayerConnector getCurrentPlayer() {
        return this.currentPlayer;
    }

    synchronized public String move(String command, char color) {
        return game.move(command, color);
    }
}
