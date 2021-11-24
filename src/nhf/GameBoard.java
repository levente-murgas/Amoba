package nhf;


import nhf.AmobaGameModel.Player;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private int Columns;
    private int Rows;
    private ArrayList<ArrayList<Character>> Field;
    private int FieldSize;

    public GameBoard(int columns, int rows) {
        Columns = columns;
        Rows = rows;
        FieldSize = Columns * Rows;
        Field = new ArrayList<ArrayList<Character>>(Rows);
        for(int i = 0; i != Rows; i++){
            Field.add(new ArrayList<>(Columns));
            for(int j = 0; j != Columns; j++){
                Field.get(i).add('0');
            }
        }
    }

/*
    ///Teszteléshez
    public GameBoard(ArrayList<ArrayList<Character>> field) {
        if (field == null) {
            throw new IllegalArgumentException("field cannot be null");
        }
        this.Field = field;
    }
/*
    ///Teszteléshez
    /*public GameBoard(GameBoard other) {
        int rows = other.getRows();
        int columns = other.getColumns();
        Field = new Character[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Field[row][col] = other.Field[row][col];
            }
        }
    }
*/
 

    public String stringOfRow(int row){
        char[] ret = new char[Columns];
        for(int i=0 ; i != Columns; i++){
            ret[i] = Field.get(row).get(i);
        }
        return new String(ret);
    }

    public String stringOfColumn(int column){
        char[] ret = new char[Rows];
        for(int i = 0; i != Rows; i++){
           ret[i] = Field.get(i).get(column);
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
            ret = ret.concat(Field.get(i--).get(j--).toString());
        }
        ret = reverseString(ret);
        //az ujonnan lerakott jelet nem vesszük kétszer bele
        if(row != Rows-1 && column != Columns-1) {
            i = row + 1;
            j = column + 1;
            //egyet le egyet jobbra
            while (i <= Rows-1 && j <= Columns-1) {
                ret = ret.concat(Field.get(i++).get(j++).toString());
            }
        }
        return ret;
    }

    public String stringOfIncreasingDiagonal(int row, int column){
        String ret = "";
        //egyet fel egyet jobbra
        int i = row;
        int j = column;
        while(i >= 0 && j <= Columns - 1){
            ret = ret.concat(Field.get(i--).get(j++).toString());
        }

        ret = reverseString(ret);
        //az ujonnan lerakott jelet nem vesszük kétszer bele
        if(row != Rows-1 && column != 0){
            i = row + 1;
            j = column - 1;
            //egyet le egyet balra
            while (i <= Rows -1  && j >= 0) {
              ret = ret.concat(Field.get(i++).get(j--).toString());

            }
        }
        return ret;
    }

    public int getFieldSize(){ return FieldSize;}

    public int getColumns(){return Columns;}

    public int getRows(){return Rows;}

    public int[] findPos(int i){
        int[] pos = new int[2];
        pos[0] = i / Columns;
        pos[1] = i % Columns;
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
        if(Field.get(row).get(column) != '0'){
            return false;
        } else {
            setValue(Field,row,column,Player.toChar(player));
            return true;
        }
    }

    public int positionsLeft() {
        int ret = 0;
        for(int i = 0; i != Rows; i++){
            for(int j = 0; j != Columns; j++) {
                if ( Field.get(i).get(j) == '0') ret++;
            }
        }
        return ret;
    }
}
