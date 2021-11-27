package nhf;

import javax.swing.*;
import java.awt.*;


/**
 * A játékmezőnek megfelelő XOButton osztály.
 * A JButton osztály leszármazottja.
 */
public class XOButton extends JButton  {
    private ImageIcon Xbutton,Obutton;

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(50,50);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(50,50);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50,50);

    }

    /**
     * Inicializál egy XOButton objektumot.
     *
     * @throws NullPointerException the null pointer exception
     */
    public XOButton() throws NullPointerException{
        setMinimumSize(new Dimension(50,50));
        setSize(50,50);
        setPreferredSize(new Dimension(50,50));
        setMaximumSize(new Dimension(50,50));
        Xbutton = new ImageIcon(this.getClass().getResource("X.png"));
        Obutton = new ImageIcon(this.getClass().getResource("O.png"));
    }

    /**
     * A gomb ikonját "X"-re állítja.
     */
    public void setX(){setIcon(Xbutton);}

    /**
     * A gomb ikonját "O"-ra állítja.
     */
    public void setO(){setIcon(Obutton);}
}
