package go.mik.Bot.Bots;

import go.mik.Bot.Logics.EasyBotLogic;

public class EasyBot extends Bot {
    public EasyBot(String serverAddress, int socketPort) {
        super(serverAddress, socketPort);
        this.setBotLogic(new EasyBotLogic(this));
    }
}
