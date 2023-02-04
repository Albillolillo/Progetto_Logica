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
    JTextField tf1,tf2;
    JTextArea ta;
    JLabel l1,l2;
    JScrollPane sp;
    JButton invio,clean;
    MyActionListener listener=new MyActionListener(this);
    public MyJFrame(){
        p=new JPanel();
        tf1=new JTextField(45);
        l1=new JLabel("Scrivere insieme formule proposizionali separate da ';'");
        tf2=new JTextField(30);
        l2=new JLabel("Scrivere formula da implicare");
        ta=new JTextArea(20,40 );
        ta.setEditable(false);
        sp=new JScrollPane(ta);
        invio=new JButton("Invio");
        invio.addActionListener(listener);
        clean=new JButton("Clean");
        clean.addActionListener(listener);
        clean.setEnabled(false);
        p.add(l1);
        p.add(tf1);
        p.add(l2);
        p.add(tf2);
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