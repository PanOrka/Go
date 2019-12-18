package go.mik.Bot.Logics;

import go.mik.Bot.Bots.Bot;

public class EasyBotLogic extends BotLogic {
    public EasyBotLogic(Bot bot) {
        super(bot);
    }

    @Override
    public void makeTurn() {
        for (int i=0; i<19; i++) {
            for (int k=0; k<19; k++) {
                if (this.field[i][k].isOccupied == 'o') {
                    this.botMove(i + ";" + k);
                }
            }
        }
    }
}
