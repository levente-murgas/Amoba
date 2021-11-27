package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * A folytatni kívánt játékállások elmentéséért felelős SavePanel osztály.
 */
public class SavePanel extends JPanel implements ActionListener {
    private GameController gc;
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

    /**
     * Elmenti a játékot az adott sorszámú mentésfájlba.
     *
     * @param whichFile a mentésfájl sorszáma
     * @param child     a mentésfájl alkönyvtára
     *                  és a mentésfájl neve
     * @return sikerült-e a mentés
     */
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

    /**
     * Setter.
     * Beállítja a GameControllert.
     * @param gc a GameController
     */
    public void setGC(GameController gc) {
        this.gc = gc;
    }
}
