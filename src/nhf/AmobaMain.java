package nhf;

import javax.swing.*;

/**
 * A program az AmobaMain osztályból indul.
 */
public class AmobaMain {

    /**
     * Main.
     *
     * @param args az argumentumok
     */
    public static void main(String[] args){
        AmobaFrame af = new AmobaFrame();
        af.setExtendedState(JFrame.MAXIMIZED_BOTH);
        af.setVisible(true);
    }
}
