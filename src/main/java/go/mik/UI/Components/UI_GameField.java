package go.mik.UI.Components;

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
	private boolean[][] _occupiedFields;
	private ArrayList<Rectangle> _rects = new ArrayList<>();


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
		printEdges(g,_verLength,_horLength,defaultGap);
		printHorLines(g,_verLength,_horLength,defaultGap);
		printVerLines(g,_verLength,_horLength,defaultGap);

	}
	
	private void printEdges(Graphics g,int verLength,int horLength, int defaultGap) {

		
		g.drawLine(defaultGap,defaultGap, defaultGap + verLength * _verFieldAmount,defaultGap);
		g.drawLine(defaultGap + verLength * _verFieldAmount, defaultGap,defaultGap + verLength * _verFieldAmount, defaultGap + horLength * _horFieldAmount);
		g.drawLine(defaultGap, defaultGap,defaultGap, defaultGap + horLength * _horFieldAmount);
		g.drawLine(defaultGap ,defaultGap + horLength * _horFieldAmount, defaultGap + verLength * _verFieldAmount, defaultGap + horLength * _horFieldAmount);
		
	}
	
	private void printHorLines(Graphics g,int verLength, int horLength, int defaultGap) {
		for(int i = 0; i <= _verFieldAmount; i++) {
			g.drawLine(defaultGap + verLength * i , defaultGap,defaultGap + verLength * i,defaultGap + horLength * _horFieldAmount);
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
				int[] pickedField = new int[2];
				pickedField[0] = (rect.x)/_verLength + 1;
				pickedField[1] = (rect.y)/_horLength + 1;
				System.out.println(pickedField[0] + "    " + pickedField[1]);
				return pickedField;
			}
		}
		return null;
	}
	
	public static GameFieldBuilder builder() {
	    return new GameFieldBuilder();
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {

	}

	@Override
	public void mousePressed(MouseEvent mouseEvent) {
		whichFieldWasPicked(mouseEvent.getX(),mouseEvent.getY());
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

	public static final class GameFieldBuilder{
		private int _verFieldAmount;
		private int _horFieldAmount;
		private int _windowSizeX;
		private int _windowSizeY;
		private boolean[][] _occupiedFields;
		
		public GameFieldBuilder verFieldAmount(int verFieldAmount) {
			this._verFieldAmount = verFieldAmount;
			return this;
		}
		
		public GameFieldBuilder horFieldAmount(int horFieldAmount) {
			this._horFieldAmount = horFieldAmount;
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

		public UI_GameField buildGameField() {
			UI_GameField gameField = new UI_GameField();
			
			gameField._verFieldAmount = this._verFieldAmount;
			gameField._horFieldAmount = this._horFieldAmount;
			gameField._windowSizeX = this._windowSizeX;
			gameField._windowSizeY = this._windowSizeY;
			gameField._occupiedFields = new boolean[_verFieldAmount][_horFieldAmount];
			
			return gameField;
		}
	}
	
}
