package go.mik.Bot;

public class Stone {
    public char isOccupied;
    public boolean wasChecked;
    public boolean wasDeadBefore;
    public int weight;
    public Stone() {
        this.isOccupied = 'o';
        this.wasChecked = false;
        this.wasDeadBefore = false;
        this.weight = 0;
    }
}
