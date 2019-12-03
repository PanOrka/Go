package go.mik.Server.Logic;

abstract class Game {
    private final Stone[][] field;
    private char actualColor, opponentColor;
    private boolean[] isEmpty;
    private int counter;
    private boolean canPut;

    Game() {
        this.field = new Stone[19][19];
        this.setStones();
    }

    synchronized String move(String command, char color) {
        if (color == 'b') {
            this.actualColor = 'b';
            this.opponentColor = 'w';
        } else {
            this.actualColor = 'w';
            this.opponentColor = 'b';
        }
        String[] str = command.split(";");
        int x = this.parser(str[0]);
        int y = this.parser(str[1]);

        if (this.field[x][y].isOccupied != 'o') {
            return "CHAT:This is occupied";
        }

        this.counter = 0;
        this.canPut = true;
        this.setFlags();
        if (x+1 < 19 && this.field[x+1][y].isOccupied == opponentColor) {
            this.isEmpty[counter] = false;
            this.checkBreath(x+1, y);
            if (!this.isEmpty[counter]) {
                this.clearChecked();
                this.canPut = false;
            }
            counter++;
        }
        this.setFlags();
        if (x-1 >= 0 && this.field[x+1][y].isOccupied == opponentColor) {
            this.isEmpty[counter] = false;
            this.checkBreath(x-1, y);
            if (!this.isEmpty[counter]) {
                this.clearChecked();
                this.canPut = false;
            }
            counter++;
        }
        this.setFlags();
        if (y+1 < 19 && this.field[x+1][y].isOccupied == opponentColor) {
            this.isEmpty[counter] = false;
            this.checkBreath(x, y+1);
            if (!this.isEmpty[counter]) {
                this.clearChecked();
                this.canPut = false;
            }
            counter++;
        }
        this.setFlags();
        if (y-1 >= 0 && this.field[x+1][y].isOccupied == opponentColor) {
            this.isEmpty[counter] = false;
            this.checkBreath(x, y-1);
            if (!this.isEmpty[counter]) {
                this.clearChecked();
                this.canPut = false;
            }
        }

        if (this.canPut) {
            return "GAME:put";
        }

        return "GAME:" + parseField();
    }

    private int parser(String number) {
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    private void checkBreath(int x, int y) {
        if (x+1 < 19 && this.field[x+1][y].isOccupied == 'o') {
            this.isEmpty[counter] = true;
            return;
        } else if (x-1 >= 0 && this.field[x-1][y].isOccupied == 'o') {
            this.isEmpty[counter] = true;
            return;
        } else if (y+1 < 19 && this.field[x][y+1].isOccupied == 'o') {
            this.isEmpty[counter] = true;
            return;
        } else if (y-1 >= 0 && this.field[x][y-1].isOccupied == 'o') {
            this.isEmpty[counter] = true;
            return;
        }

        this.field[x][y].wasChecked = true;

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

    private void clearChecked() {
        for (int i=0; i<19; i++) {
            for (Stone stones : this.field[i]) {
                stones.isOccupied = 'o';
            }
        }
    }

    private void setFlags() {
        for (int i=0; i<19; i++) {
            for (Stone stones : this.field[i]) {
                stones.wasChecked = false;
            }
        }
    }

    private void setStones() {
        for (int i=0; i<19; i++) {
            for (Stone stones : this.field[i]) {
                stones = new Stone();
            }
        }
    }

    private String parseField() {
        String str = "";
        StringBuilder builder = new StringBuilder(str);
        for (int i=0; i<19; i++) {
            for (Stone stones : this.field[i]) {
                builder.append(stones.isOccupied);
            }
        }
        str = builder.toString();
        return str;
    }
}
