package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel extends JPanel implements ActionListener {
    GameController gc;
    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] c = getComponents();
        int i = 0;
        while(i != c.length){
            if(c[i].equals(e.getSource())) break;
            i++;
        }
        if(gc.saveGame(i)) {
            JOptionPane.showMessageDialog(this, "Jatek mentve, most már boldogan alhatsz:)",
                    "SIKERES MENTES",
                    JOptionPane.INFORMATION_MESSAGE);
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
            AmobaFrame topFrame = (AmobaFrame) f1;
            topFrame.backToMenu();
            setVisible(false);
        }
    }

    public void setGC(GameController gc) {
        this.gc = gc;
    }
}
