package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;

public class BreathChecker extends GameRules {
    private boolean[] isEmpty;
    private int counter;

    public BreathChecker(Game game) {
        super(game);
        this.isEmpty = new boolean[4];
    }

    @Override
    public void check(int x, int y) {
        this.counter = 0;
        this.game.state = Game.States.CAN_PUT;
        this.setFlags();
        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == this.game.opponentColor) {
            this.isEmpty[this.counter] = false;
            this.checkBreath(x+1, y);
            if (!this.isEmpty[this.counter]) {
                this.clearChecked();
                this.game.state = Game.States.FIELD_CHANGE;
            }
            this.counter++;
        }
        this.setFlags();
        if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == this.game.opponentColor) {
            this.isEmpty[this.counter] = false;
            this.checkBreath(x-1, y);
            if (!this.isEmpty[this.counter]) {
                this.clearChecked();
                this.game.state = Game.States.FIELD_CHANGE;
            }
            this.counter++;
        }
        this.setFlags();
        if (y+1 < 19 && this.game.field[x][y+1].isOccupied == this.game.opponentColor) {
            this.isEmpty[this.counter] = false;
            this.checkBreath(x, y+1);
            if (!this.isEmpty[this.counter]) {
                this.clearChecked();
                this.game.state = Game.States.FIELD_CHANGE;
            }
            this.counter++;
        }
        this.setFlags();
        if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == this.game.opponentColor) {
            this.isEmpty[this.counter] = false;
            this.checkBreath(x, y-1);
            if (!this.isEmpty[this.counter]) {
                this.clearChecked();
                this.game.state = Game.States.FIELD_CHANGE;
            }
        }
    }

    private void checkBreath(int x, int y) {
        if (this.isEmpty[this.counter]) {
            return;
        }

        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == 'o') {
            this.isEmpty[this.counter] = true;
            return;
        } else if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == 'o') {
            this.isEmpty[this.counter] = true;
            return;
        } else if (y+1 < 19 && this.game.field[x][y+1].isOccupied == 'o') {
            this.isEmpty[this.counter] = true;
            return;
        } else if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == 'o') {
            this.isEmpty[this.counter] = true;
            return;
        }

        this.game.field[x][y].wasChecked = true;

        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == this.game.opponentColor && !this.game.field[x+1][y].wasChecked) {
            checkBreath(x+1, y);
        }
        if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == this.game.opponentColor && !this.game.field[x-1][y].wasChecked) {
            checkBreath(x-1, y);
        }
        if (y+1 < 19 && this.game.field[x][y+1].isOccupied == this.game.opponentColor && !this.game.field[x][y+1].wasChecked) {
            checkBreath(x, y+1);
        }
        if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == this.game.opponentColor && !this.game.field[x][y-1].wasChecked) {
            checkBreath(x, y-1);
        }
    }

    private void clearChecked() {
        int tempPoints = 0;
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                if (this.game.field[i][j].wasChecked) {
                    this.game.field[i][j].isOccupied = 'o';
                    this.game.field[i][j].wasDeadBefore = true;
                    tempPoints++;
                } else {
                    this.game.field[i][j].wasDeadBefore = false;
                }
            }
        }
        this.game.setPoints(tempPoints);
    }

    private void setFlags() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.game.field[i][j].wasChecked = false;
            }
        }
    }
}
