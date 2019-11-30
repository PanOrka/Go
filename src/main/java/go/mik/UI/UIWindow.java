package go.mik.UI;

import go.mik.UI.Components.UI_GameField;
import go.mik.UI.Controllers.ButtonController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class UIWindow extends JFrame implements ActionListener{
	
	private int _pickedSocketID;
	private static UI_GameField _gameField;
	private ButtonController _buttonController;
	private JButton _blackStoneBtn;
	private JPanel _mainPanel = new JPanel(new BorderLayout(2,2));
	private JPanel _chatAndButtonsPanel = new JPanel(new GridLayout(2,1));
	private JScrollPane _chat = new JScrollPane();
	
	public UIWindow(){
		super();
		takeInputForWindow();
		createWindow();
		createGameField(19,19);
		initializeButtons();
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
		setResizable(false);
		setTitle("Go game");
		setSize(1500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(_mainPanel);
		setVisible(true);
	}
	
	private void createGameField(int verFieldAmount, int horFieldAmount) {
		_gameField = UI_GameField.builder()
				.verFieldAmount(verFieldAmount)
				.horFieldAmount(horFieldAmount)
				.windowSizeX(getWidth())
				.windowSizeY(getHeight())
				.buildGameField();

		_gameField.repaint();
		_gameField.setBorder(new EmptyBorder(0,0,0,50));
		_gameField.setBackground(Color.WHITE);

		_mainPanel.add(_gameField, BorderLayout.CENTER);

	}

	private void initializeButtons(){
		_buttonController = new ButtonController(this);
		_blackStoneBtn = new JButton("PASS");
		_blackStoneBtn.setBackground(Color.white);
		_blackStoneBtn.setBorderPainted(false);

		_chat.setBorder(new EmptyBorder(0,0,0,500));

		_chatAndButtonsPanel.setBorder(new EmptyBorder(50,0,20,50));
		_chatAndButtonsPanel.add(_chat);
		_chatAndButtonsPanel.add(_blackStoneBtn);
		_chat.setBackground(Color.BLACK);

		_mainPanel.add(_chatAndButtonsPanel, BorderLayout.EAST);

	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == this._blackStoneBtn) {
			_buttonController.doCertainAction("Pick field");
		}
		
	}
	
}
