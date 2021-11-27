package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * A mentett játékállások betöltéséért felelős LoadPanel osztály.
 */
public class LoadPanel extends JPanel implements ActionListener {
    private GameController gc;

    /**
     * ActionListener actionperformed() függvényének implementációja.
     * Megkeresi a kívánt játékállást melyik fájlban kell keresni,
     * betölti, majd elindítja a játékot.
     * üzenettel értesíti a felhasználót ha a keresett fájlba még
     * nem volt játék mentve.
     * @param e a kiváltó esemény
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] c = getComponents();
        int i = 0;
        while(i != c.length){
            if(c[i].equals(e.getSource())) break;
            i++;
        }
        String board = loadGame(i,"\\saves\\save");
        if(!board.isEmpty()) {
            setUpGame(board);
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
            AmobaFrame topFrame = (AmobaFrame) f1;
            topFrame.startGame(gc);
        }
        else{
            JOptionPane.showMessageDialog(this, "No saves here yet.",
                    "Empty file", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * A mentett játék szöveges reprezentációját értelmezi és a kinyert
     * adatokból létrehozza a GameControllert.
     *
     * @param board - a mentett játék szöveges reprezentációja
     */
    protected void setUpGame(String board) {
        int rows =0;
        for(int i = 0; i != board.length()-1; i++){
            if(board.charAt(i) == '\n') rows++;
        }
        int columns = (board.length() - 1 - rows) / rows;
        char lTW = board.charAt(board.length()-1);
        int lengthToWin = lTW - '0';
        StringBuilder withoutWS = new StringBuilder(board.replaceAll("\\n", ""));
        withoutWS = withoutWS.deleteCharAt(withoutWS.length()-1);

        ArrayList<ArrayList<Character>> a = new ArrayList<>(rows);
        for(int i = 0; i != rows; i++){
            a.add(new ArrayList<>(columns));
            for(int j = 0; j != columns; j++){
                a.get(i).add(withoutWS.charAt(i*columns + j % columns));
            }
        }
        ArrayList2D<Character> b = new ArrayList2D<>(a);
        gc = new GameController(lengthToWin,b);
    }


    /**
     * Beolvassa a keresett fájlból a játékállás
     * szöveges reprezentációját.
     *
     * @param whichFile az olvasandó fájl sorszáma
     * @param child     az alkönyvtár amiben a fájl
     *                  található és a fájl neve
     * @return a játék szöveges reprezentációja
     */
    protected String loadGame(int whichFile, String child){
        Integer i = whichFile;
        String directory =  System.getProperty("user.dir");
        String filePath = directory + child + i + ".txt";
        File fi = new File(filePath);
        StringBuilder board = new StringBuilder();
        try {
            int ch;
            FileReader fr=null;
            try
            {
                fr = new FileReader(fi);
            }
            catch (FileNotFoundException fe)
            {
                System.out.println("The file could not be found.");
            }
            // read from FileReader till the end of file
            if (fi.length() != 0) {
                while ((ch = fr.read()) != -1) {
                    board.append((char) ch);
                }
                // close the file
                fr.close();
            }
        }
        catch (Exception evt) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this), evt.getMessage());
        }
        return board.toString();
    }

    /**
     * Getter.
     * Visszaadja a GameControllert
     * @return a GameController
     */
    public GameController getGc(){return gc;}
}
