package go.mik.Client.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAdapter implements ActionListener {
    private final Launcher launcher;

    ButtonAdapter(Launcher launcher) {
        this.launcher = launcher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.launcher.playerPlayButton) {
            this.launcher.setPlayer();
        } else if (e.getSource() == this.launcher.botPlayButton) {
            this.launcher.setBot();
        }
    }
}
