package go.mik.Bot.Bots;

interface ServerConnector {
    void getInput();
    void sendToServer(String msg);
    void setStones(String gameSet);
    void move(String position);
}
