package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;

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

        FormulaFactory f=new FormulaFactory();
        String s1=jf.tf1.getText();
        String s2=jf.tf2.getText();
        String[] ls=s1.split(";");
        LinkedHashSet<Formula> setFormulas=new LinkedHashSet<>();
        for(int i=0;i<ls.length;i++){
            try {
                setFormulas.add(f.parse(ls[i]));
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }

        try {
            setFormulas.add((f.parse(s2)).negate());
        } catch (ParserException e) {
            e.printStackTrace();
        }

        Tableaux T=new Tableaux(null, setFormulas,f);

        System.out.println(setFormulas);
        
        try {
            Formula f1=T.f.parse(s2);
            //SATSolver solver = MiniSat.miniSat(f);
            //solver.add(f1);

            LinkedHashSet<Formula> figli=new LinkedHashSet<>();
            String operator=T.what(f1,figli);


            jf.ta.setText(s2+"\n"+"La formula inserita ha:\n-"
                +f1.numberOfAtoms()+" atomi\n-"
                +f1.numberOfOperands()+" operandi\n-"
                +f1.numberOfNodes()+" nodi\n-"
                +f1.numberOfInternalNodes()+" nodi interni\n"
                /*+"La formula è soddisfacibile:"+f1.isSatisfiable()
                +"\nun possibile modello è: "+solver.enumerateAllModels().iterator().next()*/
                +"\nle due formule "+figli.toString()+"sono legate da "+operator
                );

            jf.clean.setEnabled(true);
            jf.invio.setEnabled(false);
        } catch (ParserException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"La proposizione inserita non è una formula ");
        }
        
    }
    public void clean(){
        jf.tf1.setText("");
        jf.tf2.setText("");
        jf.ta.setText("");
        jf.clean.setEnabled(false);
        jf.invio.setEnabled(true);
    }

}

