package UIComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI_GameField extends JPanel{
	private int _verFieldAmount;
	private int _horFieldAmount;
	private int _windowSizeX;
	private int _windowSizeY;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		printGameBoard(g);
	}
	public void printGameBoard(Graphics g) {
		int defaultGap = 50;
		int verLength = (int) Math.floor((_windowSizeX - 100)/_verFieldAmount);
		int horLength = (int) Math.floor((_windowSizeY - 100)/_horFieldAmount);
		
		printEdges(g,verLength,horLength,defaultGap);
		printHorLines(g,verLength,horLength,defaultGap);
		printVerLines(g,verLength,horLength,defaultGap);
	}
	
	private void printEdges(Graphics g,int verLength,int horLength, int defaultGap) {

		
		g.drawLine(defaultGap,defaultGap, defaultGap + verLength * _verFieldAmount,defaultGap);
		g.drawLine(defaultGap + verLength * _verFieldAmount, defaultGap,defaultGap + verLength * _verFieldAmount, defaultGap + horLength * _horFieldAmount);
		g.drawLine(defaultGap, defaultGap,defaultGap, defaultGap + horLength * _horFieldAmount);
		g.drawLine(defaultGap ,defaultGap + horLength * _horFieldAmount, defaultGap + verLength * _verFieldAmount, defaultGap + horLength * _horFieldAmount);
		
	}
	
	private void printHorLines(Graphics g,int verLength, int horLength, int defaultGap) {
		for(int i = 1; i <= _verFieldAmount; i++) {
			g.drawLine(defaultGap + verLength * i , defaultGap,defaultGap + verLength * i,defaultGap + horLength * _horFieldAmount);
		}
	}
	
	private void printVerLines(Graphics g,int verLength, int horLength, int defaultGap) {
		for(int i = 1; i <= _horFieldAmount; i++) {
			g.drawLine(defaultGap, defaultGap + horLength * i,defaultGap + verLength * _verFieldAmount,defaultGap + horLength * i);
		}
	}
	
	public static GameFieldBuilder builder() {
	    return new GameFieldBuilder();
	}
	
	public static final class GameFieldBuilder{
		private int _verFieldAmount;
		private int _horFieldAmount;
		private int _windowSizeX;
		private int _windowSizeY;
		
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
			
			return gameField;
		}
	}
	
}
