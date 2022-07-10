package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.ImageIcon; Moteasefane b sabab moshkeli Qader be ezafe kardn image baraye icon ha nabodm. ba arz poozesh
import javax.swing.*;

public class Menu extends JFrame {

    public Menu() throws Exception {
        super("Dictionary     ©Iman Zeraati");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mb = new JMenuBar();
        // create menu
        JMenu mnuDictionary = new JMenu("Dictionary");
        mb.add(mnuDictionary);
        mnuDictionary.setBackground(Color.lightGray);

        // options in Dictionary Menu
        JMenuItem option = new JMenuItem("Add Word...");
        option.setAccelerator( KeyStroke.getKeyStroke("F5"));
        mnuDictionary.add(option);

        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        // options in Dictionary Menu
        option = new JMenuItem("Delete Word...");
        option.setAccelerator( KeyStroke.getKeyStroke("F6"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        mnuDictionary.addSeparator();

        // options in Dictionary Menu
        option = new JMenuItem("Search Word...");
        option.setAccelerator( KeyStroke.getKeyStroke("F7"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });


        option = new JMenuItem("List Words");
        option.setAccelerator( KeyStroke.getKeyStroke("F8"));
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listWords();
            }
        });

        mnuDictionary.addSeparator();

        option = new JMenuItem("Exit");
        mnuDictionary.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        addStorageMenu(mb);
        addToolbar();
        setJMenuBar(mb);

        // load dictionary from disk
        Dic.loadFromDisk();

    }

    public void exit() {
        if (Dic.isModified()) {
            int option = JOptionPane.showConfirmDialog(Menu.this, "You have some pending changes. Do you want to write them to disk and then exit?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {  // exit after save
                Dic.saveToDisk();
                System.exit(0);
            }
            else if (option == JOptionPane.NO_OPTION) // exit without saving
            {
                System.exit(0);
            }
            // if cancel then do not exit
        } else {
            System.exit(0);
        }
    }

    public void centerToParent(JFrame parent, JFrame child) {
        Dimension pd = parent.getSize();
        Dimension cd = child.getSize();
        int x = (int) (pd.getWidth() - cd.getWidth()) / 2;
        int y = (int) (pd.getHeight() - cd.getHeight()) / 2;
        child.setLocation(x, y);

    }

    public void addWord() {
        Add w = new Add();
        centerToParent(Menu.this, w);
        w.setVisible(true);
    }

    public void deleteWord() {
        Delete w = new Delete();
        centerToParent(Menu.this, w);
        w.setVisible(true);
    }

    public void searchWord() {
        Search w = new Search();
        centerToParent(Menu.this, w);
        w.setVisible(true);
    }

    public void listWords() {
        List w = new List();
        w.setVisible(true);
        centerToParent(Menu.this, w);
    }

    public void addToolbar() {
        JToolBar tb = new JToolBar();
        JButton b = new JButton( );
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Add Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord();
            }

        });

        b = new JButton();
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Delete Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }

        });

        b = new JButton();
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Search Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }

        });


        b = new JButton();
        tb.add(b);
        b.setToolTipText("List Words");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listWords();
            }

        });

        tb.addSeparator();

        b = new JButton();
        tb.add(b);
        b.setToolTipText("Save");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dic.saveToDisk();
            }

        });

        b = new JButton();
        tb.add(b);
        b.setToolTipText("Load");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dic.loadFromDisk();
            }

        });

        getContentPane().add(tb, BorderLayout.NORTH);
    }
    public void addStorageMenu(JMenuBar mb) {

        JMenu mnuStorage = new JMenu("Storage");

        // options in Storage Menu
        JMenuItem option = new JMenuItem("Save Dictionary");
        option.setAccelerator( KeyStroke.getKeyStroke("F2"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = Dic.saveToDisk();
                if (result) {
                    JOptionPane.showMessageDialog(Menu.this, "Saved Dictionary Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Menu.this, "Could Not Save Dictionary Successfully! Error --> " + Dic.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        option = new JMenuItem("Load Dictionary");
        option.setAccelerator( KeyStroke.getKeyStroke("F3"));
        mnuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = Dic.loadFromDisk();
                if (result) {
                    JOptionPane.showMessageDialog(Menu.this, "Loaded Dictionary Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Menu.this, "Could Not Load Dictionary Successfully! Error --> " + Dic.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mb.add(mnuStorage);

    }

    public static void main(String[] args) throws Exception {

        Menu f = new Menu();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel label = new JLabel("©Iman Zeraati");
        label.setFont(new Font("Monospaced", Font.BOLD , 50));
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(1);
        label.setForeground(Color.blue);


        f.add(label);
    }

}
