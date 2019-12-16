package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class SuicideMoveCheckerTest {


    private Method Game_Move;
    private Game _testGame;
    private SuicideMoveChecker _testChecker;

    @Before
    public void initializeComponentsAndMethods() throws NoSuchMethodException {
        this._testGame = new Game();
        this._testChecker = new SuicideMoveChecker(_testGame);
        this.Game_Move = Game.class.getDeclaredMethod("move", String.class, char.class);

        Game_Move.setAccessible(true);
    }

    @Test
    public void checkOnEdge() throws InvocationTargetException, IllegalAccessException {

        Game_Move.invoke(_testGame,"0;1",'b');
        Game_Move.invoke(_testGame,"1;0",'b');

        assertEquals("CHAT:That move is not valid",Game_Move.invoke(_testGame,"0;0",'w'));

    }

    @Test
    public void checkWithBlackStones() throws InvocationTargetException, IllegalAccessException{

        Game_Move.invoke(_testGame,"0;1",'b');
        Game_Move.invoke(_testGame,"1;0",'b');

        assertEquals( "GAME:put:0;0;b",Game_Move.invoke(_testGame,"0;0",'b'));
    }

    @Test
    public void checkWithA_LotOfStones() throws InvocationTargetException, IllegalAccessException {

        //Black Player
        Game_Move.invoke(_testGame,"1;1",'b');
        Game_Move.invoke(_testGame,"1;2",'b');
        Game_Move.invoke(_testGame,"1;3",'b');
        Game_Move.invoke(_testGame,"1;4",'b');
        Game_Move.invoke(_testGame,"2;4",'b');
        Game_Move.invoke(_testGame,"3;3",'b');
        Game_Move.invoke(_testGame,"4;2",'b');
        Game_Move.invoke(_testGame,"5;1",'b');
        Game_Move.invoke(_testGame,"5;0",'b');
        Game_Move.invoke(_testGame,"4;0",'b');
        Game_Move.invoke(_testGame,"3;0",'b');

        //WhitePlayer
        Game_Move.invoke(_testGame,"4;1",'w');
        Game_Move.invoke(_testGame,"3;1",'w');
        Game_Move.invoke(_testGame,"3;2",'w');
        Game_Move.invoke(_testGame,"2;2",'w');
        Game_Move.invoke(_testGame,"2;3",'w');

        //Check if i can put black at white's field after killing
        assertEquals( "GAME:put:2;0;b",Game_Move.invoke(_testGame,"2;0",'b'));

    }

    @Test
    public void checkKO() throws InvocationTargetException, IllegalAccessException {
        //Black Player
        Game_Move.invoke(_testGame,"1;0",'b');
        Game_Move.invoke(_testGame,"0;1",'b');
        Game_Move.invoke(_testGame,"1;2",'b');

        //White Player
        Game_Move.invoke(_testGame,"1;1",'w');
        Game_Move.invoke(_testGame,"2;0",'w');
        Game_Move.invoke(_testGame,"3;1",'w');
        Game_Move.invoke(_testGame,"2;2",'w');

        //Choke White's stone [1,1]
        Game_Move.invoke(_testGame,"2;1",'b');

        assertEquals( "CHAT:This place is dead", Game_Move.invoke(_testGame,"1;1",'w'));

    }

}
