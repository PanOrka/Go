package go.mik.UI.Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UI_Chat extends JTextArea{

    public UI_Chat(){
        setBorder(new EmptyBorder(50,0,50,350));
        setForeground(Color.DARK_GRAY);
        setBackground(Color.getHSBColor(5,5,5));
        setFont(new Font("TimesRoman", Font.BOLD , 18));
        setEditable(false);
        setColumns(10);
        setRows(10);
    }

    public void addMessageToChat(String message){
        append(message);
    }

}
