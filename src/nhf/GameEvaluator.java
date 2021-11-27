package nhf;


/**
 * A játék végének ellenőrzéséért felelős osztály.
 */
public class GameEvaluator {
    /**
     * Játékos szerepe enumeráció.
     */
    public  enum Player {
        O,
        X;

        /**
         * Visszaadja az ellenfél Játékost.
         * A körök váltásánál van szerepe.
         * @param player aktuális játékos
         * @return a másik játékos (aki következik)
         */
        public static Player opponentOf(Player player) {
            return player == X ? O : X;
        }

        /**
         * Az enumerációt char típussá alakító függvény.
         *
         * @param player the player
         * @return the char
         */
        public static char toChar(Player player){
            return player == X ? 'X' : 'O';
        }
    }
    private GameModel Board;
    private Player CurrentPlayer;
    private int LengthToWin;

    /**
     * Inicializál egy új GameEvaluator objektumot.
     *
     * @param lengthToWin a győzelmi vonal hossza
     * @param rows        a sorok száma
     * @param columns     az oszlopok száma
     */
    public GameEvaluator(int lengthToWin, int rows, int columns){
        Board = new GameModel(lengthToWin,columns,rows);
        LengthToWin = lengthToWin;
        CurrentPlayer = Player.X;
    }

    /**
     * Inicializál egy új GameEvaluator objektumot.
     * Ennek a konstruktornak a betöltésnél van fő szerepe.
     * @param lengthToWin a győzelmi vonal hossza
     * @param gb          a GameModel objektum
     * @param player      a kezdő játékos
     */
    public GameEvaluator(int lengthToWin, GameModel gb, Player player){
        LengthToWin = lengthToWin;
        Board = gb;
        CurrentPlayer = player;
    }

    /**
     * Létrehozza a győzelmi sztringet mindig az éppen
     * lépő játékos jelével.
     * @param toCheck a lépő játékos
     * @return a győzelmi sztring
     */
    public String makeCheckLineStr(Player toCheck){
        char[] checkLine = new char[LengthToWin];
        for(int i = 0; i != LengthToWin; i++) checkLine[i] = Player.toChar(toCheck);
        return new String(checkLine);
    }

    /**
     * Megnézi, hogy abból a sorból alkotott sztringben,
     * amibe az új bábu került benne van-e a győzelmi
     * sztring, ha igen igazzal, ha nem, hamissal tér
     * vissza.
     * @param row a sor indexe amibe a bábu került
     * @return a kifejezés igazságértéke
     */
    public boolean rowMatches(int row) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String RowToCheck = Board.stringOfRow(row);
        return RowToCheck.contains(checkLineString);
    }


    /**
     * Megnézi, hogy abból az oszlopból alkotott sztringben,
     * amibe az új bábu került benne van-e a győzelmi
     * sztring, ha igen igazzal, ha nem, hamissal tér
     * vissza.
     * @param column az oszlop indexe amibe a bábu került
     * @return a kifejezés igazságértéke
     */
    public boolean columnMatches(int column) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String ColumnToCheck = Board.stringOfColumn(column);
        return ColumnToCheck.contains(checkLineString);
    }


    /**
     * Megnézi, hogy azokból a diagonálokból alkotott sztringben,
     * amikbe az új bábu került benne van-e a győzelmi
     * sztring, ha igen igazzal, ha nem, hamissal tér
     * vissza.
     * @param row a sorindex
     * @param column az oszlopindex
     * @return a kifejezés igazságértéke
     */
    public boolean diagonalMatches(int row, int column) {
        String checkLineString = makeCheckLineStr(CurrentPlayer);
        String incDiagonal = Board.stringOfIncreasingDiagonal(row,column);
        String decDiagonal = Board.stringOfDecreasingDiagonal(row,column);
        return (incDiagonal.contains(checkLineString) || decDiagonal.contains(checkLineString));
    }

    /**
     * Átvált a következő játékosra.
     */
    public void switchPlayer(){
        CurrentPlayer = Player.opponentOf(CurrentPlayer);
    }

    /**
     * Ellenőrzi, hogy a három nyerési
     * lehetőség közül legalább egy, teljesül-e.
     * Ha igen igazzal, ha nem, hamissal tér vissza.
     * @param pos a megnyomott gomb indexe
     * @return a kifejezés igazságértéke
     */
    public boolean hasWin(int pos){
        int[] position = Board.findPos(pos);
        int row = position[0];
        int column = position[1];
        return (rowMatches(row) || columnMatches(column) || diagonalMatches(row,column));
    }

    /**
     * Eldönti, hogy a játék véget ért-e,
     * és ha igen, akkor milyen végkimenetellel.
     *
     * @param pos a megnyomott gomb indexe
     * @return = 1 - az aktuális játékos nyert
     *         = 0 - döntetlen
     *         = -1 - még nincs vége a játéknak.
     */
    public int isOver(int pos) {
        if(hasWin(pos))
            return 1;
        else if (Board.positionsLeft() == 0)
            return 0;
        else
            return -1;
    }


    /**
     * Getter.
     * Visszadja a GameModelt.
     *
     * @return a GameModel
     */
    public GameModel getGameBoard() {
        return Board;
    }

    /**
     * Ha a lépés valid volt igazzal,
     * ha nem, hamissal tér vissza.
     *
     * @param pos a megnyomott gomb indexe.
     * @return a kifejezés igazságértéke.
     */
    public boolean moveMade(int pos) {
        return Board.place(pos, CurrentPlayer);
    }

    /**
     * Getter.
     * Visszaadja az aktuális játékost.
     * @return az aktuális játékos.
     */
    public Player getCurrentPlayer() { return CurrentPlayer;}
}
