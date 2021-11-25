package nhf;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GameController {

    private AmobaGameModel gameModel;
    private BoardFrame boardFrame;

    public GameController(int lengthToWin, int rows, int columns){
        gameModel = new AmobaGameModel(lengthToWin,rows,columns);
        boardFrame = new BoardFrame(this);
    }


    public GameController(int lengthToWin, ArrayList2D<Character> b) {
        GameBoard gb = new GameBoard(lengthToWin,b);
        gameModel = new AmobaGameModel(lengthToWin,gb,gb.decideCurrentPlayer());
        boardFrame = new BoardFrame(this);
    }

    public BoardFrame getBoard(){ return boardFrame;}

    public AmobaGameModel getModel(){ return gameModel;}

    public void buttonPressed(int which){
        if(gameModel.moveMade(which)){
            boardFrame.markField(which,gameModel.getCurrentPlayer());
            int status = gameModel.isOver(which);
            if(status != -1){
                boardFrame.displayGameOver(status);
            }
            gameModel.switchPlayer();
        } else {
            boardFrame.invalidMove();
        }
    }

    public boolean saveGame(int whichFile) {
        Integer i = whichFile;
        String filePath = "C:\\Users\\murga\\IdeaProjects\\nhf\\saves" + "\\save" + i + ".txt";
        File fi = new File(filePath);
        try {
            // Create a file writer
            FileWriter wr = new FileWriter(fi, false);
            // Create buffered writer to write
            BufferedWriter w = new BufferedWriter(wr);
            // Write
            gameModel.getGameBoard().parseBoard(w);
            w.flush();
            w.close();
            return true;
        }
        catch (Exception evt) {
            JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(boardFrame), evt.getMessage());
            return false;
        }
    }

}
