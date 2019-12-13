package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;

public class SuicideMoveChecker extends GameRules {
    private boolean isEmpty;

    public SuicideMoveChecker(Game game) {
        super(game);
        this.isEmpty = false;
    }

    @Override
    public void check(int x, int y) {
        if (this.game.state == Game.States.FIELD_CHANGE) {
            return;
        } else {
            this.setFlags();
            this.isEmpty = false;
            this.checkOutOfBreath(x, y);
            if (!this.isEmpty) {
                this.game.state = Game.States.NOTHING;
            } else {
                this.setToAlive();
            }
        }
    }

    private void checkOutOfBreath(int x, int y) {
        if (this.isEmpty) {
            return;
        }

        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == 'o') {
            this.isEmpty = true;
            return;
        } else if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == 'o') {
            this.isEmpty = true;
            return;
        } else if (y+1 < 19 && this.game.field[x][y+1].isOccupied == 'o') {
            this.isEmpty = true;
            return;
        } else if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == 'o') {
            this.isEmpty = true;
            return;
        }

        this.game.field[x][y].wasChecked = true;

        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == this.game.actualColor && !this.game.field[x+1][y].wasChecked) {
            checkOutOfBreath(x+1, y);
        }
        if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == this.game.actualColor && !this.game.field[x-1][y].wasChecked) {
            checkOutOfBreath(x-1, y);
        }
        if (y+1 < 19 && this.game.field[x][y+1].isOccupied == this.game.actualColor && !this.game.field[x][y+1].wasChecked) {
            checkOutOfBreath(x, y+1);
        }
        if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == this.game.actualColor && !this.game.field[x][y-1].wasChecked) {
            checkOutOfBreath(x, y-1);
        }
    }

    private void setFlags() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.game.field[i][j].wasChecked = false;
            }
        }
    }

    private void setToAlive() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.game.field[i][j].wasDeadBefore = false;
            }
        }
    }
}
