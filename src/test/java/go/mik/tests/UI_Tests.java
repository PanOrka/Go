package go.mik.tests;

import go.mik.UI.ABS_UIWindow;
import go.mik.UI.Components.UI_GameField;
import go.mik.UI.UIWindow;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UI_Tests {

    //Test konstruktora okna
    @Test
    public void createGoodUI_GameField()
    {
        ABS_UIWindow _testWindow = new UIWindow(null,"test");
        assertNotNull(_testWindow.getUI_Field());
       // assertTrue(_testWindow.getUI_Field().getVerFieldAmount()!= 0);
       // assertTrue(_testWindow.getUI_Field().getHorFieldAmount()!= 0);
    }


}
