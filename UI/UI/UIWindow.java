package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import UIComponents.UI_GameField;

public class UIWindow extends JFrame implements ActionListener{
	
	private int _pickedSocketID;
	private static UI_GameField _gameField;
	
	UIWindow(){
		super();
		takeInputForWindow();
		createWindow();
		createGameField(100,100);
	}
	
	private void takeInputForWindow() {
		while(true) {
			try {
				_pickedSocketID = Integer.parseInt((JOptionPane.showInputDialog(this, "Do jakiego socketu chcesz sie polaczyc?")));
				break;
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,"Podales bledny format socketu");
			}
			
		}
	}
	
	private void createWindow() {
		//setResizable(false);
		setTitle("Go game");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void createGameField(int verFieldAmount, int horFieldAmount) {
		_gameField = UI_GameField.builder()
				.verFieldAmount(verFieldAmount)
				.horFieldAmount(horFieldAmount)
				.windowSizeX(getWidth())
				.windowSizeY(getHeight())
				.buildGameField();
		
		add(_gameField);
		_gameField.setVisible(true);
		_gameField.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == null) {
			
		}
		
	}
	
}
