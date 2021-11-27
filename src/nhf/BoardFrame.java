package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nhf.GameModel.*;

public class BoardFrame extends JPanel implements ActionListener {
    int Rows;
    int Columns;
    int FieldSize;
    XOButton[] buttons;
    GameController gc;
    JPanel BoardPanel;
    JTextField StatusBar;

    public BoardFrame(GameController gc){
        super();
        setLayout(new BorderLayout());
        this.gc = gc;
        Rows = gc.getModel().getGameBoard().getRows();
        Columns = gc.getModel().getGameBoard().getColumns();
        FieldSize = Rows * Columns;

        BoardPanel = new JPanel();
        GridLayout gl = new GridLayout(Rows,Columns);
        BoardPanel.setLayout(gl);

        try {
            buttons = new XOButton[FieldSize];
        } catch (Exception ex){
            ex.printStackTrace();
        }
        int[] pos;
        for(int i=0; i!= FieldSize; i++){
            pos = gc.getModel().getGameBoard().findPos(i);
            buttons[i] = new XOButton();
            buttons[i].addActionListener(this);
            if(gc.getModel().getGameBoard().valueAt(pos[0],pos[1]).equals('X')) buttons[i].setX();
            else if(gc.getModel().getGameBoard().valueAt(pos[0],pos[1]).equals('O')) buttons[i].setO();
            buttons[i].setSize(50,50);
            BoardPanel.add(buttons[i]);
        }

        setPreferredSize(new Dimension(getWidth()*50,getHeight()*50));
        char next = Player.toChar(gc.getModel().getCurrentPlayer());
        StatusBar = new JTextField(next + "'s turn");
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
        gc.buttonPressed(pos);
    }

    public void markField(int which, Player player){
        switch (player) {
            case X -> {
                buttons[which].setX();
                StatusBar.setText("O's turn");
            }
            case O -> {
                buttons[which].setO();
                StatusBar.setText("X's turn");
            }
        }
    }

    public void displayGameOver(int status) {
        if(status == 1){
            char Winner = Player.toChar(gc.getModel().getCurrentPlayer());
            StatusBar.setText(Winner + " won!");
        }
        else{
            StatusBar.setText("Game ended in draw.");
        }
        Object[] option={"Back to Main Menu"};
        int n=JOptionPane.showOptionDialog(getParent(), "Game Over\n","Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if (n == 0){
            JFrame f1 = (JFrame) SwingUtilities.windowForComponent(this);
            AmobaFrame topFrame = (AmobaFrame) f1;
            topFrame.backToMenu();
            setVisible(false);
        }
    }

    public void invalidMove() {
        StatusBar.setText("The field is already taken!");
    }

    public String getStatusBar(){
        return StatusBar.getText();
    }
}
