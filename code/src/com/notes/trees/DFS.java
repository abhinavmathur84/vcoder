package com.notes.trees;

import java.util.List;

public class DFS {


    Node start;
    
    
    class Node {
        int data;
        boolean visited;
        List<Node> children;
    }
    
    void performDFS(Node n) {
        if(n == null) return;
        else {
            visit(n);
            n.visited = true;
            List<Node> children = n.children;
            for(int i=0;i<children.size();i++) {
                if(!n.visited)
                    performDFS(children.get(i));
            }
        }
    }
    
    public void visit(Node n) {
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
