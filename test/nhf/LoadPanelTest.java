package nhf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoadPanelTest {
    LoadPanel lp;

    @Before
    public void init(){
        lp = new LoadPanel();
    }

    @Test
    public void loadGameTest(){
        String res = lp.loadGame(1,"\\test\\testsave");
        Assert.assertEquals("""
                            OXO0OX0O0O00XOO
                            0XO0XOO0XXX00XO
                            OX0X0000000000X
                            3""",res);
    }

    @Test
    public void setUpGameTest(){

    }
}
