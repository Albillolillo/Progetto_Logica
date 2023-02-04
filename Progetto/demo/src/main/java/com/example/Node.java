package com.example;

import java.util.LinkedHashSet;
import org.logicng.formulas.Formula;

public class Node {
    public LinkedHashSet<Formula> path;
    public LinkedHashSet<Formula> current;
    public Node left;
    public Node right;
    public boolean closed;

    Node(LinkedHashSet<Formula> path,LinkedHashSet<Formula> current) {
        this.path = path;
        this.current = current;
        closed=false;
        right = null;
        left = null;
        contradiction();
        if(this.path!=null){
        System.out.print("\nHo generato nodo avente:"+
                            "\n path:"+path.toString()+
                            "\n current:"+current.toString()+
                            "\n close:"+closed
                            );
        }
    }

    public void contradiction(){
        if(this.path==null)return;
        for(Formula i:this.current){
            for(Formula j:this.path){
                if(i.equals(j.negate())){
                    this.left=new Node(null,null);
                    this.left.closed=true;
                    System.out.print("\nHo chiuso nodo corrente:"+this.left);
                }
            }
        }
    }

}
        
