package go.mik.Launcher;

import go.mik.PlayerStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame {
    private final JTextField nickName;
    private final JTextField socketPort;

    public Launcher(PlayerStarter playerStarter) {
        this.nickName = new JTextField();
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

        JButton botPlayButton = new JButton("Play As Bot");
        botPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(botPlayButton);

        JButton playerPlayButton = new JButton("Play As Player");
        playerPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.add(playerPlayButton);
    }
}
