package com.notes.dynamic;

public class CuttingRod {

    //priceLength[i] = price of piece of length i
    int[] priceLength;
    int length;

    int[] cost;
    String[] sols;

    //Approach

    /**
     * C[i] = Optimal cost to cut a cost of length i;
     * C[i] = max{C[i-l]+priceLength[l]} for all l<i
     */
    void calculateCost() {

        cost = new int[length + 1];
        sols = new String[length + 1];
        // initialize the optimal solution to be the price of piece of that length itself
        for (int i = 1; i <= length; i++) {
            cost[i] = priceLength[i - 1];
            sols[i] = i + "";
        }
        cost[0] = 0;
        sols[0] = "";
        // build the solution bottom up
        for (int i = 1; i <= length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                int t = cost[i - j] + priceLength[j]; // check for all pieces of length less than i
                if (t > max) {
                    max = t;
                    cost[i] = t;
                    sols[i] = sols[i - j] + "," + j; // include piece of length j and remaining subproblem
                }
            }
        }
        System.out.println("Optimal Cost is " + cost[length]);
        System.out.println("Optimal Solution is " + sols[length]);
    }

    public static void main(String[] args) {
        int[] pl = {0, 1, 5, 8, 9, 10, 17, 17, 20};
        CuttingRod cr = new CuttingRod();
        cr.length = 4;
        cr.priceLength = pl;
        cr.calculateCost();

        cr.length = 8;
        cr.priceLength = pl;
        cr.calculateCost();

    }

}
