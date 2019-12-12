package go.mik.UI.Components;

import go.mik.UI.ABS_UIWindow;
import go.mik.UI.UIWindow;
import org.junit.Test;

import static org.junit.Assert.*;

public class UI_GameFieldTest {
    UI_GameField _testField = UI_GameField.builder()
            .horFieldAmount(19)
            .verFieldAmount(19)
            .player(null)
            .windowSizeX(1500)
            .windowSizeY(1000)
            .buildGameField();

    @Test
    public void whichFieldWasPicked() {
    }

    @Test
    public void addStoneToList() {
        _testField.addStoneToList(null,10 ,10);
        assertTrue(0 < _testField.get_stones().size());
    }

    @Test
    public void clearStoneList() {
        _testField.addStoneToList(null,10 ,10);
        _testField.clearStoneList();
        assertEquals(0, _testField.get_stones().size());
    }

    @Test
    public void getVerFieldAmount() {
        assertEquals(18, _testField.getVerFieldAmount());
    }

    @Test
    public void getHorFieldAmount() {
        assertEquals(18, _testField.getHorFieldAmount());
    }
}