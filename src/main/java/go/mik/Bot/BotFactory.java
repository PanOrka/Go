package go.mik.Bot;

import go.mik.Bot.Bots.EasyBot;
import go.mik.Bot.Bots.NormalBot;

public class BotFactory {
    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public static void setBot(String serverAddress, int socketPort, Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) {
            new Thread(new EasyBot(serverAddress, socketPort)).start();
        } else if (difficulty == Difficulty.NORMAL) {
            new Thread(new NormalBot(serverAddress, socketPort)).start();
        } else if (difficulty == Difficulty.HARD) {

        }
    }
}
