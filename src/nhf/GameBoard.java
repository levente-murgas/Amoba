package nhf;


import nhf.AmobaGameModel.Player;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class GameBoard {

    private ArrayList2D<Character> Board;

    public GameBoard(int columns, int rows) {
        Board = new ArrayList2D<>(rows,columns,'0');
    }

    public void parseBoard(Writer w) throws IOException {
        for(int i = 0; i != Board.getRows(); i++){
            for(int j = 0; j != Board.getColumns(); j++){
                w.write(Board.get(i).get(j));
            }
            w.write("\n");
        }
    }
 

    public String stringOfRow(int row){
        char[] ret = new char[Board.getColumns()];
        for(int i = 0; i != Board.getColumns(); i++){
            ret[i] = Board.get(row).get(i);
        }
        return new String(ret);
    }

    public String stringOfColumn(int column){
        char[] ret = new char[Board.getRows()];
        for(int i = 0; i != Board.getRows(); i++){
           ret[i] = Board.get(i).get(column);
        }
        return new String(ret);
    }


    public String reverseString(String str){
        String nstr="";
        char ch;
        for (int i=0; i<str.length(); i++)
        {
            ch= str.charAt(i);
            nstr= ch+nstr;
        }
        return nstr;
    }

    public String stringOfDecreasingDiagonal(int row, int column){
        String ret = "";
        //egyet fel egyet balra
        int i = row;
        int j = column;
        while(i >= 0 && j >= 0) {
            ret = ret.concat(Board.get(i--).get(j--).toString());
        }
        ret = reverseString(ret);
        //az ujonnan lerakott jelet nem vesszük kétszer bele
        if(row != Board.getRows() -1 && column != Board.getColumns()-1) {
            i = row + 1;
            j = column + 1;
            //egyet le egyet jobbra
            while (i <= Board.getRows()-1 && j <= Board.getColumns()-1) {
                ret = ret.concat(Board.get(i++).get(j++).toString());
            }
        }
        return ret;
    }

    public String stringOfIncreasingDiagonal(int row, int column){
        String ret = "";
        //egyet fel egyet jobbra
        int i = row;
        int j = column;
        while(i >= 0 && j <= Board.getColumns() - 1){
            ret = ret.concat(Board.get(i--).get(j++).toString());
        }

        ret = reverseString(ret);
        //az ujonnan lerakott jelet nem vesszük kétszer bele
        if(row != Board.getRows()-1 && column != 0){
            i = row + 1;
            j = column - 1;
            //egyet le egyet balra
            while (i <= Board.getRows() -1  && j >= 0) {
              ret = ret.concat(Board.get(i++).get(j--).toString());

            }
        }
        return ret;
    }

    public int getFieldSize(){ return Board.getSize();}

    public int getColumns(){return Board.getColumns();}

    public int getRows(){return Board.getRows();}

    public int[] findPos(int i){
        int[] pos = new int[2];
        pos[0] = i / Board.getColumns();
        pos[1] = i % Board.getColumns();
        return pos;
    }

    public static void setValue(ArrayList<ArrayList<Character>> list, int row, int column, char value){
        list.get(row).set(column, value);
    }

    public boolean place(int pos, Player player) {
        if (player == null) {
            throw new IllegalArgumentException("cannot mark null player");
        }
        int[] position = findPos(pos);
        int row = position[0];
        int column = position[1];
        if(Board.get(row).get(column) != '0'){
            return false;
        } else {
            Board.setValue(row,column,Player.toChar(player));
            return true;
        }
    }

    public int positionsLeft() {
        int ret = 0;
        for(int i = 0; i != Board.getRows(); i++){
            for(int j = 0; j != Board.getColumns(); j++) {
                if ( Board.get(i).get(j) == '0') ret++;
            }
        }
        return ret;
    }
}
