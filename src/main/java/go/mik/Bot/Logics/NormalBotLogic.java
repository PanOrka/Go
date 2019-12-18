package go.mik.Bot.Logics;

import go.mik.Bot.Bots.Bot;

public class NormalBotLogic extends BotLogic {
    private final int killWeight = 1;
    private final int preventKillWeight = 1;

    private int killPoints; // PUNKTY ZA KILL
    private boolean[] isEmptyKill;
    private boolean isEmptySuicide;
    private int killCounter, killTempPoints;
    private int killPreventPoints; // PUNKTY ZA PREVENT

    public NormalBotLogic(Bot bot) {
        super(bot);
        this.isEmptyKill = new boolean[4];
    }

    @Override
    public void makeTurn() {
        for (int i=0; i<19; i++) {
            for (int k=0; k<19; k++) {
                this.setPoints();
                if (this.field[i][k].isOccupied != 'o') {
                    this.field[i][k].weight = -1;
                    continue;
                }

                if (this.field[i][k].wasDeadBefore) {
                    this.field[i][k].weight = -1;
                    continue;
                }
                this.field[i][k].isOccupied = this.actualColor;
                this.calculateKill(i, k);
                this.field[i][k].isOccupied = 'o';

                if (this.killPoints == 0) {
                    this.setFlags();
                    this.isEmptySuicide = false;
                    this.field[i][k].isOccupied = this.actualColor;
                    this.checkOutOfBreath(i, k);
                    this.field[i][k].isOccupied = 'o';
                    if (!this.isEmptySuicide) {
                        this.field[i][k].weight = -1;
                        continue;
                    }
                }

                this.field[i][k].isOccupied = this.opponentColor;
                this.calculatePreventKill(i, k);
                this.field[i][k].isOccupied = 'o';

                this.addWeights(i, k);
            }
        }

        int bestX = 10;
        int bestY = 10;

        for (int i=0; i<19; i++) {
            for (int k=0; k<19; k++) {
                if (this.field[i][k].weight > this.field[bestX][bestY].weight) {
                    bestX = i;
                    bestY = k;
                }
            }
        }

        this.setToAlive();
        System.out.println("MY MOVE: " + bestX + ";" + bestY);
        System.out.println("MOJA WAGA: " + this.field[bestX][bestY].weight);
        this.botMove(bestX + ";" + bestY);
    }



    ////////////////////////////////////// KILL PREVENT ////////////////////////////////////////////////////////////////

