package nhf;

import java.util.ArrayList;

public class ArrayList2D<T> extends ArrayList<ArrayList<T>> {
    private ArrayList<ArrayList<T>> Array;
    private int rows;
    private int columns;
    private int size;

    public ArrayList2D(int rows, int columns, T filler){
        this.rows = rows;
        this.columns = columns;
        this.size = rows * columns;
        Array = new ArrayList<>(this.rows);
        for(int i = 0; i != this.rows; i++){
            Array.add(new ArrayList<>(this.columns));
            for(int j = 0; j != this.columns; j++){
                Array.get(i).add(filler);
            }
        }
    }

    public ArrayList2D(ArrayList<ArrayList<T>> a){
        rows = a.size();
        columns = a.get(0).size();
        Array = a;
    }

    public void setValue(int row, int column, T value){
        Array.get(row).set(column, value);
    }

    public ArrayList<T> get(int i){
        return Array.get(i);
    }

    public int getRows(){ return rows;}

    public int getColumns(){return columns;}

    public int getSize(){return size;}
}
