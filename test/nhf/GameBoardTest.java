package nhf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
    GameBoard gb;

    @Before
    public void init(){
        LoadPanel lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        gb = lp.gc.getModel().getGameBoard();
    }

    @Test
    public void positionsLeftTest(){
        int i = gb.positionsLeft();
        Assert.assertEquals(21,i);
    }

    @Test (expected = IllegalArgumentException.class)
    public void placeNullPlayerTest(){
        gb.place(1,null);
    }

    @Test
    public void placeTest() {
        boolean res1 = gb.place(0, GameModel.Player.X);
        Assert.assertFalse(res1);
        boolean res2 = gb.place(3, GameModel.Player.X);
        Assert.assertTrue(res2);
    }

    @Test
    public void reverseStrTest(){
        String s1 = "alma";
        String s2 = gb.reverseString(s1);
        Assert.assertEquals("amla",s2);
    }

}


