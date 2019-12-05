package go.mik.Server.Logic.PlayerConnector;

import go.mik.Server.Logic.Game.GameSystemInterface;

public abstract class PlayerConnector implements Runnable {
    protected PlayerConnector opponent;
    protected String nickName;
    protected char color;
    protected GameSystemInterface gameSystemInterface;

    abstract void setup() throws Exception;
    abstract void takeMsg(String msg);
    abstract void move(String command);
    abstract void sendMsg(String msg);
    abstract void getInput();

    @Override
    abstract public void run();
}
