package go.mik.Client.Launcher;


import go.mik.Client.Player;
import go.mik.Client.PlayerStarter;
import go.mik.Client.ServerConnector;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JFrame implements ActionListener {
    private final JTextField nickName;
    private final JButton botPlayButton;
    private final JButton playerPlayButton;
    private final PlayerStarter playerStarter;

    public Launcher(PlayerStarter playerStarter) {
        super("GoLauncher");
        this.playerStarter = playerStarter;
        this.nickName = new JTextField();
        this.playerPlayButton = new JButton("Play vs Player");
        this.botPlayButton = new JButton("Play vs BOT");
        this.setUI();
    }

    private void setUI() {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 2));
        this.setSize(new Dimension(300, 100));

        this.add(new JLabel("Nick", SwingConstants.CENTER));
        this.add(this.nickName);

        this.botPlayButton.addActionListener(this);
        this.add(this.botPlayButton);

        this.playerPlayButton.addActionListener(this);
        this.add(this.playerPlayButton);
    }

    private void setPlayer(boolean playWithBot) {
        try {
            String nickName = this.nickName.getText();
            if (nickName.equals("")) {
                throw new InvalidNickException();
            }

            ServerConnector serverConnector = new Player(nickName, "127.0.0.1", 1111);
            this.playerStarter.clientInit(serverConnector, playWithBot);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.playerPlayButton) {
            this.setPlayer(false);
        } else if (e.getSource() == this.botPlayButton) {
            this.setPlayer(true);
        }
    }
}
