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
    GameController gcArray[];
    JButton[] loadBtns;

    ///betölti az összes mentést ami van
    public LoadPanel(int size){
        JLabel saves = new JLabel("Saves");
        add(saves);
        gcArray = new GameController[size];
        loadBtns = new JButton[size];
        for(int i = 0; i != loadBtns.length; i++){
            loadBtns[i] = new JButton("Save" + (i+1));
            add(loadBtns[i]);
            loadBtns[i].addActionListener(this);
            String board = loadGame(i+1);
            if(!board.isEmpty()){
                loadBtns[i].setEnabled(true);
                gcArray[i] = setUpGame(board);
            }
            else {loadBtns[i].setEnabled(false);}
        }
    }

    public void update(int which){
        String board = loadGame(which);
        if(!board.isEmpty()){
            loadBtns[which].setEnabled(true);
            gcArray[which] = setUpGame(board);
        }
        else {loadBtns[which].setEnabled(false);}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = 0;
        while(i != loadBtns.length){
            if(loadBtns[i].equals(e.getSource())) break;
            i++;
        }
        JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
        AmobaFrame topFrame = (AmobaFrame) f1;
        topFrame.startGame(gcArray[i]);
    }

    private GameController setUpGame(String board) {
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
        return new GameController(lengthToWin,b);
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
}
