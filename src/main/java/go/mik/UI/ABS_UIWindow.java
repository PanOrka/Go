package go.mik.UI;

import go.mik.Client.Player;
import go.mik.UI.Components.UI_Chat;
import go.mik.UI.Components.UI_GameField;
import go.mik.UI.Controllers.ButtonController;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ABS_UIWindow extends JFrame implements ActionListener {

    protected UI_GameField _gameField;
    protected ButtonController _buttonController;
    protected JButton _blackStoneBtn;
    protected JButton _surrenderBtn;
    protected UI_Chat _chatBox;
    protected JTextField _inputForChat;
    protected Player _player;
    protected String _nickName;

    ABS_UIWindow() {
    }

    public void getMessageForChat(String message){
        _chatBox.addMessageToChat(message);
    };

    public UI_GameField getUI_Field(){
        return  this._gameField;
    };

    abstract void createGameField(int verFieldAmount, int horFieldAmount);

}