    private void calculatePreventKill(int x, int y) {
        this.killCounter = 0;

        if (x+1 < 19 && this.field[x+1][y].isOccupied == this.actualColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkPreventKill(x+1, y);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPreventPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (x-1 >= 0 && this.field[x-1][y].isOccupied == this.actualColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkPreventKill(x-1, y);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPreventPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (y+1 < 19 && this.field[x][y+1].isOccupied == this.actualColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkPreventKill(x, y+1);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPreventPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (y-1 >= 0 && this.field[x][y-1].isOccupied == this.actualColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkPreventKill(x, y-1);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPreventPoints += this.killTempPoints;
            }
        }
    }

    private void checkPreventKill(int x, int y) {
        if (this.isEmptyKill[this.killCounter]) {
            return;
        }

        if (x+1 < 19 && this.field[x+1][y].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (x-1 >= 0 && this.field[x-1][y].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (y+1 < 19 && this.field[x][y+1].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (y-1 >= 0 && this.field[x][y-1].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        }

        this.field[x][y].wasChecked = true;
        this.killTempPoints += this.preventKillWeight; ///////////////////////////////////////////////////////////////// WAGA PREWENCJI ZABICIA JEDNEGO (NASZEGO) PIONA

        if (x+1 < 19 && this.field[x+1][y].isOccupied == this.actualColor && !this.field[x+1][y].wasChecked) {
            checkPreventKill(x+1, y);
        }
        if (x-1 >= 0 && this.field[x-1][y].isOccupied == this.actualColor && !this.field[x-1][y].wasChecked) {
            checkPreventKill(x-1, y);
        }
        if (y+1 < 19 && this.field[x][y+1].isOccupied == this.actualColor && !this.field[x][y+1].wasChecked) {
            checkPreventKill(x, y+1);
        }
        if (y-1 >= 0 && this.field[x][y-1].isOccupied == this.actualColor && !this.field[x][y-1].wasChecked) {
            checkPreventKill(x, y-1);
        }
    }

    ////////////////////////////////////// END KILL PREVENT ////////////////////////////////////////////////////////////



    ////////////////////////////////////// KILL ////////////////////////////////////////////////////////////////////////

    private void calculateKill(int x, int y) {
        this.killCounter = 0;

        if (x+1 < 19 && this.field[x+1][y].isOccupied == this.opponentColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkBreath(x+1, y);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (x-1 >= 0 && this.field[x-1][y].isOccupied == this.opponentColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkBreath(x-1, y);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (y+1 < 19 && this.field[x][y+1].isOccupied == this.opponentColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkBreath(x, y+1);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPoints += this.killTempPoints;
            }
            this.killCounter++;
        }

        if (y-1 >= 0 && this.field[x][y-1].isOccupied == this.opponentColor) {
            this.setFlags();
            this.killTempPoints = 0;
            this.isEmptyKill[this.killCounter] = false;
            this.checkBreath(x, y-1);
            if (!this.isEmptyKill[this.killCounter]) {
                this.killPoints += this.killTempPoints;
            }
        }
    }

    private void checkBreath(int x, int y) {
        if (this.isEmptyKill[this.killCounter]) {
            return;
        }

        if (x+1 < 19 && this.field[x+1][y].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (x-1 >= 0 && this.field[x-1][y].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (y+1 < 19 && this.field[x][y+1].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        } else if (y-1 >= 0 && this.field[x][y-1].isOccupied == 'o') {
            this.isEmptyKill[this.killCounter] = true;
            return;
        }

        this.field[x][y].wasChecked = true;
        this.killTempPoints += this.killWeight; //////////////////////////////////////////////////////////////////////// WAGA ZABICIA JEDNEGO PIONA

        if (x+1 < 19 && this.field[x+1][y].isOccupied == this.opponentColor && !this.field[x+1][y].wasChecked) {
            checkBreath(x+1, y);
        }
        if (x-1 >= 0 && this.field[x-1][y].isOccupied == this.opponentColor && !this.field[x-1][y].wasChecked) {
            checkBreath(x-1, y);
        }
        if (y+1 < 19 && this.field[x][y+1].isOccupied == this.opponentColor && !this.field[x][y+1].wasChecked) {
            checkBreath(x, y+1);
        }
        if (y-1 >= 0 && this.field[x][y-1].isOccupied == this.opponentColor && !this.field[x][y-1].wasChecked) {
            checkBreath(x, y-1);
        }
    }

    ////////////////////////////////////// END KILL ////////////////////////////////////////////////////////////////////



    ////////////////////////////////////// SUICIDE /////////////////////////////////////////////////////////////////////

    private void checkOutOfBreath(int x, int y) {
        if (this.isEmptySuicide) {
            return;
        }

        if (x+1 < 19 && this.field[x+1][y].isOccupied == 'o') {
            this.isEmptySuicide = true;
            return;
        } else if (x-1 >= 0 && this.field[x-1][y].isOccupied == 'o') {
            this.isEmptySuicide = true;
            return;
        } else if (y+1 < 19 && this.field[x][y+1].isOccupied == 'o') {
            this.isEmptySuicide = true;
            return;
        } else if (y-1 >= 0 && this.field[x][y-1].isOccupied == 'o') {
            this.isEmptySuicide = true;
            return;
        }

        this.field[x][y].wasChecked = true;

        if (x+1 < 19 && this.field[x+1][y].isOccupied == this.actualColor && !this.field[x+1][y].wasChecked) {
            checkOutOfBreath(x+1, y);
        }
        if (x-1 >= 0 && this.field[x-1][y].isOccupied == this.actualColor && !this.field[x-1][y].wasChecked) {
            checkOutOfBreath(x-1, y);
        }
        if (y+1 < 19 && this.field[x][y+1].isOccupied == this.actualColor && !this.field[x][y+1].wasChecked) {
            checkOutOfBreath(x, y+1);
        }
        if (y-1 >= 0 && this.field[x][y-1].isOccupied == this.actualColor && !this.field[x][y-1].wasChecked) {
            checkOutOfBreath(x, y-1);
        }
    }

    ////////////////////////////////////// END SUICIDE /////////////////////////////////////////////////////////////////

    private void setFlags() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.field[i][j].wasChecked = false;
            }
        }
    }

    private void setToAlive() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.field[i][j].wasDeadBefore = false;
            }
        }
    }

    private void setPoints() {
        this.killPoints = 0;
        this.killPreventPoints = 0;
    }

    private void addWeights(int x, int y) {
        this.field[x][y].weight += this.killPoints;
        this.field[x][y].weight += this.killPreventPoints;
    }
}
