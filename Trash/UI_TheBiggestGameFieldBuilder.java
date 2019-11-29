package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import UIBuilders.UI_GameFieldBuilder;

public class UI_TheBiggestGameFieldBuilder implements UI_GameFieldBuilder{
	
	private int _verFieldAmount,_horFieldAmount,_windowSizeX, _windowSizeY;
	
	public UI_TheBiggestGameFieldBuilder(int verFieldAmount, int horFieldAmount, int windowSizeX, int windowSizeY) {
		_verFieldAmount = verFieldAmount;
		_horFieldAmount = horFieldAmount;
		_windowSizeX = windowSizeX;
		_windowSizeY = windowSizeY;
	}

	@Override
	public int getVerFieldAmount() {
		return _verFieldAmount;
	}

	@Override
	public int getHorFieldAmount() {
		return _horFieldAmount;
	}

	@Override
	public int getWindowSizeX() {
		return _windowSizeX;
	}

	@Override
	public int getWindowSizeY() {
		return _windowSizeY;
	}
	
}
