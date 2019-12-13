package go.mik.Bot;

public class Stone {
    public char isOccupied;
    public boolean wasChecked;
    public boolean wasDeadBefore;
    public Stone() {
        this.isOccupied = 'o';
        this.wasChecked = false;
        this.wasDeadBefore = false;
    }
}
