package com.example;
import java.util.LinkedHashSet;

import org.logicng.formulas.*;
import org.logicng.functions.SubNodeFunction;

public class Tableaux{
    public FormulaFactory f;
    public Node root;
    
    public Tableaux(LinkedHashSet<Formula> path,LinkedHashSet<Formula> current,FormulaFactory f){
        this.f= f;
        this.root=new Node(path,current);
    }


    public String is_not_or(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.or(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2])){
                            ret.add(op1);
                            ret.add(op2);
                            return "is_not_or";
                        }
                    }
                }
            }
        }
        return null;
    }

    public String is_not_and(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.and(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2])){
                            ret.add(op1);
                            ret.add(op2);
                            return "is_not_and";
                        }
                    }
                }
            }
        }
        return null;
    }

    public String is_impl(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.implication(op1,op2);
                    if(new_end.equals((Formula)subformulas[size-1])){
                        ret.add(op1);
                        ret.add(op2);
                        return "is_impl";
                    }
                }
            }
        }
        return null;
    }

    public String is_not_impl(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        if(subformulas[size-1].numberOfAtoms() == subformulas[size-2].numberOfAtoms()){
            for(int i=0; i < size-1;i++){
                for(int j=i+1 ; j < size-1;j++){
                    if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                        Formula op1= subformulas[i];
                        Formula op2= subformulas[j];
                        Formula new_end=this.f.implication(op1,op2);
                        if(new_end.equals((Formula)subformulas[size-2])){
                            ret.add(op1);
                            ret.add(op2);
                            return "is_not_impl";
                        }
                    }
                }
            }

        }
        return null;
    }

    public String is_and(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.and(op1,op2);
                    
                    if(new_end.equals((Formula)subformulas[size-1])){
                        ret.add(op1);
                        ret.add(op2);
                        return "is_and";
                    }
                }
            }
        }
        return null;
    }

    public String is_or(Formula[] subformulas,LinkedHashSet<Formula> ret){
        int size= subformulas.length;
        for(int i=0; i < size-1;i++){
            for(int j=i+1 ; j < size-1;j++){
                if(subformulas[i].numberOfAtoms()+subformulas[j].numberOfAtoms() == subformulas[size-1].numberOfAtoms()){
                    Formula op1= subformulas[i];
                    Formula op2= subformulas[j];
                    Formula new_end=this.f.or(op1,op2);
                    
                    if(new_end.equals((Formula)subformulas[size-1])){
                        ret.add(op1);
                        ret.add(op2);
                        return "is_or";
                    }
                }
            }
        }
        return null;
    }

    public String what(Formula f,LinkedHashSet<Formula> ret){
        LinkedHashSet<Formula> subFormulas = f.apply(new SubNodeFunction());
        Formula[] subform_array = new Formula[subFormulas.size()];
        subform_array= subFormulas.toArray(subform_array);
        if(this.is_and(subform_array, ret) != null){return this.is_and(subform_array, ret);}
        if(this.is_not_and(subform_array, ret) != null){return this.is_not_and(subform_array, ret);}
        if(this.is_or(subform_array, ret) != null){return this.is_or(subform_array, ret);}
        if(this.is_not_or(subform_array, ret) != null){return this.is_not_or(subform_array, ret);}
        if(this.is_impl(subform_array, ret) != null){return this.is_impl(subform_array, ret);}
        if(this.is_not_impl(subform_array, ret) != null){return this.is_not_impl(subform_array, ret);}
        return null;
    }
    public boolean tableaux_algorithm(Node root,Formula f){
        if(root.left.closed == true)
            return true;
        
        LinkedHashSet<Formula> operands= new LinkedHashSet<>();
        
        switch (what(f,operands)) {
            case "is_not_and":
                LinkedHashSet<Formula> path = (LinkedHashSet<Formula>)root.path.clone();
                for(Formula i:root.current){
                    path.add(i);
                }
                Formula[] subform_array = new Formula[2];
                subform_array= operands.toArray(subform_array);
                LinkedHashSet<Formula> current= new LinkedHashSet<Formula>();
                current.add(subform_array[0].negate());
                Node left=new Node(path,current);
                
                
                break;
            case "is_not_or":
                break;
            case "is_not_impl":

                break;
            case "is_or":

                break;
            case "is_not":

                break;
            case "is_impl":

                break;
        
            default:
                return false;
        }




        return false;
    }

}
class Node {
    public LinkedHashSet<Formula> path;
    public LinkedHashSet<Formula> current;
    public Node left;
    public Node right;
    public boolean closed;

    Node(LinkedHashSet<Formula> path,LinkedHashSet<Formula> current) {
        this.path = path;
        this.current = current;
        right = null;
        left = null;
    }
}
        
