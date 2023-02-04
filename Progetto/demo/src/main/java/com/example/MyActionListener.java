package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
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

        FormulaFactory f=new FormulaFactory();
        String s1=jf.tf1.getText();
        String s2=jf.tf2.getText();
        String[] ls=s1.split(";");
        LinkedHashSet<Formula> setFormulas=new LinkedHashSet<>();
        for(int i=0;i<ls.length;i++){
            try {
                setFormulas.add(f.parse(ls[i]));
            } catch (ParserException e) {
                JOptionPane.showMessageDialog(null,"Almeno una delle formule inserite nel set è errata");
                return;
            }
        }

        if(s2.length()>0){
            try {
                setFormulas.add((f.parse(s2)).negate());
            } catch (ParserException e) {
                JOptionPane.showMessageDialog(null,"La proposizione da implicare non è una formula ");
                return;
            }
        }

        Tableaux T=new Tableaux(null, setFormulas,f);
        System.out.println(setFormulas);
        
        boolean risultato=T.tableaux_algorithm(T.root);

        String tree=new String();
        TreePrinter tp=new TreePrinter(tree,T.root);
        tp.printNode();
        
        jf.ta.setText("L'algoritmo è stato eseguito correttamente! \n");
        if(risultato){
            jf.ta.append("La formula "+s2+" risulta essere implicata dal set di formule: "+s1+"\n");
        }else{
            jf.ta.append("La formula "+s2+" risulta non essere implicata dal set di formule: "+s1+"\n");
        }
        jf.ta.append(tp.ret);
        jf.clean.setEnabled(true);
        jf.invio.setEnabled(false);
    }

    public void clean(){
        jf.tf1.setText("");
        jf.tf2.setText("");
        jf.ta.setText("");
        jf.clean.setEnabled(false);
        jf.invio.setEnabled(true);
    }

}

