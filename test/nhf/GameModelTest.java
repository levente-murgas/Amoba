package nhf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A GameModel osztály metódusainak tesztosztálya.
 */
public class GameModelTest {
    GameModel gb;

    /**
     *  Tesztkörnyezet felállítása.
     */
    @Before
    public void init(){
        LoadPanel lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        gb = lp.getGc().getModel().getGameBoard();
    }

    /**
     * positionsLeftTest() fv. tesztje
     */
    @Test
    public void positionsLeftTest(){
        int i = gb.positionsLeft();
        Assert.assertEquals(21,i);
    }

    /**
     * place() fv. tesztje exception dobás esetén.
     */
    @Test (expected = IllegalArgumentException.class)
    public void placeNullPlayerTest(){
        gb.place(1,null);
    }

    /**
     * place() fv. tesztje
     */
    @Test
    public void placeTest() {
        boolean res = gb.place(0, GameEvaluator.Player.X);
        Assert.assertFalse(res);
        res = gb.place(3, GameEvaluator.Player.X);
        Assert.assertTrue(res);
    }

    /**
     * reverseString() fv. tesztje
     */
    @Test
    public void reverseStrTest(){
        String s1 = "alma";
        String s2 = gb.reverseString(s1);
        Assert.assertEquals("amla",s2);
    }

    /**
     * findPos() fv. tesztje
     */
    @Test
    public void findPosTest(){
        int[] pos = gb.findPos(25);
        Assert.assertEquals(1,pos[0]);
        Assert.assertEquals(10,pos[1]);
    }

}


