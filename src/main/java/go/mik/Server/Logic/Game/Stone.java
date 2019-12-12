package go.mik.Server.Logic.Game;

public class Stone {
    public char isOccupied;
    public boolean wasChecked;
    public boolean wasDeadBefore;
    Stone() {
        this.isOccupied = 'o';
        this.wasChecked = false;
        this.wasDeadBefore = false;
    }
}
