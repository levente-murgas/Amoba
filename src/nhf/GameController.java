package nhf;

/**
 * A GameController az MVC hármasból a controller szerepét
 * tölti be. Összeköti a játék nézetét a nézet mögött naplózott
 * változtatásokkal.
 */
public class GameController {

    private GameEvaluator gameEvaluator;
    private BoardFrame boardFrame;

    /**
     * Inicializál egy új GameController objektumot.
     *
     * @param lengthToWin a győzelmi vonal hossza
     * @param rows        a sorok hossza
     * @param columns     az oszlopok hossza
     */
    public GameController(int lengthToWin, int rows, int columns){
        gameEvaluator = new GameEvaluator(lengthToWin,rows,columns);
        boardFrame = new BoardFrame(this);
    }


    /**
     * Inicializál egy új GameController objektumot.
     * Ennek a konstruktornak a betöltésnél van fő szerepe.
     * @param lengthToWin a győzelmi vonal hossza
     * @param b           a betöltés során megkonstruált mentett játékállás
     */
    public GameController(int lengthToWin, ArrayList2D<Character> b) {
        GameModel gb = new GameModel(lengthToWin,b);
        gameEvaluator = new GameEvaluator(lengthToWin,gb,gb.decideCurrentPlayer());
        boardFrame = new BoardFrame(this);
    }

    /**
     * Getter.
     * Odaadja a BoardFrame-et.
     * @return a BoardFrame.
     */
    public BoardFrame getBoard(){ return boardFrame;}

    /**
     * Getter.
     * Odaadja a GameModelt.
     * @return the game model
     */
    public GameEvaluator getModel(){ return gameEvaluator;}

    /**
     * A GameController levezényel egy lépést.
     * Regisztáltatja a változást a GameModellel.
     * Megkérdezi a GameEvaluatort, hogy helyes volt-e
     * a lépés és ha igen eredményezi-e a játék végét.
     * A változásokat megjelenítését kéri a BoardFrametől.
     * @param which a megnyomott gomb indexe
     */
    public void buttonPressed(int which){
        if(gameEvaluator.moveMade(which)){
            boardFrame.markField(which, gameEvaluator.getCurrentPlayer());
            int status = gameEvaluator.isOver(which);
            if(status != -1){
                boardFrame.displayGameOver(status);
            }
            gameEvaluator.switchPlayer();
        } else {
            boardFrame.invalidMove();
        }
    }

}
