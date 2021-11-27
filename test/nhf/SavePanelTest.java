package nhf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SavePanelTest {
    SavePanel sp;
    LoadPanel lp;
    @Before
    public void init(){
        lp = new LoadPanel();
        String board = lp.loadGame(1,"\\test\\testsave");
        lp.setUpGame(board);
        sp = new SavePanel();
        sp.setGC(lp.getGc());
    }


    @Test
    public void saveGameTest(){
        sp.saveGame(3,"\\test\\testsave");
        String res = lp.loadGame(3,"\\test\\testsave");
        Assert.assertEquals("""
                            OXO0OX0O0O00XOO
                            0XO0XOO0XXX00XO
                            OX0X0000000000X
                            3""",res);
    }
}
