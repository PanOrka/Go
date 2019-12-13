package go.mik.UI.Components;

import go.mik.Client.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ABS_UIWindow extends JFrame {

    protected UI_GameField _gameField;
    protected JButton _passBtn;
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

    //For Testing
    public UI_Chat get_chatBox(){
        return  this._chatBox;
    }
    //For Testing
    public JTextField get_inputForChat(){
        return this._inputForChat;
    }

}