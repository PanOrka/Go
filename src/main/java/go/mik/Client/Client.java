package go.mik.Client;

public interface Client {
    void start();
    void sendToChat(String message);
    void setStones(String gameSet);
    void sendToOpponentChat(String message);
    void move(String position);
    void quit();
}
