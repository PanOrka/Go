package go.mik.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TestSender extends JFrame implements ActionListener {
    private JTextField text;
    private Player player;

    TestSender(String nickName, Player player) {
        super("Go " + nickName);
        this.player = player;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));
        this.setSize(new Dimension(150, 100));

        this.text = new JTextField();
        this.add(this.text);

        JButton send = new JButton("send");
        send.addActionListener(this);
        this.add(send);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        player.send(text.getText());
    }
}
