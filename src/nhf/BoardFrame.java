package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nhf.AmobaGameModel.*;

public class BoardFrame extends JPanel implements ActionListener {
    int Rows;
    int Columns;
    int FieldSize;
    XOButton[] buttons;
    AmobaGameModel Ags;
    JPanel BoardPanel;
    JTextField StatusBar;

    public BoardFrame(AmobaGameModel ags){
        super();
        setLayout(new BorderLayout());

        this.Ags = ags;
        GameBoard gb = ags.getGameBoard();
        Rows = gb.getRows();
        Columns = gb.getColumns();
        FieldSize = Rows * Columns;

        BoardPanel = new JPanel();
        GridLayout gl = new GridLayout(Rows,Columns);
        BoardPanel.setLayout(gl);

        try {
            buttons = new XOButton[FieldSize];
        } catch (Exception ex){
            ex.printStackTrace();
        }
        for(int i=0; i!= FieldSize; i++){
            buttons[i] = new XOButton();
            buttons[i].addActionListener(this);
            buttons[i].setSize(50,50);
            BoardPanel.add(buttons[i]);
        }

        setPreferredSize(new Dimension(getWidth()*50,getHeight()*50));

        StatusBar = new JTextField("X jön");
        StatusBar.setHorizontalAlignment(JTextField.CENTER);
        StatusBar.setSize(50,100);
        StatusBar.setEditable(false);
        StatusBar.setBackground(null);
        StatusBar.setBorder(null);

        add(StatusBar,BorderLayout.NORTH);
        add(BoardPanel,BorderLayout.CENTER);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int pos;
        for(pos = 0; pos != FieldSize; pos++){
            if(e.getSource() == buttons[pos]) break;
        }
        if(Ags.moveMade(pos)){
            switch (Ags.getCurrentPlayer()) {
                case X -> {
                    buttons[pos].setX();
                    StatusBar.setText("O jon");
                }
                case O -> {
                    buttons[pos].setO();
                    StatusBar.setText("X jon");
                }
            }
            int status = Ags.isOver(pos);
            if(status != -1){
                gameOver(status);
            }
            Ags.switchPlayer();
        } else {
            StatusBar.setText("A mezo mar foglalt!");
        }
    }

    private void gameOver(int status) {
        if(status == 1){
            char Winner = Player.toChar(Ags.getCurrentPlayer());
            StatusBar.setText(Winner + " nyert!");
        }
        else{
            StatusBar.setText("A jatek dontetlennel ert veget.");
        }

    }
}
