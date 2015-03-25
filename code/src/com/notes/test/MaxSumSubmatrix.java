package com.notes.test;


public class MaxSumSubmatrix {

    int[][] inputMatrix;

    int k;

    // matrix to store sum of all s[i,j] for each j from 0-n-k+1
    int[][] s;

    // matrix to store the sum of matrix startin from i,j
    int[][] ks;

    public MaxSumSubmatrix(int[][] inp, int k) {
        this.k = k;
        inputMatrix = inp;
        int row = inputMatrix.length;
        int col = inputMatrix[0].length;

        s = new int[row][col - k + 1];
        ks = new int[row - k + 1][col - k + 1];

    }

    void calculateS() {
        int n = inputMatrix.length;
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = 0; j < k; j++) {
                t = t + inputMatrix[i][j];
            }
            s[i][0] = t;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - k + 1; j++) {
                s[i][j] = s[i][j - 1] + inputMatrix[i][j + k - 1]
                    - inputMatrix[i][j - 1];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < s[0].length; j++) {
                System.out.print(s[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static int maxRow, maxCol;

    public int calculateKS() {
        int n = inputMatrix.length;
        int m = inputMatrix[0].length;
        int sum = 0;
        for (int j = 0; j < m - k + 1; j++) {
            for (int c = 0; c < k; c++) {
                sum = sum + s[c][j];
            }
            ks[0][j] = sum;
            sum = 0;
        }

        int max = ks[0][0];

        for (int i = 1; i < n - k + 1; i++) {
            for (int j = 0; j < m - k + 1; j++) {
                ks[i][j] = ks[i - 1][j] - s[i - 1][j] + s[i + k - 1][j];
                if (max < ks[i][j]) {
                    max = ks[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("#############################################");
        for (int i = 0; i < ks.length; i++) {
            for (int j = 0; j < ks[0].length; j++) {
                System.out.print(ks[i][j] + "\t");
            }
            System.out.println();
        }
        return max;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int[][] inpu = { { 1, 0, 2, 4 }, { 3, 7, 1, 6 }, { 5, 8, 11, 6 },
            { 9, 12, 2, 1 } };
        int k = 2;
        MaxSumSubmatrix mat = new MaxSumSubmatrix(inpu, 2);
        mat.calculateS();
        System.out.println(mat.calculateKS());
        System.out.println(mat.maxRow + "\t" + mat.maxCol);
    }

}
