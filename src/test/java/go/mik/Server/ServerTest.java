package go.mik.Server;
import go.mik.Client.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTest {

    private Field _playerCounter;
    private static threadServerStarter _threadServerStarter;
    private static threadPlayerStarter _threadPlayerStarter;

    private static ExecutorService _service = Executors.newFixedThreadPool(10);


    @Before
    public void initializeField() throws Exception {

        this._playerCounter = Server.class.getDeclaredField("playerCounter");
        _playerCounter.setAccessible(true);

    }

    @Test
    public void checkIfMorePLayersCanJoinWithoutBot() throws Exception {
        Thread _server = new Thread(new threadServerStarter(9999),"server");
        _server.start();
        Thread.sleep(100);

        Thread player1 = new Thread(new threadPlayerStarter("test1",false,9999),"player1");
        Thread player2 = new Thread(new threadPlayerStarter("test2",false,9999),"player2");
        Thread player3 = new Thread(new threadPlayerStarter("test3",false,9999),"player3");

        player1.start();
        player2.start();
        player3.start();

        Thread.sleep(100);

        int _playerCounter = (int) this._playerCounter.get(threadServerStarter._testServer);

        //Check if first game started
        assertEquals(1,_playerCounter);

    }

    @Test
    public void checkIfMorePLayersCanJoinWithBot() throws Exception {
        Thread _server = new Thread(new threadServerStarter(9998),"server");
        _server.start();

        Thread.sleep(100);

        Thread player1 = new Thread(new threadPlayerStarter("test1",false,9998),"player1");
        Thread player2 = new Thread(new threadPlayerStarter("test2",true,9998),"player2");

        player1.start();
        player2.start();

        Thread.sleep(100);

        int _playerCounter = (int) this._playerCounter.get(threadServerStarter._testServer);

        //Check if first player is still waiting for opponent
        assertEquals(1,_playerCounter);

    }

    public static class threadServerStarter implements Runnable{
        public static Server _testServer;

        public threadServerStarter(int serverPort){
            _testServer = new Server(serverPort);
        }
        @Override

        public void run() {
            _testServer.start();
        }
    }

    public static class threadPlayerStarter implements Runnable{
        private Player _player;
        public boolean _playWithBot;

        public threadPlayerStarter(String addicion, boolean playWithBot, int serverPort) throws Exception {
            _player = new Player("test" + addicion,"127.0.0.1", serverPort);
            _playWithBot = playWithBot;
        }

        @Override
        public void run() {
            _player.start(_playWithBot);
        }

        public Player getPlayerInstance(){
            return this._player;
        }
    }

}
