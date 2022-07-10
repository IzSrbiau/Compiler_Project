package com.company;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Delete extends JFrame{
    private JTextField tfWord;
    private JButton btnDelete;

    public Delete() {
        super("Delete Word   © IZ Srbiau");

        tfWord = new JTextField(20);
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (  tfWord.getText().length() > 0 ) {
                    boolean done = Dic.delete(tfWord.getText());
                    if (!done)
                        JOptionPane.showMessageDialog( Delete.this, "Word  Not Found. Please try again!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                    else
                        JOptionPane.showMessageDialog( Delete.this, "Word  Deleted Successfully!","Delete Word", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog( Delete.this, "Please enter word from dictionary!","Add Word", JOptionPane.ERROR_MESSAGE);
            }
        }
        );

        Container c = getContentPane();
        c.setLayout( new FlowLayout());
        c.add( new JLabel("Word To Delete :"));
        c.add(tfWord);
        c.add( btnDelete);

        pack();
    }

}
