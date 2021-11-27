package nhf;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class GameModelTest {
    GameModel gm;

    public GameModelTest(GameModel gm){
        this.gm = gm;
    }

    @Before
    public void init(){
        LoadPanel lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        gm = lp.getGc().getModel();
    }

    @Test
    public void checkLineStrTest(){
        String checkLineStr = gm.makeCheckLineStr(GameModel.Player.X);
        Assert.assertEquals("XXX",checkLineStr);
        checkLineStr = gm.makeCheckLineStr(GameModel.Player.O);
        Assert.assertEquals("OOO",checkLineStr);
    }

    @Test
    public void columnMatchesTest(){
        Assert.assertTrue(gm.columnMatches(1));
        Assert.assertFalse(gm.columnMatches(0));
    }

    @Test
    public void rowMatchesTest(){
        Assert.assertTrue(gm.columnMatches(1));
        Assert.assertFalse(gm.rowMatches(2));
    }

    @Test
    public void diagonalMatchesTest(){
        Assert.assertTrue(gm.diagonalMatches(1,4));
        Assert.assertTrue(gm.diagonalMatches(2,14));
        Assert.assertFalse(gm.diagonalMatches(0,0));
        ;
    }

    @Parameterized.Parameters
    public static List<GameModel> data(){
        List<GameModel> data = new LinkedList<>();
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
