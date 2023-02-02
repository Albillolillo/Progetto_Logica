package com.example;
import org.logicng.formulas.*;

public class Tableaux{
    public FormulaFactory f;
    
    public Tableaux(){
        this.f= new FormulaFactory();
    }


    public boolean is_not_or(Formula[] subformulas){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.or(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2])){
                            System.out.println(op1+" + "+op2+" = "+new_end);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean is_not_and(Formula[] subformulas){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.and(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2]))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean is_impl(Formula[] subformulas){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.implication(op1,op2);
                    
                    if(new_end.equals((Formula)subformulas[size-1])){
                        System.out.println('\n'+new_end.toString()+ ':' + op1.toString()+ " + " + op2.toString());
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean is_not_impl(Formula[] subformulas){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.implication(op1,op2);
                        
                        if(new_end.equals((Formula)subformulas[size-2])){
                            System.out.println('\n'+new_end.toString()+ ':' + op1.toString()+ " + " + op2.toString());
                                return true;
                        }
                    }
                }
            }

        }
        return false;
    }

    public boolean is_and(Formula[] subformulas){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.and(op1,op2);
                    
                    if(new_end.equals((Formula)subformulas[size-1])){
                        System.out.println('\n'+new_end.toString()+ ':' + op1.toString()+ " + " + op2.toString());
                            return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean is_or(Formula[] subformulas){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.or(op1,op2);
                    
                    if(new_end.equals((Formula)subformulas[size-1])){
                        System.out.println('\n'+new_end.toString()+ ':' + op1.toString()+ " + " + op2.toString());
                            return true;
                    }
                }
            }
        }
        return false;
    }

}
        
