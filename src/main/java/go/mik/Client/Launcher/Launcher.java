package go.mik.Client.Launcher;

import go.mik.Client.Logic.Client;
import go.mik.Client.Logic.Player;
import go.mik.Client.PlayerStarter;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JFrame implements Runnable {
    private final JTextField nickName;
    private final JTextField socketPort;
    private final JTextField serverAddress;
    final JButton botPlayButton;
    final JButton playerPlayButton;
    private final PlayerStarter playerStarter;

    public Launcher(PlayerStarter playerStarter) {
        super("GoLauncher");
        this.playerStarter = playerStarter;
        this.nickName = new JTextField();
        this.serverAddress = new JTextField();
        this.playerPlayButton = new JButton("Play As Player");
        this.botPlayButton = new JButton("Play As Bot");
        this.socketPort = new JTextField();
        this.setUI();
    }

    private void setUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 2));
        this.setSize(new Dimension(300, 150));

        this.add(new JLabel("Nick", SwingConstants.CENTER));
        this.add(this.nickName);

        this.add(new JLabel("Server Address", SwingConstants.CENTER));
        this.add(this.serverAddress);

        this.add(new JLabel("Socket Port", SwingConstants.CENTER));
        this.add(this.socketPort);

        this.botPlayButton.addActionListener(new ButtonAdapter(this));
        this.add(this.botPlayButton);

        this.playerPlayButton.addActionListener(new ButtonAdapter(this));
        this.add(this.playerPlayButton);
    }

    void setPlayer() {
        try {
            String nickName = this.nickName.getText();
            String serverAddress = this.serverAddress.getText();
            int socketPort = Integer.parseInt(this.socketPort.getText());
            Client player = new Player(nickName, serverAddress, socketPort);

            this.playerStarter.clientInit(player);
            this.dispose();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    void setBot() {

    }

    @Override
    public void run() {
        try {
            System.out.println ("Thread " +
                    Thread.currentThread().getId() +
                    " is running");
        }
        catch(Exception e) {
            System.out.println ("Exception is caught");
        }
    }
}
