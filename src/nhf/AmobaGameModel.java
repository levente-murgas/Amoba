package nhf;


import java.io.IOException;
import java.io.Writer;

public class AmobaGameModel  {
    public  enum Player {
        O, X;
        public static Player opponentOf(Player player) {
            return player == X ? O : X;
        }
        public static char toChar(Player player){
            return player == X ? 'X' : 'O';
        }
    }
    private GameBoard Board;
    private Player CurrentPlayer;
    private int LengthToWin;

    public AmobaGameModel(int lengthToWin, int rows, int columns){
        Board = new GameBoard(lengthToWin,columns,rows);
        LengthToWin = lengthToWin;
        CurrentPlayer = Player.X;
    }

    private String makeCheckLineStr(Player toCheck){
        char[] checkLine = new char[LengthToWin];
        for(int i = 0; i != LengthToWin; i++) checkLine[i] = Player.toChar(toCheck);
        return new String(checkLine);
    }

    private boolean rowMatches(int row) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String RowToCheck = Board.stringOfRow(row);
        return RowToCheck.contains(checkLineString);
    }


    private boolean columnMatches(int column) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String ColumnToCheck = Board.stringOfColumn(column);
        return ColumnToCheck.contains(checkLineString);
    }


    private boolean diagonalMatches(int row, int column) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String incDiagonal = Board.stringOfIncreasingDiagonal(row,column);
        String decDiagonal = Board.stringOfDecreasingDiagonal(row,column);
        return (incDiagonal.contains(checkLineString) || decDiagonal.contains(checkLineString));
    }

    public void switchPlayer(){
        CurrentPlayer = Player.opponentOf(CurrentPlayer);
    }

    public boolean hasWin(int pos){
        int[] position = Board.findPos(pos);
        int row = position[0];
        int column = position[1];
        return (rowMatches(row) || columnMatches(column) || diagonalMatches(row,column));
    }

    public int isOver(int pos) {
        if(hasWin(pos))
            return 1;
        else if (Board.positionsLeft() == 0)
            return 0;
        else
            return -1;
    }


    public GameBoard getGameBoard() {
        return Board;
    }

    public boolean moveMade(int pos) {
        return Board.place(pos, CurrentPlayer);
    }

    public Player getCurrentPlayer() { return CurrentPlayer;}
}
