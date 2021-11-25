package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class LoadPanel extends JPanel implements ActionListener {
    GameController gc;
    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] c = getComponents();
        int i = 0;
        while(i != c.length){
            if(c[i].equals(e.getSource())) break;
            i++;
        }
        String board = loadGame(i);
        setUpGame(board);
        JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
        AmobaFrame topFrame = (AmobaFrame) f1;
        topFrame.startGame(gc);
    }

    private void setUpGame(String board) {
        int rows =0;
        for(int i = 0; i != board.length()-1; i++){
            if(board.charAt(i) == '\n') rows++;
        }
        int columns = (board.length() - 1 - rows) / rows;
        Character lengthToWin = board.charAt(board.length()-1);
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
        GameBoard gb = new GameBoard(lengthToWin,b);
        AmobaGameModel ag = new AmobaGameModel(lengthToWin,gb,gb.decideCurrentPlayer());
        gc = new GameController(ag);
    }


    private String loadGame(int whichFile){
        Integer i = whichFile;
        String filePath = "C:\\Users\\murga\\IdeaProjects\\nhf\\saves" + "\\save" + i + ".txt";
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
                System.out.println("A keresett fajl nem talalhato.");
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
}
