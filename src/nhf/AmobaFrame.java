package nhf;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AmobaFrame extends JFrame implements ActionListener {
    private JPanel Cards;
    private CardLayout cl;
    private JPanel GameMenu;
    private BoardFrame boardView;
    private int Rows;
    private int Columns;
    private int LengthToWin;
    private JComboBox RoBSelect;
    private JComboBox CoBSelect;
    private JComboBox LTWSelect;
    private DefaultComboBoxModel<Integer> cbm;

    private int currentCard = 1;
    private AmobaGameModel ags;

    public AmobaFrame() {
        Cards = new JPanel();
        cl = new CardLayout();
        Cards.setLayout(cl);
        Rows = 3;
        Columns = 3;
        LengthToWin = 3;

        setTitle("Amoba");
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainMenu = new JPanel(new BorderLayout());
        JPanel saveMenu = new JPanel();
        GameMenu = new JPanel();
        Cards.add(mainMenu,"main");
        Cards.add(saveMenu,"save");
        Cards.add(GameMenu,"game");

        Integer[] sizes = {3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
                13, 14, 15, 16, 17, 18, 19, 20};

        //MAINMENU
        JLabel RowsOfBoard = new JLabel("Palya magassaga:");
        RoBSelect = new JComboBox(sizes);
        RoBSelect.setSelectedItem(3);
        RoBSelect.setSelectedIndex(0);
        RoBSelect.setEditable(false);
        RoBSelect.addActionListener(this);

        JLabel ColumnsOfBoard = new JLabel("Palya szelessege:");
        CoBSelect = new JComboBox(sizes);
        CoBSelect.setSelectedItem(3);
        CoBSelect.addActionListener(this);
        CoBSelect.setSelectedIndex(0);
        CoBSelect.setEditable(false);

        JLabel LengthToWin = new JLabel("Gyozelemhez szukseges vonal hossza:");
        cbm = new DefaultComboBoxModel<>();
        cbm.addElement(3);
        cbm.setSelectedItem(3);
        LTWSelect = new JComboBox(cbm);
        LTWSelect.setSelectedIndex(0);

        JButton betolt = new JButton("Betolt");
        JButton start = new JButton("Start");
        JButton kilepes = new JButton("Kilepes");

        JPanel TopPanel = new JPanel();
        TopPanel.add(RowsOfBoard);
        TopPanel.add(RoBSelect);
        TopPanel.add(ColumnsOfBoard);
        TopPanel.add(CoBSelect);
        TopPanel.add(LengthToWin);
        TopPanel.add(LTWSelect);

        JPanel BottomPanel = new JPanel();
        BottomPanel.add(betolt);
        BottomPanel.add(start);
        BottomPanel.add(kilepes);

        betolt.addActionListener(e -> cl.show(Cards,"save"));

        kilepes.addActionListener(e -> System.exit(0));

        start.addActionListener(e -> {
            ags = new AmobaGameModel((Integer) LTWSelect.getSelectedItem(),
                    (Integer) RoBSelect.getSelectedItem(), (Integer) CoBSelect.getSelectedItem());
            boardView = new BoardFrame(ags);
            GameMenu.add(boardView,BorderLayout.CENTER);
            cl.show(Cards,"game");
        });

        mainMenu.add(TopPanel,BorderLayout.NORTH);
        mainMenu.add(BottomPanel,BorderLayout.SOUTH);
        //
        ///SaveMenu
        JPanel savesPanel = new JPanel();
        savesPanel.setLayout(new BoxLayout(savesPanel,BoxLayout.Y_AXIS));
        JLabel saves = new JLabel("Mentesek");
        JButton save1 = new JButton("Mentes1");
        JButton save2 = new JButton("Mentes2");
        JButton save3 = new JButton("Mentes3");
        JButton save4 = new JButton("Mentes4");
        JButton save5 = new JButton("Mentes5");
        JButton back = new JButton("Vissza");
        back.addActionListener(e -> cl.show(Cards,"main"));
        addAComponent(saves,savesPanel);
        addAComponent(save1,savesPanel);
        addAComponent(save2,savesPanel);
        addAComponent(save3,savesPanel);
        addAComponent(save4,savesPanel);
        addAComponent(save5,savesPanel);
        saveMenu.add(back);
        saveMenu.add(savesPanel);
        //
        ///GameMenu
        GameMenu.setLayout(new BorderLayout());

        getContentPane().add(Cards);

        pack();
    }



    private static void addAComponent(JComponent c, Container container) {
        c.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        try {
            if (cb == RoBSelect) {
                Rows = (Integer) cb.getSelectedItem();
            } else if (cb == CoBSelect) {
                Columns = (Integer) cb.getSelectedItem();
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        if (Columns < LengthToWin || Rows < LengthToWin) {
            LengthToWin = Math.min(Rows, Columns);
            for (int i = cbm.getSize()-1; i != cbm.getIndexOf(LengthToWin); --i) {
                cbm.removeElementAt(i);
            }
            if (LTWSelect.getSelectedIndex() > LengthToWin) {
                LTWSelect.setSelectedIndex(cbm.getSize() - 1);
            }
        } else if (Rows > LengthToWin && Columns > LengthToWin) {
            LengthToWin = Math.min(Rows, Columns);
            int last = cbm.getElementAt(cbm.getSize()-1);
            for(int i = last + 1; i <= LengthToWin; i++){
                cbm.addElement(i);
            }
        }
        if (cb == LTWSelect) {
            LengthToWin = (Integer) cb.getSelectedItem();
        }
    }

    public static void main(String[] args){
        AmobaFrame a = new AmobaFrame();
        a.setVisible(true);

    }
}
