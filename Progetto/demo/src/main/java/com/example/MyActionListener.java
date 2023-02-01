package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.logicng.formulas.*;
import org.logicng.io.parsers.ParserException;
import org.logicng.solvers.MiniSat;
import org.logicng.solvers.SATSolver;

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
            SATSolver solver = MiniSat.miniSat(f);
            solver.add(f1);

            jf.ta.setText(s+"\n"+"La formula inserita ha:\n-"
                +f1.numberOfAtoms()+" atomi\n-"
                +f1.numberOfOperands()+" operandi\n-"
                +f1.numberOfNodes()+" nodi\n-"
                +f1.numberOfInternalNodes()+" nodi interni\n"
                +"La formula è soddisfacibile:"+f1.isSatisfiable()
                +"\nun possibile modello è: "+solver.enumerateAllModels().iterator().next()
                );
            
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
