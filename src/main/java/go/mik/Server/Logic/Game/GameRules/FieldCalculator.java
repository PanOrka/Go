package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;

public class FieldCalculator {
    private Game game;
    private boolean[] isDifferentColor;
    private int counter;
    private int tempPoints, points;
    private char diffColor;

    public FieldCalculator(Game game) {
        this.game = game;
        this.isDifferentColor = new boolean[4];
        this.diffColor = 'o';
        this.tempPoints = 0;
        this.points = 0;
    }

    public int calculatePoints(char color) {
        this.setFlags();
        this.points = 0;
        if (color == 'b') {
            this.diffColor = 'w';
        } else {
            this.diffColor = 'b';
        }

        for (int x=0; x<19; x++) {
            for (int y=0; y<19; y++) {
                if (this.game.field[x][y].isOccupied == color) {
                    this.counter = 0;

                    if (x+1 < 19 && this.game.field[x+1][y].isOccupied == 'o' && !this.game.field[x+1][y].wasChecked) {
                        this.tempPoints = 0;
                        this.isDifferentColor[this.counter] = false;
                        this.calculateFields(x+1, y);
                        if (!this.isDifferentColor[this.counter]) {
                            points += tempPoints;
                        }
                        this.counter++;
                    }

                    if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == 'o' && !this.game.field[x-1][y].wasChecked) {
                        this.tempPoints = 0;
                        this.isDifferentColor[this.counter] = false;
                        this.calculateFields(x-1, y);
                        if (!this.isDifferentColor[this.counter]) {
                            points += tempPoints;
                        }
                        this.counter++;
                    }

                    if (y+1 < 19 && this.game.field[x][y+1].isOccupied == 'o' && !this.game.field[x][y+1].wasChecked) {
                        this.tempPoints = 0;
                        this.isDifferentColor[this.counter] = false;
                        this.calculateFields(x, y+1);
                        if (!this.isDifferentColor[this.counter]) {
                            points += tempPoints;
                        }
                        this.counter++;
                    }

                    if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == 'o' && !this.game.field[x][y-1].wasChecked) {
                        this.tempPoints = 0;
                        this.isDifferentColor[this.counter] = false;
                        this.calculateFields(x, y-1);
                        if (!this.isDifferentColor[this.counter]) {
                            points += tempPoints;
                        }
                    }
                }
            }
        }
        return points;
    }

    private void calculateFields(int x, int y) {
        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == this.diffColor) {
            this.isDifferentColor[this.counter] = true;
        } else if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == this.diffColor) {
            this.isDifferentColor[this.counter] = true;
        } else if (y+1 < 19 && this.game.field[x][y+1].isOccupied == this.diffColor) {
            this.isDifferentColor[this.counter] = true;
        } else if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == this.diffColor) {
            this.isDifferentColor[this.counter] = true;
        }

        this.game.field[x][y].wasChecked = true;
        this.tempPoints++;

        if (x+1 < 19 && this.game.field[x+1][y].isOccupied == 'o' && !this.game.field[x+1][y].wasChecked) {
            calculateFields(x+1, y);
        }
        if (x-1 >= 0 && this.game.field[x-1][y].isOccupied == 'o' && !this.game.field[x-1][y].wasChecked) {
            calculateFields(x-1, y);
        }
        if (y+1 < 19 && this.game.field[x][y+1].isOccupied == 'o' && !this.game.field[x][y+1].wasChecked) {
            calculateFields(x, y+1);
        }
        if (y-1 >= 0 && this.game.field[x][y-1].isOccupied == 'o' && !this.game.field[x][y-1].wasChecked) {
            calculateFields(x, y-1);
        }
    }

    private void setFlags() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.game.field[i][j].wasChecked = false;
            }
        }
    }
}
