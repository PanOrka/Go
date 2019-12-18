package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;
import go.mik.Server.Logic.Game.GameRules.BreathChecker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BreathCheckerTest {

    private Game _testGame;
    private BreathChecker _testBreathChecker;
    private Method Game_Move;
    private Method Check_Breath;
    private Field isEmpty;
    private String[] testFieldsArray;

    @Before
    public void initializeComponentsAndMethods() throws NoSuchMethodException, NoSuchFieldException {
        this._testGame = new Game();
        this._testBreathChecker = new BreathChecker(_testGame);
        this.Game_Move = Game.class.getDeclaredMethod("move", String.class, char.class);
        this.Check_Breath = BreathChecker.class.getDeclaredMethod("checkBreath",int.class, int.class);
        this.isEmpty = BreathChecker.class.getDeclaredField("isEmpty");

        Game_Move.setAccessible(true);
        isEmpty.setAccessible(true);
        Check_Breath.setAccessible(true);
    }

    @Test
    public void checkBreathInCorner() throws InvocationTargetException, IllegalAccessException {
        int result = 0;
        testFieldsArray = new String[3];
        //Breath = 0
        Game_Move.invoke(_testGame,"0;0",'b');
        testFieldsArray[0] = "0;0";
        //Breath = 1
        Game_Move.invoke(_testGame,"0;1",'b');
        testFieldsArray[1] = "0;1";
        //Breath = 1
        Game_Move.invoke(_testGame,"1;0",'b');
        testFieldsArray[2] = "1;0";

        result = calculateAllBreaths(testFieldsArray);

        assertEquals(2,result);

    }

    @Test
    public void checkAloneStone() throws InvocationTargetException, IllegalAccessException {
        Game_Move.invoke(_testGame,"5;5",'b');
        Check_Breath.invoke(_testBreathChecker,5,5);
        int result = 0;
        result += howManyBreaths((boolean[]) isEmpty.get(_testBreathChecker));

        assertEquals(1,result);
    }

    @Test
    public void checkDonutChain() throws InvocationTargetException, IllegalAccessException {
        int result = 0;
        testFieldsArray = new String[8];
        //Black Stones
        Game_Move.invoke(_testGame,"0;0",'b');
        testFieldsArray[0] = "0;0";
        Game_Move.invoke(_testGame,"1;0",'b');
        testFieldsArray[1] = "1;0";
        Game_Move.invoke(_testGame,"2;0",'b');
        testFieldsArray[2] = "2;0";
        Game_Move.invoke(_testGame,"2;1",'b');
        testFieldsArray[3] = "2;1";
        Game_Move.invoke(_testGame,"2;2",'b');
        testFieldsArray[4] = "2;2";
        Game_Move.invoke(_testGame,"0;1",'b');
        testFieldsArray[5] = "0;1";
        Game_Move.invoke(_testGame,"0;2",'b');
        testFieldsArray[6] = "0;2";
        Game_Move.invoke(_testGame,"1;2",'b');
        testFieldsArray[7] = "1;2";

        result = calculateAllBreaths(testFieldsArray);

        assertEquals(7,result);

        result = 0;
        Game_Move.invoke(_testGame,"1;1",'w');
        result = calculateAllBreaths(testFieldsArray);

        assertEquals(8,result);

    }

    public int howManyBreaths(boolean[] resultArray){
        int breathCounter = 0;
        for(boolean bool : resultArray){
            if(bool){
                breathCounter++;
            }
        }
        return breathCounter;
    }

    public int calculateAllBreaths(String[] testArray) throws InvocationTargetException, IllegalAccessException {
        int result = 0;
        String[] parsedFields;
        int x;
        int y;

        for (String s : testArray) {
           parsedFields = s.split(";");
           x = Integer.parseInt(parsedFields[0]);
           y = Integer.parseInt(parsedFields[1]);
           Check_Breath.invoke(_testBreathChecker, x, y);
           result += howManyBreaths((boolean[]) isEmpty.get(_testBreathChecker));
           System.out.println(result);
        }
        return result;
    }

}
