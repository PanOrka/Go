package go.mik.Bot.Logics;

import go.mik.Bot.Bots.Bot;

public class NormalBotLogic extends BotLogic {
    public NormalBotLogic(Bot bot) {
        super(bot);
    }

    @Override
    public void makeTurn() {
        for (int i=0; i<19; i++) {
            for (int k=0; k<19; k++) {
                if (this.getFieldColor(i, k) == 'o') {
                    this.botMove(i + ";" + k);
                }
            }
        }
    }
}
