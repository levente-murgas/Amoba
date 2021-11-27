package nhf;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

/**
 * A GameEvaluator osztály metódusainak tesztosztálya.
 */
@RunWith(Parameterized.class)
public class GameEvaluatorTest {
    GameEvaluator gm;

    /**
     * Paraméteres teszteléshez konstruktor.
     * @param gm the gm
     */
    public GameEvaluatorTest(GameEvaluator gm){
        this.gm = gm;
    }

    /**
     * Tesztkörnyezet felállítása.
     */
    @Before
    public void init(){
        LoadPanel lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        gm = lp.getGc().getModel();
    }

    /**
     * checkLineStr() függvény tesztje.
     */
    @Test
    public void checkLineStrTest(){
        String checkLineStr = gm.makeCheckLineStr(GameEvaluator.Player.X);
        Assert.assertEquals("XXX",checkLineStr);
        checkLineStr = gm.makeCheckLineStr(GameEvaluator.Player.O);
        Assert.assertEquals("OOO",checkLineStr);
    }

    /**
     * columnMatches() függvény tesztje.
     */
    @Test
    public void columnMatchesTest(){
        Assert.assertTrue(gm.columnMatches(1));
        Assert.assertFalse(gm.columnMatches(0));
    }

    /**
     *  rowMatches() függvény tesztje.
     */
    @Test
    public void rowMatchesTest(){
        Assert.assertTrue(gm.columnMatches(1));
        Assert.assertFalse(gm.rowMatches(2));
    }

    /**
     * diagonalMatches() függvény tesztje.
     */
    @Test
    public void diagonalMatchesTest(){
        Assert.assertTrue(gm.diagonalMatches(1,4));
        Assert.assertTrue(gm.diagonalMatches(2,14));
        Assert.assertFalse(gm.diagonalMatches(0,0));
        ;
    }

    /**
     * Feltöltjük a GameEvaluator inicializálásához a paramétereket.
     *
     * @return paraméterek listája
     */
    @Parameterized.Parameters
    public static List<GameEvaluator> data(){
        List<GameEvaluator> data = new LinkedList<>();
        LoadPanel lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        data.add(lp.getGc().getModel());
        board = lp.loadGame(2,"\\test\\testsave");
        lp.setUpGame(board);
        data.add(lp.getGc().getModel());
        return data;
    }

}
