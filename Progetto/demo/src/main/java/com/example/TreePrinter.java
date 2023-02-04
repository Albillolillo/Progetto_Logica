package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextArea;

import org.logicng.formulas.Formula;

public class TreePrinter {
    private JTextArea ta;
    private Node root;
    public TreePrinter(JTextArea ta,Node root){
        this.ta=ta;
        this.root=root;

    }


    public   void printNode() {
        int maxLevel = maxLevel(root);
        System.out.println(maxLevel);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private  void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;
        System.out.println("\nsono in printnodeinternal a livello :"+ level);
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        this.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                if(node.closed == true){
                    this.ta.append("X");
                }else{
                    for(Formula f :node.current){
                        ta.append(f + ", ");
                    }
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                }
            } else {
                newNodes.add(null);
                newNodes.add(null);
                ta.append(" ");
            }

            this.printWhitespaces(betweenSpaces);
        }
            ta.append("\n");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                this.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    this.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    this.ta.append("/");
                else
                    this.printWhitespaces(1);

                this.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    ta.append("\\");
                else
                    this.printWhitespaces(1);

                this.printWhitespaces(endgeLines + endgeLines - i);
            }

            ta.append("\n");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private  void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            this.ta.append(" ");
    }

    private   int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private   boolean isAllElementsNull(List<Node> list) {
        for (Node object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}