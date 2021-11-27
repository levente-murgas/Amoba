package nhf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *  A LoadPanel osztály metódusának tesztosztálya.
 */
public class LoadPanelTest {
    LoadPanel lp;

    /**
     * Tesztkörnyezet előkészítése.
     */
    @Before
    public void init(){
        lp = new LoadPanel();
    }

    /**
     * loadGame() fv. tesztje
     */
    @Test
    public void loadGameTest(){
        String res = lp.loadGame(1,"\\test\\testsave");
        Assert.assertEquals("""
                            OXO0OX0O0O00XOO
                            0XO0XOO0XXX00XO
                            OX0X0000000000X
                            3""",res);
        res = lp.loadGame(2,"\\test\\testsave");
        Assert.assertEquals("""
                            XOX00O000000O00
                            0O0XOX0XOOO00OX
                            XOXOX0X0X0X0X0O
                            3""",res);
    }


}
