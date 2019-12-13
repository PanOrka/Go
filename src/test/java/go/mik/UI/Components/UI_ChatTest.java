package go.mik.UI.Components;

import org.junit.Test;

import static org.junit.Assert.*;

public class UI_ChatTest {

    UI_Chat _testChat = new UI_Chat();
    @Test
    public void addMessageToChat(){
        _testChat.addMessageToChat("test");
        assertEquals("test",_testChat.getText());
    }
}
