package com.notes.dynamic;


/**
 * Given an array p[] which represents the chain of matrices
 * such that the ith matrix Ai is of dimension p[i-1] x p[i].
 * We need to write a function MatrixChainOrder() that should
 * return the minimum number of multiplications needed to multiply
 * the chain.
 */
public class MatrixChainMultiplication {

    //we have A1....An
    //dimension of Ai is p[i-1]*p[i]
    // dimension of A1...An-1   p[0]*p[n-1]
    int[] p;
    String[] matrix;

    int[][] M;
    String[][] sols;

    //Approach

    /**
     * M[i][j] = minimum number of multiplications required to multiply Ai...Aj
     * T = Ai......Aj of dimension p[i-1]*p[j]
     * T1 = Ai...Ak of dimension p[i-1]*p[k]
     * T2 = Ak+1...Aj of dimension p[k]*p[j]
     * so number of multiplications required for T
     * M[i][j] = min {M[i][k]+ M[k+1][j] + p[i-1]*p[k]*p[j]}
     * We need to calculate M[1][n-1]
     * M[i][j] = 0 when i=j as no multiplications are required
     * Number of matrix is p.length-1.
     */

    void calculateMatMult() {
        M = new int[p.length + 1][p.length + 1];
        sols = new String[p.length + 1][p.length + 1];
        int n = p.length;
        for (int i = 1; i < n; i++) {
            M[i][i] = 0;
            sols[i][i] = matrix[i - 1];
        }
        for (int len = 2; len <= n; len++) {
            // len = 2 A1A2 i=1.n-1, j=2 if k=i(A1)(A2) and k<j
            // len = n A1...An, i=1 j=n k<j
            // for any other len i=n-len+1; j= i+len-1 k<j
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                int min = Integer.MAX_VALUE;
                String sol = "";
                for (int k = i; k < j; k++) {
                    int t = M[i][k] + M[k + 1][j] + p[i - 1] * p[k] * p[j]; //(Ai...Ak)(Ak+1..Aj)=> p[i-1]*p[k]*p[j-1]
                    if (t < min) {
                        min = t;
                        int temp = k + 1;
                        sol = "(" + sols[i][k] + ")(" + sols[k + 1][j] + ")";
                    }
                }
                M[i][j] = min;
                sols[i][j] = sol;
            }
        }
        System.out.println(M[1][n - 1]);
        System.out.println(sols[1][n - 1]);
    }

    public static void main(String[] args) {
        int p[] = {40, 20, 30, 10, 30};
        String[] mat = new String[]{"A", "B", "C", "D"};
        MatrixChainMultiplication mcm = new MatrixChainMultiplication();
        mcm.p = p;
        mcm.matrix = mat;
        mcm.calculateMatMult();

        int p1[] = {10, 20, 30, 40, 30};
        String[] mat1 = new String[]{"A", "B", "C", "D"};
        mcm.p = p1;
        mcm.matrix = mat1;
        mcm.calculateMatMult();

    }

}
