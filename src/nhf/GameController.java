package nhf;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GameController {

    private GameModel gameModel;
    private BoardFrame boardFrame;

    public GameController(int lengthToWin, int rows, int columns){
        gameModel = new GameModel(lengthToWin,rows,columns);
        boardFrame = new BoardFrame(this);
    }


    public GameController(int lengthToWin, ArrayList2D<Character> b) {
        GameBoard gb = new GameBoard(lengthToWin,b);
        gameModel = new GameModel(lengthToWin,gb,gb.decideCurrentPlayer());
        boardFrame = new BoardFrame(this);
    }

    public BoardFrame getBoard(){ return boardFrame;}

    public GameModel getModel(){ return gameModel;}

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

}
