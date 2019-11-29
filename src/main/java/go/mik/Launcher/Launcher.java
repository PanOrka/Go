package go.mik.Launcher;

import go.mik.Client.Player;
import go.mik.PlayerStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements ActionListener {
    private final JTextField nickName;
    private final JTextField socketPort;
    private final JButton botPlayButton;
    private final JButton playerPlayButton;
    private final PlayerStarter playerStarter;

    public Launcher(PlayerStarter playerStarter) {
        this.playerStarter = playerStarter;
        this.nickName = new JTextField();
        this.playerPlayButton = new JButton("Play As Player");
        this.botPlayButton = new JButton("Play As Bot");
        this.socketPort = new JTextField();
        this.setUI();
    }

    private void setUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3, 2));
        this.setSize(new Dimension(300, 150));
        this.setBackground(Color.BLACK);

        this.add(new JLabel("Nick", SwingConstants.CENTER));
        this.add(this.nickName);

        this.add(new JLabel("Socket Port", SwingConstants.CENTER));
        this.add(this.socketPort);

        this.botPlayButton.addActionListener(this);
        this.add(this.botPlayButton);

        this.playerPlayButton.addActionListener(this);
        this.add(this.playerPlayButton);
    }

    private void setPlayer() {
        this.playerStarter.clientInit(new Player());
        this.dispose();
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
