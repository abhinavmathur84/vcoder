package com.notes.dynamic;

/**
 * Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1]
 * of frequency counts, where freq[i] is the number of searches to keys[i].
 * Construct a binary search tree of all keys such that the total cost of all
 * the searches is as small as possible.
 */
public class OptimalBST {

    int[] keys;

    int[] freq;

    //C[i][j] = min cost to perform search on on the keys from keys[i]..keys[j]
    int[][] cost;

    //sols[i][j] = root of optimal subtree which contains keys[j]...keys[j]
    Node[][] sols;

    // DS to store the solution
    class Node {
        int node;
        Node left;
        Node right;

        Node(int i){
            node = i;
            left = null;
            right = null;
        }
    }

    //Approach
    /**
     * C[i][j] = min cost to perform search on keys from keys[i]--key[j] and sols[j][j] will have the root
     *  We loop over all j-i+1 elements making each one of them as root and compute the cost
     *  We then chose the min of all possible BST.
     *  C[i][j] =  min{C[i,k-1]+sum(i,k-1)+freq[k]+C[k+1,j]+sum(k+1,j)} and
     *  for such a k , Node left = new Node(sols[i][k-1])
     *                 Node right = new Node(sols[k+1][j]}
     *                 Node root = new Node(freq[k]);
     *                 root.left = left;
     *                 root.right = right;
     *  sols[i][j] = root;
     *
     *  We need to compute C[0][n-1] and sols[0][n-1]
     *  i varies from [0..n-1)
     *  j varies from [i+1..n) when i=0 => j=1 ; i=n-2 => j=n-1...n-1
     *  k varies from [i..j]
     *
     *  Boundry Cases
     *      when k=0, this means that first node is root, so left sub-problem is 0
     *      when k = n-1, this means that right most node is root, so right sub-problem is 0
     *
     *  Base Cases
     *  1) c[i][i] = freq[i] // for only one node cost is same as its frequency
     *      sols[i][i] = new Node(keys[i]);
     *  2) c[i][i+1] = if(1*freq[i] + 2*freq[i+1] < 2*freq[i] + 1*freq[i+1])
     *                      then 1*freq[i] + 2*freq[i+1] // making lower key as root
     *                      else 2*freq[i] + 1*freq[i+1] // making higher key as root
     *     sols[i][i+1] =  Node nLower = new Node(keys{i})  // making lower key as root
     *                     nLower.right = new Node(keys{i+1))
     */

    void computeBST() {

        int n = freq.length;
        cost = new int[n][n];
        sols = new Node[n][n];

        for(int i=0;i< n;i++) {
            cost[i][i] = freq[i];// for only one node cost is same as its frequency
            sols[i][i] = new Node(keys[i]); //the same node is the solution
        }

        for(int i=0;i< n -1;i++) {
            int lowerRootCost = freq[i]+2*freq[i+1];
            int higherRootCost = freq[i+1]+2*freq[i];
            Node sol = null;
            if(lowerRootCost <=higherRootCost) {
                sol = new Node(freq[i]); // make the lower key as root
                sol.right = new Node(freq[i+1]);
                cost[i][i+1] = lowerRootCost;
                sols[i][i+1] = sol;
            } else {
                sol = new Node(freq[i+1]); // make the higher key as root
                sol.left = new Node(freq[i]);
                cost[i][i+1] = higherRootCost;
                sols[i][i+1] = sol;
            }

        }

        for(int i=0;i< n -1;i++) {
            for(int j=i+1;j< n;j++) {
                int min = Integer.MAX_VALUE;
                for(int k=i;k<=j;k++) {
                    // if k ==0, this means we are on first key so left cost will be 0
                    int leftCost = k > 0 ? cost[i][k - 1] + sum(i, k - 1): 0;

                    // if k>=n-1 means we are already on last key so right cost will be 0
                    int rightCost = k< n-1 ? cost[k + 1][j] + sum(k + 1, j) : 0;

                    int t = leftCost + freq[k] + rightCost; // compute cost with this as root
                    Node r = new Node(keys[k]);

                    if(t < min) { // if better cost found update solution
                        min = t;
                        sols[i][j] = r;
                        if(k ==0) {
                            r.left =null;
                            r.right = sols[k+1][j];
                        } else if(k == n-1) {
                            r.right =null;
                            r.left = sols[i][k-1];
                        }
                        else {
                            r.left = sols[i][k-1];
                            r.left = sols[k+1][i];
                        }

                        cost[i][j] = min;
                        sols[i][j] =r;
                    }
                }

            }
        }
        System.out.println(cost[0][n -1]);
        System.out.println(sols[0][n -1]);
    }

    private int sum(int i,int j){
        int ret =0;
        for(int k=i;k<=j;k++)
        {
            ret = ret + freq[k];
        }
        return ret;
    }

    public static void main(String[] args) {
        OptimalBST bst = new OptimalBST();
        int keys[] = {10, 12, 20};
        int freq[] = {34, 8, 50};
        bst.keys = keys;
        bst.freq = freq;
        bst.computeBST();
    }

}
