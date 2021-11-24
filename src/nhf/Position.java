package nhf;

public class Position {

    private int row;
    private int column;

    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Position(int[] t){
        row = t[0];
        column = t[1];
    }

    public int getRow(){return row;}

    public int getColumn(){return column;}


}
