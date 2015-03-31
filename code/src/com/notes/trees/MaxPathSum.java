package com.notes.trees;


/**
 * Find path with maximum sum between 2 leaves of a binary tree
 */
public class MaxPathSum {
	
	
	//Approach
	/**
	 *  For each node see, if we can update the leftSum and right Sum,
	 *  ie, the max Path sum to any of the leaf in its left subTree and rightSubTree
	 *  so, we can calculate max sum of path going thru this node
	 *   now our solution is simply the max of these sums
	 *  
	 *  
	 *  findPath(Node root) {
	 *  	calculateSum(root);
	 *      int max = findMax(root,Integer.MIN);
	 *      
	 *  
	 *  }
	 *  
	 *  int findMax(Node n,int i) {
	 *  	if(n == null) return;
	 *      else {
	 *      
	 *      	int left = n.leftSum;
	 *      	int right = n.rightSum;
	 *          // following options are there now
	 *          //1) Path goes through n
	 *          int t1 = left+n+right;
	 *         // path does not goes through n
	 *         int m = Math.max(i,t1)
	 *         int t2 = Math.max(findMax(n.left,m),findMax(n.right,m));
	 *         return t2;
	 *      }
	 *  }
	 *  
	 *  void calculateSum(Node n) {
	 *   if( n = null) return;
	 *   if(n.left == null && n.right == null) { // leaf
	 *   	n.sum = n.data;
	 *   	n.data;
	 *   	n.left = 0;
	 *   	n.right = 0 ;
	 *   }
	 *   else {
	 *   	int left = calculateSum(n.left);
	 *      int right = calculateSum(n.right);
	 *      int max = Math.max(left,right);
	 *      n.sum = max+n.data;
	 *      n.left = left;
	 *      n.right = right;
	 *   }
	 *  
	 */

}
