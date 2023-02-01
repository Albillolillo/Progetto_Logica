package com.example;

import org.logicng.formulas.*;

public class App{
    public static void main( String[] args ){
        FormulaFactory f = new FormulaFactory();
        Variable a = f.variable("A");
        Variable b = f.variable("B");
        Literal notC = f.literal("C", false);
        Formula formula = f.and(a, f.not(f.or(b, notC)));
        System.out.println( formula.toString());
    }
}
