package go.mik.Server.Logic.Game.GameRules;

import go.mik.Server.Logic.Game.Game;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class SuicideMoveCheckerTest {

    Game _testGame;
    SuicideMoveChecker _testChecker;

    @Test
    public void checkTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Method test = SuicideMoveChecker.class.getDeclaredMethod("checkOutOfBreath", int.class, int.class);
        _testGame = new Game();
        _testChecker = new SuicideMoveChecker(_testGame);

        Method Game_Move = Game.class.getDeclaredMethod("move", String.class, char.class);
        Game_Move.setAccessible(true);

        Game_Move.invoke(_testGame,"GAME:put:0;1",'b');
        Game_Move.invoke(_testGame,"GAME:put:1;0",'b');

        _testChecker.check(0,0);

    }
}
