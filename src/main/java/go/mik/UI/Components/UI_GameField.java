package go.mik.UI.Components;

import go.mik.Client.Player;
import go.mik.UI.Managers.Stone;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class UI_GameField extends JPanel implements MouseListener {
    private int _verFieldAmount;
    private int _horFieldAmount;
    private int _windowSizeX;
    private int _windowSizeY;
    private int _verLength;
    private int _horLength;
    private int _defaultGap;
    private ArrayList<Rectangle> _rects = new ArrayList<>();
    //private ArrayList<Stone> _stones = new ArrayList<>();

    private Stone[][] _stonesArray;

    private Player _player;

    public UI_GameField(){

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        printGameBoard(g);

    }
    public void printGameBoard(Graphics g) {

        setBackground(Color.orange);
        printHorLines(g,_verLength,_horLength,_defaultGap);
        printVerLines(g,_verLength,_horLength,_defaultGap);
        printStones(g);
    }

    private void printHorLines(Graphics g,int verLength, int horLength, int defaultGap) {
        for(int i = 0; i <= _verFieldAmount; i++) {
            g.drawLine(defaultGap + verLength * i , defaultGap,defaultGap + verLength * i,defaultGap + horLength * _horFieldAmount );

        }
    }
    private void createRectsForPickingField(int defaultGap,int verLength, int horLength){
        for(int i = 0; i <= _verFieldAmount; i++) {
            for (int j = 0; j <= _horFieldAmount; j++) {
                _rects.add(new Rectangle(defaultGap + verLength * i - verLength / 4, defaultGap + horLength * j - horLength / 4, (verLength / 2), (horLength / 2)));
                //g.drawRect(defaultGap + verLength * i - verLength/4,defaultGap + horLength * j - horLength/4,(verLength/2),(horLength/2));
            }
        }
    }

    private void printVerLines(Graphics g,int verLength, int horLength, int defaultGap) {
        for (int i = 0; i <= _horFieldAmount; i++) {
            g.drawLine(defaultGap, defaultGap + horLength * i, defaultGap + verLength * _verFieldAmount, defaultGap + horLength * i);
        }
    }

    public int[] whichFieldWasPicked(int locX, int locY){
        for(Rectangle rect : _rects){
            if(rect.contains(locX,locY)){
                int[] pickedField = new int[4];
                pickedField[0] = (rect.x)/_verLength + 1;
                pickedField[1] = (rect.y)/_horLength + 1;
                pickedField[2] = rect.x;
                pickedField[3] = rect.y;
                return pickedField;
            }
        }
        return null;
    }

    public void printStones(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i <= _verFieldAmount; i++) {
            for (int j = 0; j <= _horFieldAmount; j++) {
                Stone stone = _stonesArray[i][j];
                if (stone != null) {
                    switch (stone.get_stoneColour()) {
                        case "black":
                            g2d.setColor(Color.BLACK);
                            g2d.fillOval(i * _verLength + _defaultGap - stone.get_width() / 2, j * _horLength + _defaultGap - stone.get_height() / 2, stone.get_width(), stone.get_height());
                        case "white":
                            g2d.setColor(Color.WHITE);
                            g2d.fillOval(i * _verLength + _defaultGap - stone.get_width() / 2, j * _horLength + _defaultGap - stone.get_height() / 2, stone.get_width(), stone.get_height());
                        default:
                    }

                }
            }
        }
    }

    public static GameFieldBuilder builder() {
        return new GameFieldBuilder();
    }

    public void addNewStone(String colour, int x, int y){
        _stonesArray[x][y] = (new Stone(x,y,_verLength/2,_horLength/2,colour));
    }
    public void removeStoneFromList(int x, int y){
        _stonesArray[x][y] = null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int[] pickedField = whichFieldWasPicked(mouseEvent.getX(),mouseEvent.getY());

        if(pickedField != null){

			String position = "" + (pickedField[0]-1) + ";" + (pickedField[1]-1);
			_player.move(position);
        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public int getVerFieldAmount(){
        return this._verFieldAmount;
    }

    public int getHorFieldAmount(){
        return this._horFieldAmount;
    }

    public static final class GameFieldBuilder{
        private int _verFieldAmount;
        private int _horFieldAmount;
        private int _windowSizeX;
        private int _windowSizeY;
        private int _verLength;
        private int _horLength;
        private int _defaultGap;
        private Stone[][] _stonesArray;
        private Player _player;

        public GameFieldBuilder verFieldAmount(int verFieldAmount) {
            this._verFieldAmount = verFieldAmount - 1;
            return this;
        }

        public GameFieldBuilder horFieldAmount(int horFieldAmount) {
            this._horFieldAmount = horFieldAmount - 1;
            return this;
        }

        public GameFieldBuilder windowSizeX(int windowSizeX) {
            this._windowSizeX = windowSizeX;
            return this;
        }

        public GameFieldBuilder windowSizeY(int windowSizeY) {
            this._windowSizeY = windowSizeY;
            return this;
        }
        public GameFieldBuilder player(Player player) {
            this._player = player;
            return this;
        }
        public GameFieldBuilder calculatVerLength(){
            this._verLength = (int)Math.floor((this._windowSizeX - 650 + this._verFieldAmount)/_verFieldAmount);
            return this;
        }
        public GameFieldBuilder calculateHorLength(){
            this._horLength = (int)Math.floor((this._windowSizeY - 150 + this._horFieldAmount)/_horFieldAmount);
            return this;
        }
        public GameFieldBuilder defaultGapForGameField(int gap){
            this._defaultGap = gap;
            return this;
        }
        public GameFieldBuilder initializeStoneList(){
            this._stonesArray = new Stone[this._verFieldAmount+1][this._horFieldAmount+1];
            return this;
        }

        public UI_GameField buildGameField() {
            UI_GameField gameField = new UI_GameField();

            gameField._verFieldAmount = this._verFieldAmount;
            gameField._horFieldAmount = this._horFieldAmount;
            gameField._windowSizeX = this._windowSizeX;
            gameField._windowSizeY = this._windowSizeY;
            gameField._verLength = this._verLength;
            gameField._horLength = this._horLength;
            gameField._defaultGap = this._defaultGap;
            gameField._player = this._player;
            gameField._stonesArray = this._stonesArray;
            gameField.createRectsForPickingField(this._defaultGap,this._verLength,this._horLength);
            return gameField;
        }
    }

}
