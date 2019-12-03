package go.mik.Server.Logic;

abstract class Game {
    private final char[][] field;

    Game() {
        this.field = new char[19][19];
        for (int i=0; i<19; i++) {
            for (char chars : this.field[i]) {
                chars = 'o';
            }
        }
    }

    synchronized String move(String command, char color) {
        String[] str = command.split(";");
        int x = this.parser(str[0]);
        int y = this.parser(str[1]);

        if (this.field[x][y] == 'b' || this.field[x][y] == 'w') {
            return "CHAT:This is occupied";
        }

        return "nothing now";
    }

    private int parser(String number) {
        try {
            return Integer.parseInt(number);
        } catch(NumberFormatException ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
