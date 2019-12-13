package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;

public abstract class GameRules {
    protected Game game;

    protected GameRules(Game game) {
        this.game = game;
    }

    public abstract void check(int x, int y);
}
