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
	private ArrayList<Rectangle> _rects = new ArrayList<>();
	private ArrayList<Stone> _stones = new ArrayList<>();
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
		int defaultGap = 50;
		_verLength = (int) Math.floor((_windowSizeX - 650 + _verFieldAmount)/_verFieldAmount);
		_horLength = (int) Math.floor((_windowSizeY - 150 + _horFieldAmount)/_horFieldAmount);

		setBackground(Color.orange);
		printHorLines(g,_verLength,_horLength,defaultGap);
		printVerLines(g,_verLength,_horLength,defaultGap);
		printStones(g);
	}
	
	private void printHorLines(Graphics g,int verLength, int horLength, int defaultGap) {
		for(int i = 0; i <= _verFieldAmount; i++) {
			g.drawLine(defaultGap + verLength * i , defaultGap,defaultGap + verLength * i,defaultGap + horLength * _horFieldAmount );
			for(int j = 0; j <= _horFieldAmount; j++){
				_rects.add(new Rectangle(defaultGap + verLength * i - verLength/4,defaultGap + horLength * j - horLength/4,(verLength/2),(horLength/2)));
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

	public void printStones(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(Stone _stone : _stones){
			if(_stone.get_stoneColour().equals("black")) {
				g2d.setColor(Color.BLACK);
				g2d.fillOval((_stone.get_x() - 1) * _verLength + 50 - _stone.get_width()/2,(_stone.get_y() - 1) * _horLength + 50 - _stone.get_height()/2,_stone.get_width(),_stone.get_height());
			}
			else if(_stone.get_stoneColour().equals("white")){
					g2d.setColor(Color.WHITE);
					g2d.fillOval((_stone.get_x() - 1 ) * _verLength + 50 - _stone.get_width()/2 ,(_stone.get_y() - 1) * _horLength  + 50 - _stone.get_height()/2, _stone.get_width(),_stone.get_height());
			}
		}
	}

	public void addStoneToList(String colour, int fieldX, int fieldY){
		_stones.add(new Stone(fieldX,fieldY,_verLength/2,_horLength/2,colour));
		repaint();
	}

	public static GameFieldBuilder builder() {
	    return new GameFieldBuilder();
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		int[] pickedField = whichFieldWasPicked(mouseEvent.getX(),mouseEvent.getY());
		boolean canPrintThere = true;

		if(pickedField != null){
			/*for(Stone _stone : _stones){
				if(_stone.get_x() == pickedField[0] && _stone.get_y() == pickedField[1]){
					canPrintThere = false;
					break;
				}
			}*/

			if(canPrintThere){
				String position = "" + pickedField[0] + ";" + pickedField[1];
				_player.move(position);
			}
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

		public UI_GameField buildGameField() {
			UI_GameField gameField = new UI_GameField();
			
			gameField._verFieldAmount = this._verFieldAmount;
			gameField._horFieldAmount = this._horFieldAmount;
			gameField._windowSizeX = this._windowSizeX;
			gameField._windowSizeY = this._windowSizeY;
			gameField._player = this._player;
			
			return gameField;
		}
	}
	
}
