package go.mik.Client;

import go.mik.Server.Logic.Game.Game;
import go.mik.Server.ServerTest;
import go.mik.UI.Components.Stone;
import go.mik.UI.Components.UIWindow;
import go.mik.UI.Components.ABS_UIWindow;
import go.mik.UI.Components.UI_GameField;
import static org.mockito.Mockito.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class PlayerTest {
    private static ServerTest.threadServerStarter _threadServerStarter;
    private static ServerTest.threadPlayerStarter _threadPlayerStarter;
    private static Field _gameWindow;
    private static Method Game_Move;

    @BeforeClass
    public static void initializeNeededComponents() throws NoSuchFieldException{
        _gameWindow = Player.class.getDeclaredField("_gameWindow");
        _gameWindow.setAccessible(true);
    }

    @Test
    public void setStonesTestWithoutPut() throws Exception {

        Thread _server = new Thread(new ServerTest.threadServerStarter(9999));
        _server.start();

        Thread.sleep(100);

        ServerConnector _firstPlayer = new Player("test","127.0.0.1",9999);
        _firstPlayer.setStones("ooowooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "oooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowooooooooooooooooooooooooooooooooooooooooooooooooooooooooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                "ooowoooooooooooooooooooooooooooooooooooooooooooooooooooooo");

        UIWindow gameWindow = (UIWindow) _gameWindow.get(_firstPlayer);
        UI_GameField gameField = gameWindow.getUI_Field();

        int i = 0;

        for(int j = 0; j < 19; j++){
            if( gameField.getStonesArray()[0][j] != null){
                i++;
            }
        }
        assertEquals(1,i);
    }

    @Test
    public void setStonesWithPut() throws Exception {
        Thread _server = new Thread(new ServerTest.threadServerStarter(9998));
        _server.start();

        Thread.sleep(100);

        ServerConnector _firstPlayer = new Player("test","127.0.0.1",9998);

        _firstPlayer.setStones("put:18;18;b");

        UIWindow gameWindow = (UIWindow) _gameWindow.get(_firstPlayer);
        UI_GameField gameField = gameWindow.getUI_Field();

        assertNotEquals(null,gameField.getStonesArray()[18][18]);

    }
}
