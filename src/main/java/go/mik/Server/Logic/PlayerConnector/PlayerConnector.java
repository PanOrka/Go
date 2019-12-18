package go.mik.Server.Logic.PlayerConnector;

import go.mik.Server.Logic.Game.GameSystemInterface;

public abstract class PlayerConnector implements Runnable {
    volatile protected PlayerConnector opponent;
    volatile protected String nickName;
    protected char color;
    protected GameSystemInterface gameSystemInterface;

    abstract void setup() throws Exception;
    abstract void takeMsg(String msg);
    abstract void move(String command);
    abstract void sendMsg(String msg);
    abstract void getInput();

    @Override
    abstract public void run();

    protected void pass() {
        if (this != this.gameSystemInterface.getCurrentPlayer()) {
            this.takeMsg("CHAT:Not your turn");
            return;
        }

        this.takeMsg("CHAT:You have passed");
        this.sendMsg("CHAT:Opponent have passed\nYour move");
        this.sendMsg("PASS");
        this.gameSystemInterface.playerPass(this.color);
        this.gameSystemInterface.setCurrentPlayer(this.opponent);
    }

    protected void surr() {
        if (this != this.gameSystemInterface.getCurrentPlayer()) {
            this.takeMsg("CHAT:Not your turn");
            return;
        }

        this.takeMsg("CHAT:You have surrendered");
        this.sendMsg("CHAT:Opponent have surrendered");
        this.gameSystemInterface.playerSurr(this.color);
    }

    public void endGame(int blackPoints, int whitePoints) {
        this.sendMsg("CHAT:Game has ended");
        this.takeMsg("CHAT:Game has ended");

        this.sendMsg("CHAT:Black score: " + blackPoints);
        this.sendMsg("CHAT:White score: " + whitePoints);

        this.takeMsg("CHAT:Black score: " + blackPoints);
        this.takeMsg("CHAT:White score: " + whitePoints);
    }
}
