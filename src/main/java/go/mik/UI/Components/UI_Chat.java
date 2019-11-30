package go.mik.UI.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI_Chat extends JTextArea {

    public UI_Chat(){
        setBorder(new EmptyBorder(0,0,0,400));
        setForeground(Color.BLUE);
        setFont(new Font("TimesRoman", Font.BOLD , 20));
        setLineWrap(true);
        setEditable(false);
    }

    public void addMessageToChat(String message){
        append(message);
    }

}
