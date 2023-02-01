package com.example;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyJFrame extends JFrame{
    JPanel p;
    JTextField tf;
    JTextArea ta;
    JLabel l;
    JScrollPane sp;
    JButton invio,clean;
    MyActionListener listener=new MyActionListener(this);
    public MyJFrame(){
        p=new JPanel();
        tf=new JTextField(30);
        l=new JLabel("Scrivere formula proposizionale");
        ta=new JTextArea(30,40 );
        ta.setEditable(false);
        ta.setBounds(100,100,300,200);
        sp=new JScrollPane(ta);
        invio=new JButton("Invio");
        invio.addActionListener(listener);
        clean=new JButton("Clean");
        clean.addActionListener(listener);
        clean.setEnabled(false);
        p.add(l);
        p.add(tf);
        p.add(invio);
        p.add(clean);
        p.add(sp);
        getContentPane().add(p);

        setTitle("Tableaux solver");
        setVisible(true);
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}