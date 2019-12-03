package go.mik.UI;

import go.mik.Client.Player;
import go.mik.UI.Components.UI_Chat;
import go.mik.UI.Components.UI_GameField;
import go.mik.UI.Controllers.ButtonController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

// ZABAWA
public class UIWindow extends JFrame implements ActionListener{
	
	private int _pickedSocketID;
	private UI_GameField _gameField;
	private ButtonController _buttonController;
	private JButton _blackStoneBtn;
	private JButton _surrenderBtn;
	private JPanel _mainPanel = new JPanel(new BorderLayout(2,2));
	private JPanel _chatAndButtonsPanel = new JPanel(new GridBagLayout());
	private JScrollPane _chatPanel;
	private UI_Chat _chatBox;
	private JTextField _inputForChat;
	private Player _player;
	private final String _nickName;

	public UIWindow(Player player, String nickName){
		super("Go " + nickName);
		this._nickName = nickName;
		this._player = player;
		createWindow();
		createGameField(19,19);
		initializeChat();
		initializeButtons();
		initializeChatAndButtonsPanel();
		setVisible(true);

	}
	
	private void createWindow() {
		setResizable(true);
		//setTitle("Go game");
		setSize(1500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(_mainPanel);
	}
	
	private void createGameField(int verFieldAmount, int horFieldAmount) {
		_gameField = UI_GameField.builder()
				.verFieldAmount(verFieldAmount)
				.horFieldAmount(horFieldAmount)
				.windowSizeX(getWidth())
				.windowSizeY(getHeight())
				.player(this._player)
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
		_inputForChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				_player.sendToOpponentChat("CHAT:" + _inputForChat.getText());
				getMessageForChat(_nickName + ": " + _inputForChat.getText() + '\n');
				_inputForChat.setText("");
			}
		});
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

		_surrenderBtn = new JButton("SURRENDER");
		//_surrenderBtn.setIcon(new ImageIcon(getClass().getResource("/kamien.png")));
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
	public UI_GameField getUI_Field(){
		return this._gameField;
	}
	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == this._blackStoneBtn) {
			_buttonController.doCertainAction("Pick field");
		}
		
	}

}
