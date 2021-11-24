package nhf;

import java.awt.*;

public class GameController {

    private AmobaGameModel gameModel;
    private BoardFrame boardFrame;

    public GameController(int lengthToWin, int rows, int columns){
        gameModel = new AmobaGameModel(lengthToWin,rows,columns);
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

    public Window getWindow(){
        return boardFrame.getWindow();
    }
}
