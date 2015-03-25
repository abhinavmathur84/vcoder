package com.notes.trees;

/**
 * Implement a BST 
 * @author abhmathu
 *
 */
public class BSTRecurse {

    Node root;
 
    class Node {
        public Node(int i) {
           data = i;
        }
        int data;
        Node left;
        Node right;
        
        @Override
        public String toString() {
            return data+"";
        }
    }
    
    public void add(int i) {
        if(root == null) {
            root = new Node(i);
        } else {
            add(i,root);
        }
    }
   
    private  void add(int i,Node parent) {
        if(i<=parent.data) {
            addLeft(i,parent.left,parent);
        } else {
            addRight(i,parent.right,parent);
        }
    }
    
    private void addLeft(int i,Node n,Node parent) {
        if(n == null) {
            parent.left = new Node(i);
        } else {
            add(i,n);
        }
    }
    
    private void addRight(int i,Node n, Node parent) {
        if(n == null) {
            parent.right = new Node(i);
        } else {
            add(i,n);
        }
    }
    
    public Node find(int i) {
        return find(i,root);
    }
    
    private Node find(int i, Node n) {
        if(n == null) return null;
        if(i == n.data) return n;
        if(i<n.data) return find(i,n.left);
        else return find(i,n.right);
    }
      
    public void inOrder() {
        inOrder(root);
    }
    
    public void inOrder(Node n) {
        if(n == null) return;
        inOrder(n.left);
        System.out.print(n.data+" ");
        inOrder(n.right);
    }
  
    public void delete(int i) {
        delete(i,root,null);
    }
    
    private void delete(int i,Node n,Node parent) {
        if(n == null) return;
        else if(i<n.data) delete(i,n.left,n);
        else if(i> n.data) delete(i,n.right,n);
        else if(parent == null) { // deleting root
            if(n.left !=null) {
                Node rightMost = findRightMost(n.left);
                rightMost.right = n.right;
                root = n.left;
            } else {
                if(n.right !=null) {
                    Node leftMost = findLeftMost(n.right);
                    leftMost.left = n.left;
                    root = n.right;
                }
            }
            if(n.left == null && n.right == null) {
                root = null;
            }
        }
        else {
            if(parent.left!=null && parent.left.data == i) {
                    if(n.left == null && n.right == null) { // deleting the left child
                    parent.left = null;
                } else if(n.left !=null) {
                    Node rightMost = findRightMost(n.left);
                    // make the right subtree of n as right subtree of rightMost child of n
                    rightMost.right = n.right; 
                    parent.left = n.left;
                } else if(n.right !=null) {
                    Node leftMost = findLeftMost(n.right);
                    leftMost.left = n.left;
                    parent.left = n.right;
                }
            } else  if(parent.right !=null && parent.right.data == i){
                    if(n.left == null && n.right == null) { // deleting the right child
                    parent.right = null;
                } else if(n.left !=null) {
                    Node rightMost = findRightMost(n.left);
                    rightMost.right = n.right;
                    parent.right = n.left;
                } else if(n.right !=null) {
                    Node leftMost = findLeftMost(n.right);
                    leftMost.left = n.left;
                    parent.right = n.right;
                }
            }
        }
    }
    
    private Node findLeftMost(Node right) {
       if(right == null) return null;
       if(right.left == null) return right;
       else return findLeftMost(right.left);
    }

    private Node findRightMost(Node left) {
        if(left == null) return null;
        if(left.right == null) return left;
        else return findRightMost(left.right);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
       int [] numbers = {15, 27, 19, 67, 10, 3, 87, 65, 90, 54, 32, 31, 29, 54, 35, 10, 87, 65, 90, 65, 43, 96, 67, 54, 98, 65};
        
        BSTRecurse  b = new BSTRecurse();
        for(int i=0;i<numbers.length;i++) {
            b.add(numbers[i]);
        }
        b.inOrder();
        System.out.println();
        System.out.println(b.root);
        b.delete(15);
        b.inOrder();
        System.out.println();
        
    }

}
