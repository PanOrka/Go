package go.mik.Bot.Bots;

import go.mik.Bot.Logics.NormalBotLogic;

public class NormalBot extends Bot {
    public NormalBot(String serverAddress, int socketPort) {
        super(serverAddress, socketPort);
        this.setBotLogic(new NormalBotLogic(this));
    }
}
