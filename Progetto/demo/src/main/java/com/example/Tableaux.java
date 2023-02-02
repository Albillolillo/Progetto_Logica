package com.example;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.logicng.formulas.*;
import org.logicng.functions.*;

public class Tableaux{
    private static FormulaFactory f;
    
    public Tableaux(){
        this.f= new FormulaFactory();
    }

   
    //dato un array di sottoformule ritorna
    // true se due formule in and tra loro sono uguali a f1
    static boolean is_and(Formula f1,Formula[] subformulas){
        int size= subformulas.length;

        


        return false;
       
    }

    static boolean is_not_and(Formula[] subformulas){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=f.and(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2]))
                            return true;
                    }
                }
            }

        }

        return false;
    }
    static boolean is_not_or(Formula f1,Formula[] subformulas){


        return false;
    }
    static boolean is_not_not(Formula f1,Formula[] subformulas){


        return false;
    }
    static boolean is_or(Formula f1,Formula[] subformulas){


        return false;
    }


public static void main( String[] args ){
        Tableaux t= new Tableaux();

        
        Variable a =f.variable("A");
        Variable b = f.variable("B");
        Literal notC =f.literal("C", false);
        Formula formula =f.not(f.and(a, f.not(f.or(b, notC))));

       
       
        LinkedHashSet<Formula> subFormulas = formula.apply(new SubNodeFunction());
        Formula[] subform_array = new Formula[subFormulas.size()];
        subform_array= subFormulas.toArray(subform_array);
        
        System.out.println( is_not_and(subform_array));
}
}      
    


        
