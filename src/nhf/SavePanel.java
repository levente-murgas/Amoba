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
        gc.saveGame(i);
    }

    public void setGC(GameController gc) {
        this.gc = gc;
    }
}
