package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.logicng.formulas.*;
import org.logicng.io.parsers.ParserException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MyActionListener implements ActionListener{
    private MyJFrame jf;

    public MyActionListener(MyJFrame jf){
        this.jf=jf;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource().getClass().equals(JButton.class)) {
            JButton btn = (JButton) actionEvent.getSource();
            switch (btn.getText()) {
                case "Invio":
                    invio();
                    break;
                case "Clean":
                    clean();
                    break;
            }
        }
    }
    public void invio(){
        String s=jf.tf.getText();
        FormulaFactory f=new FormulaFactory();
        try {
            Formula f1=f.parse(s);
            jf.ta.setText(s);
            jf.clean.setEnabled(true);
            jf.invio.setEnabled(false);
        } catch (ParserException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"La proposizione inserita non è una formula ");
        }
        jf.tf.setText("");
    }
    public void clean(){
        jf.ta.setText("");
        jf.clean.setEnabled(false);
        jf.invio.setEnabled(true);
    }
}
