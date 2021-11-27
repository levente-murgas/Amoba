package nhf;


import nhf.GameEvaluator.Player;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A GameBoard az MVC hármasból a model szerepét tölti be.
 * Ez az osztály adminisztálja a megtett lépéseket.
 */
public class GameModel {

    private ArrayList2D<Character> Board;
    private int LengthToWin;

    /**
     * Inicializál egy új GameModel objektumot.
     *
     * @param lengthToWin a győzelmi vonal hossza
     * @param columns     a sorok száma
     * @param rows        az oszlopok száma
     */
    public GameModel(int lengthToWin, int columns, int rows) {
        LengthToWin = lengthToWin;
        Board = new ArrayList2D<>(rows,columns,'0');
    }

    /**
     * Inicializál egy új GameModel
     * Ennek a konstruktornak a betöltésnél van fő szerepe.
     * @param lengthToWin a győzelmi vonal hossza
     * @param b           a betöltött játékállás
     */
    public GameModel(int lengthToWin, ArrayList2D<Character> b){
        LengthToWin = lengthToWin;
        Board = b;
    }

    /**
     * Korábbi játék betöltésekor eldönti,
     * melyik játékosnak kell folytatni a játékot először.
     *
     * @return a soron következő játékos
     */
    public Player decideCurrentPlayer(){
        int X = 0;
        int O = 0;
        for(int i = 0; i != Board.getRows(); i++){
            for(int j = 0; j != Board.getColumns(); j++){
                if(Board.get(i).get(j) == 'X') X++;
                else if(Board.get(i).get(j) == 'O') O++;
            }
        }
        Player ret;
        ret = (O < X) ? Player.O : Player.X;
        return ret;
    }


    /**
     * Visszadja, hogy milyen érték található
     * a tábla adott mezőjén.
     *
     * @param pos a tábla adott mezőjének indexei
     * @return a játékos aki áll rajta
     * @throws IllegalArgumentException túl indexelés esetén kivételt dobunk
     */
    public Character valueAt(int[] pos) throws IllegalArgumentException{
            return Board.get(pos[0]).get(pos[1]);
    }

    /**
     * A tábla adatait szöveges formába konvertáló
     * függvény. Mentéshez szükséges.
     *
     * @param w a Writer ami segítségével írunk
     * @throws IOException i/o hiba esetén kivételt dobunk
     */
    public void parseBoard(Writer w) throws IOException {
        for(int i = 0; i != Board.getRows(); i++){
            for(int j = 0; j != Board.getColumns(); j++){
                w.write(Board.get(i).get(j));
            }
            w.write("\n");
        }
        Integer i = LengthToWin;
        w.write(i.toString());
    }


    /**
     * Sztringet csinál
     * a tábla egy sorából.
     *
     * @param row az adott sor
     * @return a képezett sztring
     */
    public String stringOfRow(int row){
        char[] ret = new char[Board.getColumns()];
        for(int i = 0; i != Board.getColumns(); i++){
            ret[i] = Board.get(row).get(i);
        }
        return new String(ret);
    }

    /**
     * Sztringet csinál
     * a tábla egy oszlopából.
     *
     * @param column az adott oszlop
     * @return a képezett sztring
     */
    public String stringOfColumn(int column){
        char[] ret = new char[Board.getRows()];
        for(int i = 0; i != Board.getRows(); i++){
           ret[i] = Board.get(i).get(column);
        }
        return new String(ret);
    }


    /**
     * Megfordít egy sztringet.
     * A diagonál készítésnél van szerepe.
     * @param str megfordítandó sztring
     * @return a megfordított sztring
     */
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

    /**
     * A pozíciót érintő monoton
     * csökkenő diagonált hozza létre.
     *
     * @param row    a sorindex
     * @param column az oszlopindex
     * @return a diagonál String formájában
     */
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

    /**
     * A pozíciót érintő monoton
     * növekvő diagonált hozza létre.
     *
     * @param row    a sorindex
     * @param column az oszlopindex
     * @return a diagonál String formájában
     */
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

    /**
     * Getter.
     * Visszaadja a tábla méretét.
     * @return a pálya mérete
     */
    public int getFieldSize(){ return Board.getSize();}

    /**
     * Getter.
     * Visszaadja a tábla oszlopainak számát.
     * @return az oszlopok száma
     */
    public int getColumns(){return Board.getColumns();}

    /**
     * Getter.
     * Visszaadja a tábla sorainak számát.
     * @return a sorok száma
     */
    public int getRows(){return Board.getRows();}

    /**
     * Kiszámolja hogy az i.-dik gombhoz melyik
     * melyik pozíció tartozik a táblában.
     * @param i az i.dik gomb
     * @return a pozíció koordinátái
     */
    public int[] findPos(int i){
        int[] pos = new int[2];
        pos[0] = i / Board.getColumns();
        pos[1] = i % Board.getColumns();
        return pos;
    }

    /**
     * Adminisztrál egy lépést a táblán,
     * ha az lehetséges.
     * @param pos    a megnyomott gomb indexe
     * @param player az aktuális játékos
     * @return érvényes-e a lépés.
     */
    public boolean place(int pos, Player player) throws IllegalArgumentException{
        if (player == null) {
            throw new IllegalArgumentException("cannot mark null player");
        }
        int[] position = findPos(pos);
        if(Board.get(position[0]).get(position[1]) != '0'){
            return false;
        } else {
            Board.setValue(position[0],position[1],Player.toChar(player));
            return true;
        }
    }

    /**
     * Visszaadja hány szabad mező maradt még a pályán.
     *
     * @return a szabad mezők száma
     */
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
