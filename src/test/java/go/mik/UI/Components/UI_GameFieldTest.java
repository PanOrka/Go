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
            .initializeStoneArray()
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
        _testField.addNewStone(null,10 ,10);
        boolean foundStone = false;
        for (int i = 0; i <= _testField.getVerFieldAmount(); i++) {
            for (int j = 0; j <= _testField.getVerFieldAmount(); j++) {
                if(_testField.getStonesArray()[i][j] != null){
                    foundStone = true;
                    break;
                }
            }
        }
        assertTrue(foundStone);
    }
    @Test
    public void removeStoneFromList(){
        _testField.addNewStone(null,10 ,10);
        _testField.removeStoneFromList(10,10);
        boolean foundStone = false;
        for (int i = 0; i <= _testField.getVerFieldAmount(); i++) {
            for (int j = 0; j <= _testField.getVerFieldAmount(); j++) {
                if(_testField.getStonesArray()[i][j] != null){
                    foundStone = true;
                    break;
                }
            }
        }
        assertFalse(foundStone);
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