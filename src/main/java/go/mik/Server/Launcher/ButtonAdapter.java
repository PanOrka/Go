package go.mik.Server.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonAdapter implements ActionListener {
    private final Launcher launcher;

    ButtonAdapter(Launcher launcher) {
        this.launcher = launcher;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.launcher.setServer();
    }
}
