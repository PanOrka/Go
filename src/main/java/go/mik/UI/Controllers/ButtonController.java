package go.mik.UI.Controllers;

import javax.swing.*;

public class ButtonController {
    private final JFrame _frame;

    public ButtonController( JFrame frame){
        _frame = frame;
    }

    public void doCertainAction(final String _action) {
        switch(_action) {
            case "Pick field":
                pickField();
                break;
            default:
        }
    }

    public void pickField(){

    }

    public JFrame getFrame(){
        return _frame;
    }
}
