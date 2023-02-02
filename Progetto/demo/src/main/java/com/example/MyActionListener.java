package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.regex.Pattern;

import org.logicng.formulas.*;
import org.logicng.functions.SubNodeFunction;
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
        Tableaux T=new Tableaux();

        String s1=jf.tf1.getText();
        String s2=jf.tf2.getText();
        String[] ls=s1.split(";");
        LinkedHashSet<Formula> setFormulas=new LinkedHashSet<>();
        for(int i=0;i<ls.length;i++){
            try {
                setFormulas.add(T.f.parse(ls[i]));
            } catch (ParserException e) {
                e.printStackTrace();
            }
        }

        try {
            setFormulas.add((T.f.parse(s2)).negate());
        } catch (ParserException e) {
            e.printStackTrace();
        }


        System.out.println(setFormulas);
        
        try {
            Formula f1=T.f.parse(s2);
            SATSolver solver = MiniSat.miniSat(T.f);
            solver.add(f1);

            LinkedHashSet<Formula> subFormulas = f1.apply(new SubNodeFunction());
            Formula[] subform_array = new Formula[subFormulas.size()];
            subform_array= subFormulas.toArray(subform_array);

            jf.ta.setText(s2+"\n"+"La formula inserita ha:\n-"
                +f1.numberOfAtoms()+" atomi\n-"
                +f1.numberOfOperands()+" operandi\n-"
                +f1.numberOfNodes()+" nodi\n-"
                +f1.numberOfInternalNodes()+" nodi interni\n"
                +"La formula è soddisfacibile:"+f1.isSatisfiable()
                +"\nun possibile modello è: "+solver.enumerateAllModels().iterator().next()
                +"\nla formula è concatenazione negata di due formule in &:"+T.is_not_and(subform_array)
                +"\nla formula è concatenazione negata di due formule in |:"+T.is_not_or(subform_array)
                +"\nla formula è concatenazione di due formule in ->:"+T.is_impl(subform_array)
                +"\nla formula è concatenazione negata di due formule in ->:"+T.is_not_impl(subform_array)
                +"\nla formula è concatenazione di due formule in &:"+T.is_and(subform_array)
                +"\nla formula è concatenazione di due formule in |:"+T.is_or(subform_array)
                );

            jf.clean.setEnabled(true);
            //jf.invio.setEnabled(false);
        } catch (ParserException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"La proposizione inserita non è una formula ");
        }
        //jf.tf1.setText("");
    }
    public void clean(){
        jf.ta.setText("");
        jf.clean.setEnabled(false);
        jf.invio.setEnabled(true);
    }

}

