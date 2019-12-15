package go.mik.UI.Components;

import go.mik.Client.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

// ZABAWA
public class UIWindow extends ABS_UIWindow{

	private JPanel _mainPanel = new JPanel(new BorderLayout(2,2));
	private JPanel _chatAndButtonsPanel = new JPanel(new GridBagLayout());
	private JScrollPane _chatPanel;

	public UIWindow(Player player, String nickName){
		this._nickName = nickName;
		this._player = player;
		createWindow();
		createGameField(19,19);
		initializeChat();
		initializeButtons();
		initializeChatAndButtonsPanel();
		setVisible(true);
	}

	@Override
	void createGameField(int verFieldAmount, int horFieldAmount) {
		_gameField = UI_GameField.builder()
				.verFieldAmount(verFieldAmount)
				.horFieldAmount(horFieldAmount)
				.windowSizeX(getWidth())
				.windowSizeY(getHeight())
				.defaultGapForGameField(50)
				.calculatVerLength()
				.calculateHorLength()
				.player(this._player)
				.initializeStoneArray()
				.buildGameField();

		_gameField.repaint();
		_gameField.setBorder(new EmptyBorder(0,0,0,50));
		_gameField.setBackground(Color.WHITE);
		_mainPanel.add(_gameField, BorderLayout.CENTER);
	}

	private void createWindow() {
		setResizable(false);
		setTitle("Go " + _nickName);
		setSize(1500, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(_mainPanel);
	}



	private void initializeChatAndButtonsPanel(){
		GridBagConstraints c = new GridBagConstraints();

		_chatAndButtonsPanel.setBorder(new EmptyBorder(50,100,0,100));
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
				_player.sendToOpponentChat(_inputForChat.getText());
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
		_chatAndButtonsPanel.add(_passBtn,c);

		c.gridx = 0;
		c.gridy = 3;
		c.weighty = 0.8;
		c.weightx = 0;
		_chatAndButtonsPanel.add(_surrenderBtn,c);

		_mainPanel.add(_chatAndButtonsPanel, BorderLayout.EAST);
	}
	private void initializeButtons(){

		ImageIcon _surrFlag = new ImageIcon(getClass().getResource("/surrFlag.png"));
		ImageIcon _passIcon = new ImageIcon(getClass().getResource("/pass.png"));

		_passBtn = new JButton();
		_passBtn.setIcon(_passIcon);
		_passBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				_player.pass();
			}
		});
		_passBtn.setBackground(Color.white);
		_passBtn.setBorderPainted(false);
		_passBtn.setPreferredSize(new Dimension(150,50));

		_surrenderBtn = new JButton();
		_surrenderBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				_player.surrender();
			}
		});
		_surrenderBtn.setIcon(_surrFlag);
		_surrenderBtn.setBackground(Color.white);
		_surrenderBtn.setBorderPainted(false);
		_surrenderBtn.setPreferredSize(new Dimension(150,50));

	}

	private void initializeChat(){
		_chatBox = new UI_Chat();
		DefaultCaret caret = (DefaultCaret)_chatBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		_inputForChat = new JTextField();
		_inputForChat.setPreferredSize(new Dimension(350, 50));
	}

}
