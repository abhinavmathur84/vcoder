package com.notes.trees;

/**
 * Given a binary tree and 2 nodes, find common ancestor for them
 */
public class FindCommonAncestor {

    class Node {
        int i;
        Node left;
        Node right;
    }

    //Approach
    /**
     *  Node findCommonAncestor(Node n, Node n1,Node n2) {
     *      if(n == null) { // if we have reached a null node
     *          return null;
     *      }
     *      else {
     *              //both n1 and n2 are present in subtree rooted at n
     *              if(contains(n,n1) && contains(n,n2)) {
     *                  if(contains(n.left,n1) && contains(n.left,n2)) // both present in left subtree
     *                  {
     *                      return findCommonAncestor(n.left,n1,n2);
     *                  }
     *                  else if(contains(n.right,n1) && contains(n.right.n2)) // both present in right subtree
     *                  {
     *                      return findCommonAncestor(n.right,n1,n2);
     *                  }
     *                  else  // one present in left and one in right
     *                  {
     *                      return n;
     *                  }
     *              } else {
     *                  //subtree rooted at n does not contains node n1 or n2
     *                  // since we start it with root, and either n1 or n2 is not present
     *                  // we can return false
     *                  //otherwise previous logic will take care
     *
     *              }
     *          }
     *  }
     *
     *  // returns true if subtree rooted at n contains n1
     *  NodeChild contains(Node n, Node n1) {
     *      if(n == null) return false;
     *      return contains(n.left,n1) || contains(n.right,n1)
     *
     *  }
     */






}
