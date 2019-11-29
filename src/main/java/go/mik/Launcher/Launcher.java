package go.mik.Launcher;

import go.mik.Client.Client;
import go.mik.Client.Player;
import go.mik.PlayerStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements ActionListener {
    private final JTextField nickName;
    private final JTextField socketPort;
    private final JTextField serverAddress;
    private final JButton botPlayButton;
    private final JButton playerPlayButton;
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

        this.botPlayButton.addActionListener(this);
        this.add(this.botPlayButton);

        this.playerPlayButton.addActionListener(this);
        this.add(this.playerPlayButton);
    }

    private void setPlayer() {
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

    private void setBot() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.playerPlayButton) {
            this.setPlayer();
        } else if (e.getSource() == this.botPlayButton) {
            this.setBot();
        }
    }
}
