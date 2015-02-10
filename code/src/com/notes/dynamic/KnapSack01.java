package com.notes.dynamic;


public class KnapSack01 {

    int[] weight;

    // benefit[i] corresponding to weight[i]
    int[] benefit;

    // size of knapsack
    int size;

    int[] B;
    String[] sols;
    //Approach

    /**
     * B[w] = max benefit which can be obtained from knapsack of size w
     * B[w] = max{B[w-weight[i]]+benefit[i]} for all i possible in weights
     */
    void calcuate() {
        B = new int[size + 1];
        sols = new String[size + 1];

        B[0] = 0; // benefit for knapsack of size 0 is 0;
        sols[0] = "";

        int len = benefit.length;

        // build the solution bottom up
        for (int w = 0; w <= size; w++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                if (w >= weight[i]) {
                    int t = B[w - weight[i]] + benefit[i];
                    if (t > max) {
                        max = t;
                        B[w] = t;
                        sols[w] = sols[w - weight[i]] + "," + weight[i];
                    }
                }
            }
        }
        System.out.println("Optimal Weight is " + B[size]);
        System.out.println("Solution is " + sols[size]);
    }

    public static void main(String[] args) {
        int[] w = {2, 3, 4, 5};
        int[] b = {3, 4, 5, 6};

        KnapSack01 ks = new KnapSack01();
        ks.benefit = b;
        ks.weight = w;
        ks.size = 5;
        ks.calcuate();

        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int  W = 50;
        ks.benefit = val;
        ks.weight = wt;
        ks.size = W;
        ks.calcuate();
    }

}
