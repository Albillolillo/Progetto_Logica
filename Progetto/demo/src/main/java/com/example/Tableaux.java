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
            for(int j=0 ; j < size-1;j++){
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
                for(int j=0 ; j < size-1;j++){
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
        System.out.println("\nsono nel what:"+subFormulas);

        if(subFormulas.size()<2){return "literal";}
        String is_what;

        is_what=this.is_and(subform_array, ret);
        if(is_what != null){return is_what;}

        is_what=this.is_not_and(subform_array, ret);
        if(is_what != null){return is_what;}

        is_what=this.is_or(subform_array, ret); 
        if(is_what != null){return is_what;}

        is_what=this.is_not_or(subform_array, ret);
        if(is_what != null){return is_what;}

        is_what=this.is_impl(subform_array, ret);
        if(is_what != null){return is_what;}

        is_what=this.is_not_impl(subform_array, ret);
        if(is_what != null){return is_what;}

        return null;
    }

    public boolean tableaux_algorithm(Node root){
        if(root.left!=null && root.left.closed == true)
            return true;

        LinkedHashSet<Formula> path;
        if(root.path!=null){
            path = (LinkedHashSet<Formula>)root.path.clone();
        }else{
            path =new LinkedHashSet<>();
        }

        for(Formula i:root.current){
            path.add(i);
        }
        
        Node left,right;
        Formula[] current_array=new Formula[root.current.size()];
        current_array=root.current.toArray(current_array);
        LinkedHashSet<Formula> current= new LinkedHashSet<Formula>();
        for(int i=0;i < current_array.length;i++) {
            LinkedHashSet<Formula> operands= new LinkedHashSet<>();
            Formula[] operands_array= new Formula[2];
            Formula f = current_array[i];
            System.out.print("\nsono in :"+current_array[i]+"\n");
            switch (what(f,operands)){
                case "literal":
                    System.out.print("\ncase literal\n");
                    break;

                case "is_not_and":
                    System.out.print("\ncase is_not_and\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size(); j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[0].negate());
                    root.left=new Node(path,current);
                    current=new LinkedHashSet<>();
                    
                    for(int j=i+1;j < root.current.size();j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[1].negate());
                    root.right= new Node(path,current);
                    return tableaux_algorithm(root.left) && tableaux_algorithm(root.right);

                case "is_not_or":
                    System.out.print("\ncase is_not_or\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size(); j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[0].negate());
                    current.add(operands_array[1].negate());
                    root.left=new Node(path,current);
                    return tableaux_algorithm(root.left) && true;
                     
                case "is_not_impl":
                    System.out.print("\ncase is_not_impl\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size(); j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[0]);
                    current.add(operands_array[1].negate());
                    root.left=new Node(path,current);
                    return tableaux_algorithm(root.left) && true;

                case "is_or":
                    System.out.print("\ncase is_or\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size();j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[0]);
                    root.left=new Node(path,current);
                    current=new LinkedHashSet<>();
                    
                    for(int j=i+1;j < root.current.size();j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[1]);
                    root.right= new Node(path,current);
                    return tableaux_algorithm(root.left) && tableaux_algorithm(root.right);

                case "is_and":
                    System.out.print("\ncase is_and\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size(); j++){
                        current.add(current_array[j]);
                        }
                    current.add(operands_array[0]);
                    current.add(operands_array[1]);
                    root.left=new Node(path,current);
                    return tableaux_algorithm(root.left) && true;

                case "is_impl":
                    System.out.print("\ncase is_impl\n");
                    operands_array= operands.toArray(operands_array);
                    
                    for(int j=i+1;j < root.current.size();j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[0]);
                    root.left=new Node(path,current);
                    current=new LinkedHashSet<>();
                
                    for(int j=i+1;j < root.current.size();j++){
                        current.add(current_array[j]);
                    }
                    current.add(operands_array[1]);
                    root.right= new Node(path,current);
                    return tableaux_algorithm(root.left) && tableaux_algorithm(root.right);
                    
                default:
                    break;
            }
        }
        return false;
    }
}


