package go.mik.UI.Components;

import org.junit.Test;

import static org.junit.Assert.*;

public class UI_GameFieldTest {
    UI_GameField _testField = UI_GameField.builder()
            .horFieldAmount(19)
            .verFieldAmount(19)
            .player(null)
            .windowSizeX(1500)
            .windowSizeY(1000)
            .defaultGapForGameField(50)
            .calculatVerLength()
            .calculateHorLength()
            .buildGameField();

    @Test
    public void whichFieldWasPicked() {
        int _verLength = (int) Math.floor((1500 - 650 + _testField.getVerFieldAmount())/_testField.getVerFieldAmount());
        int _horLength = (int) Math.floor((1500 - 650 + _testField.getHorFieldAmount())/_testField.getHorFieldAmount());
        int[] wantedField = new int[]{5, 5};
        int[] calculatedField = _testField.whichFieldWasPicked(_verLength * 4 + 50,_horLength * 4 + 50);
        int[] testedFIeld = new int[]{calculatedField[0],calculatedField[1]};

        //Sprawdzmy, czy wybrane zostalo pole [5;5]
        assertArrayEquals(wantedField,testedFIeld);
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

    @Test
    public void paintGameboardTest(){
    }
}