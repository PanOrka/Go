package go.mik.Client;

public interface ServerConnector {
    void start(boolean playWithBot);
    void sendToChat(String message);
    void setStones(String gameSet);
    void sendToOpponentChat(String message);
    void move(String position);
}
