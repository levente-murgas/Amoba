package nhf;


import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class AmobaFrame extends JFrame implements ActionListener, MenuListener {
    private JPanel Cards;
    private CardLayout cl;
    private JPanel gameMenu;
    private int Rows;
    private int Columns;
    private int LengthToWin;
    private SavePanel savePanel;
    private JComboBox RoBSelect;
    private JComboBox CoBSelect;
    private JComboBox LTWSelect;
    private DefaultComboBoxModel<Integer> cbm;
    private GameController gc;
    private BoardFrame boardView;
    private JMenuBar MenuBar;
    private JMenu FileMenu;
    private JMenu Help;
    private JMenuItem About;
    private JMenuItem SaveAndExit;
    private JMenuItem BackToMenu;

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
        JPanel loadMenu = new JPanel();
        JPanel saveMenu = new JPanel();
        gameMenu = new JPanel();
        Cards.add(mainMenu,"main");
        Cards.add(loadMenu,"load");
        Cards.add(saveMenu,"save");
        Cards.add(gameMenu,"game");
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

        JLabel LengthToWinLabel = new JLabel("Gyozelemhez szukseges vonal hossza:");
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
        TopPanel.add(LengthToWinLabel);
        TopPanel.add(LTWSelect);

        JPanel BottomPanel = new JPanel();
        BottomPanel.add(betolt);
        BottomPanel.add(start);
        BottomPanel.add(kilepes);

        mainMenu.add(TopPanel,BorderLayout.NORTH);
        mainMenu.add(BottomPanel,BorderLayout.SOUTH);
        //
        ///loadMenu
        LoadPanel loadPanel = new LoadPanel();
        loadPanel.setLayout(new BoxLayout(loadPanel,BoxLayout.Y_AXIS));
        JLabel loads = new JLabel("Mentesek");
        JButton load1 = new JButton("Mentes1");
        load1.addActionListener(loadPanel);
        JButton load2 = new JButton("Mentes2");
        load2.addActionListener(loadPanel);
        JButton load3 = new JButton("Mentes3");
        load3.addActionListener(loadPanel);
        JButton load4 = new JButton("Mentes4");
        load4.addActionListener(loadPanel);
        JButton load5 = new JButton("Mentes5");
        load5.addActionListener(loadPanel);
        JButton backToMenuBtn = new JButton("Vissza");
        backToMenuBtn.addActionListener(e -> backToMenu());
        addAComponent(loads,loadPanel);
        addAComponent(load1,loadPanel);
        addAComponent(load2,loadPanel);
        addAComponent(load3,loadPanel);
        addAComponent(load4,loadPanel);
        addAComponent(load5,loadPanel);
        loadMenu.add(backToMenuBtn);
        loadMenu.add(loadPanel);
        //
        ///gameMenu
        gameMenu.setLayout(new BorderLayout());

        //saveMenu
        savePanel = new SavePanel();
        savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.Y_AXIS));
        JLabel jl1 = new JLabel("Mentsd ide:");
        JButton save1 = new JButton("Mentes1");
        save1.addActionListener(savePanel);
        JButton save2 = new JButton("Mentes2");
        save2.addActionListener(savePanel);
        JButton save3 = new JButton("Mentes3");
        save3.addActionListener(savePanel);
        JButton save4 = new JButton("Mentes4");
        save4.addActionListener(savePanel);
        JButton save5 = new JButton("Mentes5");
        save5.addActionListener(savePanel);
        JButton backToGameBtn = new JButton("Vissza a jatekhoz");
        backToGameBtn.addActionListener(e -> {
            boardView.setVisible(true);
            cl.show(Cards,"game");
        });
        addAComponent(jl1,savePanel);
        addAComponent(save1,savePanel);
        addAComponent(save2,savePanel);
        addAComponent(save3,savePanel);
        addAComponent(save4,savePanel);
        addAComponent(save5,savePanel);
        saveMenu.add(backToGameBtn);
        saveMenu.add(savePanel);

        ///MenuBar
        MenuBar = new JMenuBar();
        FileMenu = new JMenu("File");
        Help = new JMenu("Help");
        About = new JMenuItem("About");
        SaveAndExit = new JMenuItem("Save & Exit");
        BackToMenu = new JMenuItem("Back to Menu");
        FileMenu.add(SaveAndExit);
        FileMenu.add(BackToMenu);
        Help.add(About);
        MenuBar.add(FileMenu);
        MenuBar.add(Help);
        setJMenuBar(MenuBar);
        FileMenu.addMenuListener(this);
        About.addActionListener(e -> openWebpage("https://hu.wikipedia.org/wiki/Am%C5%91ba_(j%C3%A1t%C3%A9k)"));
        BackToMenu.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this,
                    "Biztos ki akarsz lepni mentes nelkul? A jatekallas nagyon szomoru lesz ha elveszit teged:(", "Back To Menu",
                    JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.YES_OPTION) {
                backToMenu();
                boardView.setVisible(false);
            }

        });
        SaveAndExit.addActionListener(e -> {
            cl.show(Cards,"save");
            boardView.setVisible(false);
        });

        betolt.addActionListener(e -> cl.show(Cards,"load"));

        kilepes.addActionListener(e -> System.exit(0));

        start.addActionListener(e -> {
            LengthToWin = (Integer) LTWSelect.getSelectedItem();
            Rows = (Integer) RoBSelect.getSelectedItem();
            Columns = (Integer) CoBSelect.getSelectedItem();
            gc = new GameController(this.LengthToWin,Rows,Columns);
            startGame();
        });



        getContentPane().add(Cards);

        pack();
    }

    public void startGame(){
        savePanel.setGC(gc);
        boardView = gc.getBoard();
        gameMenu.add(boardView,BorderLayout.CENTER);
        cl.show(Cards,"game");
    }

    private static void openWebpage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getCurrentCard(){
        Component[] c = Cards.getComponents();
        int i = 0;
        int j = c.length;
        while (i < j) {
            if (c[i].isVisible()) {
                break;
            }
            else
                i ++;
        }
        switch (i){
            case 0 -> {
                return "main";
            }
            case 1 -> {
                return "load";
            }
            case 2-> {
                return "save";
            }
            default -> {return "game";}
        }
    }

    public void backToMenu(){
        cl.show(Cards,"main");
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



    @Override
    public void menuSelected(MenuEvent e) {
        if(getCurrentCard().equals("game")){
            SaveAndExit.setEnabled(true);
            BackToMenu.setEnabled(true);
        }
        else{
            SaveAndExit.setEnabled(false);
            BackToMenu.setEnabled(false);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }

    public static void main(String[] args){
        AmobaFrame a = new AmobaFrame();
        a.setVisible(true);

    }
}
