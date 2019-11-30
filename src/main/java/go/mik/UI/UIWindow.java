package go.mik.UI;

import go.mik.UI.Components.UI_Chat;
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
	private JButton _surrenderBtn;
	private JPanel _mainPanel = new JPanel(new BorderLayout(2,2));
	private JPanel _chatAndButtonsPanel = new JPanel(new GridBagLayout());
	private JScrollPane _chatPanel;
	private UI_Chat _chatBox;
	private JTextField _inputForChat;
	
	public UIWindow(){
		super();
		takeInputForWindow();
		createWindow();
		createGameField(45,45);
		initializeChat();
		initializeButtons();
		initializeChatAndButtonsPanel();

		getMessageForChat("TESTXDDDDDDDDDDDDDD\n");
		getMessageForChat("TEST2\n");
		getMessageForChat("TEST3\n");
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
	private void initializeChatAndButtonsPanel(){
		GridBagConstraints c = new GridBagConstraints();

		_chatAndButtonsPanel.setBorder(new EmptyBorder(50,100,20,100));
		_chatAndButtonsPanel.setBackground(Color.WHITE);

		_chatPanel = new JScrollPane(_chatBox);
		_chatPanel.setPreferredSize(new Dimension(350,550));

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.weighty = 0;
		_chatAndButtonsPanel.add(_chatPanel,c);
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 0.2;
		c.weightx = 1;
		_chatAndButtonsPanel.add(_inputForChat,c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		c.weighty = 0;
		c.weightx = 1;
		_chatAndButtonsPanel.add(_blackStoneBtn,c);
		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 0.8;
		c.weightx = 0;
		_chatAndButtonsPanel.add(_surrenderBtn,c);

		_mainPanel.add(_chatAndButtonsPanel, BorderLayout.EAST);
	}
	private void initializeButtons(){
		_buttonController = new ButtonController(this);
		_blackStoneBtn = new JButton("PASS");
		_blackStoneBtn.setBackground(Color.white);
		_blackStoneBtn.setBorderPainted(false);
		_blackStoneBtn.setPreferredSize(new Dimension(100,50));

		_surrenderBtn = new JButton();
		_surrenderBtn.setIcon(new ImageIcon(getClass().getResource("/kamien.png")));
		_surrenderBtn.setBackground(Color.white);
		_surrenderBtn.setBorderPainted(false);
		_surrenderBtn.setPreferredSize(new Dimension(150,50));

	}

	private void initializeChat(){
		_chatBox = new UI_Chat();
		_inputForChat = new JTextField();
		_inputForChat.setPreferredSize(new Dimension(350, 50));
	}

	public void getMessageForChat(String message){
		_chatBox.addMessageToChat(message);
	}


	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == this._blackStoneBtn) {
			_buttonController.doCertainAction("Pick field");
		}
		
	}


	
}
