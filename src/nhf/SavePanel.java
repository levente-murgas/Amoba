package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

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
        if(saveGame(i,"\\saves\\save")) {
            JOptionPane.showMessageDialog(this, "Jatek mentve, most már boldogan alhatsz:)",
                    "SIKERES MENTES",
                    JOptionPane.INFORMATION_MESSAGE);
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
            AmobaFrame topFrame = (AmobaFrame) f1;
            topFrame.backToMenu();
            setVisible(false);
        }
    }

    public boolean saveGame(int whichFile, String child) {
        Integer i = whichFile;
        String directory =  System.getProperty("user.dir");
        String filePath = directory + child + i + ".txt";
        File fi = new File(filePath);
        try {
            // Create a file writer
            FileWriter wr = new FileWriter(fi, false);
            // Create buffered writer to write
            BufferedWriter w = new BufferedWriter(wr);
            // Write
            gc.getModel().getGameBoard().parseBoard(w);
            w.flush();
            w.close();
            return true;
        }
        catch (Exception evt) {
            JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this), evt.getMessage());
            return false;
        }
    }

    public void setGC(GameController gc) {
        this.gc = gc;
    }
}
