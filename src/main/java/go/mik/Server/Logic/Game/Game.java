package go.mik.Server.Logic.Game;

import go.mik.Server.Logic.Game.GameRules.BreathChecker;
import go.mik.Server.Logic.Game.GameRules.GameRules;
import go.mik.Server.Logic.Game.GameRules.SuicideMoveChecker;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<GameRules> gameRules;

    public enum States {
        NOTHING, CAN_PUT, FIELD_CHANGE
    }

    public final Stone[][] field;
    public char actualColor, opponentColor;
    public States state;

    Game() {
        this.field = new Stone[19][19];
        this.setStones();
        this.setRules();
    }

    String move(String command, char color) {
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
        } else if (this.field[x][y].wasDeadBefore) {
            return "CHAT:This place is dead";
        }

        state = States.NOTHING;
        this.field[x][y].isOccupied = this.actualColor;

        for (GameRules rules : gameRules) {
            rules.check(x, y);
        }

        if (state == States.CAN_PUT) {
            return "GAME:put:" + x + ";" + y + ";" + this.actualColor;
        } else if (state == States.FIELD_CHANGE) {
            return "GAME:" + parseField();
        } else {
            this.field[x][y].isOccupied = 'o';
            return "CHAT:That move is not valid";
        }
    }

    private int parser(String number) {
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    private void setStones() {
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                this.field[i][j] = new Stone();
            }
        }
    }

    private String parseField() {
        String str = "";
        StringBuilder builder = new StringBuilder(str);
        for (int i=0; i<19; i++) {
            for (int j=0; j<19; j++) {
                builder.append(field[j][i].isOccupied); // ZMIENIC i -> j
            }
        }
        str = builder.toString();
        return str;
    }

    private void setRules() {
        gameRules = new ArrayList<>();
        gameRules.add(new BreathChecker(this));
        gameRules.add(new SuicideMoveChecker(this));
    }

    int getBlackPoints() {

        return 15;
    }

    int getWhitePoints() {

        return 20;
    }
}
