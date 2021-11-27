package nhf;

import java.util.ArrayList;

/**
 * 2D-s tömb osztály az gyűjtemény keretrendszerbeli
 * ArrayList osztály felhasználásával.
 * Generikus osztály.
 * Ebben az adatstuktúrában tároljuk a táblát.
 *
 * @param <T> a paraméter típusa
 */
public class ArrayList2D<T> extends ArrayList<ArrayList<T>> {
    private ArrayList<ArrayList<T>> Array;
    private int rows;
    private int columns;
    private int size;

    /**
     * Inicializál egy 2D-s tömböt.
     *
     * @param rows    sorok száma
     * @param columns oszlopok száma
     * @param filler  karakter a feltöltéshez
     */
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

    /**
     * Inicializál egy 2D-s tömböt egy ArrayListek-et
     * tároló ArrayList objektumból.
     * A betöltésben van szerepe.
     * @param a az ArrayListeket tároló ArrayList.
     */
    public ArrayList2D(ArrayList<ArrayList<T>> a){
        rows = a.size();
        columns = a.get(0).size();
        Array = a;
    }

    /**
     * Beállítja a paraméterként kapott értékre
     * a tömb egy elemét
     * @param row    a sorindex
     * @param column az oszlopindex
     * @param value  az új érték
     */
    public void setValue(int row, int column, T value){
        Array.get(row).set(column, value);
    }

    /**
     * Visszadja a tömb egy sorát.
     * @param i a lekérdezett sor indexe
     * @return a lekérdezett sor
     */
    public ArrayList<T> get(int i){
        return Array.get(i);
    }

    /**
     * Visszaadja a sorok számát.
     *
     * @return a sorok száma.
     */
    public int getRows(){ return rows;}

    /**
     * Visszaadja az oszlopok számát.
     *
     * @return az oszlopok száma.
     */
    public int getColumns(){return columns;}

    /**
     * Visszaadja a 2D-s tömb méretét.
     *
     * @return a tömb mérete.
     */
    public int getSize(){return size;}
}
