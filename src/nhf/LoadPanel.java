package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
        /*boardView = gc.getBoard();
        gameMenu.add(boardView,BorderLayout.CENTER);
        cl.show(Cards,"game");
         */
    }

    private void setUpGame(String board) {
        int rows =0;
        for(int i = 0; i != board.length()-1; i++){
            if(board.charAt(i) == '\n') rows++;
        }
        int columns = board.length() / rows;
        int lengthToWin = board.length() - 1;

        gc = new GameController(lengthToWin,rows,columns);
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
