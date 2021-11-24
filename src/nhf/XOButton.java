package nhf;

import javax.swing.*;
import java.awt.*;


public class XOButton extends JButton  {
    ImageIcon Xbutton,Obutton;


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

    public XOButton() throws NullPointerException{
        setMinimumSize(new Dimension(50,50));
        setSize(50,50);
        setPreferredSize(new Dimension(50,50));
        setMaximumSize(new Dimension(50,50));
        Xbutton = new ImageIcon(this.getClass().getResource("X.png"));
        Obutton = new ImageIcon(this.getClass().getResource("O.png"));
    }

    public void setX(){setIcon(Xbutton);}

    public void setO(){setIcon(Obutton);}



}
