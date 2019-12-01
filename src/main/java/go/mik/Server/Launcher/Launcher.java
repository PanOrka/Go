package go.mik.Server.Launcher;

import go.mik.Server.Server;
import go.mik.Server.ServerStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements ActionListener {
    private final JTextField socketPort;
    private final JButton startServer;
    private final ServerStarter serverStarter;

    public Launcher(ServerStarter serverStarter) {
        this.serverStarter = serverStarter;
        this.socketPort = new JTextField();
        this.startServer = new JButton("Start Server");
        this.setUI();
    }

    private void setUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 2));
        this.setSize(new Dimension(300, 100));

        this.add(new JLabel("Socket Port", SwingConstants.CENTER));
        this.add(this.socketPort);

        this.startServer.addActionListener(this);
        this.add(this.startServer);
    }

    private void setServer() {
        try {
            int socketPort = Integer.parseInt(this.socketPort.getText());

            Server server = new Server(socketPort);
            this.serverStarter.serverInit(server);
            this.dispose();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setServer();
    }
}
