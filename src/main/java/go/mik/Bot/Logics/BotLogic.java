package go.mik.Bot.Logics;

import go.mik.Bot.Bots.Bot;
import go.mik.Bot.Stone;

public abstract class BotLogic {
    private final Bot bot;
    private final Stone[][] field;

    BotLogic(Bot bot) {
        this.bot = bot;
        this.field = new Stone[19][19];
    }

    private void setStones() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.field[i][j] = new Stone();
            }
        }
    }

    public void setField(char color, int x, int y) {
        this.field[x][y].isOccupied = color;
    }

    public abstract void makeTurn();
}
