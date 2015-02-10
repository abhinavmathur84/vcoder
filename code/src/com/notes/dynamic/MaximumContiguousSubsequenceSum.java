package com.notes.dynamic;

public class MaximumContiguousSubsequenceSum {

    // The input array
    int[] a;

    // array which contains the maximum sum till i;
    // maxSum[i] = maximum sum till a[i];
    int[] maxSum;

    // array which stores the subsequence which will give the max sum till i;
    // sols[i] = subsequence which will give maximum sum till a[i]
    String[] sols;

    //Approach

    /**
     * initialize the max  to maxSum[0];
     * Solve for i-1 and now
     * if maxSum[i-1] > 0 then maxSum[i] = max {maxSum[i-1]+a[i],a[i]} and update the sols accordingly
     * else maxSum[i] = a[i] and sols[i] = a[i] only. start a new window at a[i]
     * keep updating the max and solution accordingly
     */

    void calculate() {
        maxSum = new int[a.length];
        sols = new String[a.length];

        // initialize the maxSum with just 1 element array as that element itself;
        maxSum[0] = a[0];

        // initialize the solution(subsequence) with just 1 element array as that element itself.s
        sols[0] = a[0] + "";

        int max = maxSum[0];
        String sol = sols[0];
        // loop to build the subsequence bottom up starting from second element of array and ending at last.
        for (int i = 1; i < a.length; i++) {
            if(maxSum[i-1] > 0) {
                int temp = maxSum[i-1]+a[i];
                if(temp > a[i]) {
                    maxSum[i] = temp; // update the maxSum
                    sols[i] = sols[i-1]+","+a[i]; // update the sols to include a[i]
                } else {
                    maxSum[i] = a[i]; //update the maxSum to a[i]
                    sols[i] = a[i]+""; // start a new subsequence starting from a[i]
                }
            } else {
                maxSum[i] = a[i]; //update the maxSum to a[i]
                sols[i] = a[i]+""; // start a new subsequence starting from a[i]
            }
            if(max < maxSum[i]) {
                max = maxSum[i];
                sol = sols[i];
            }
        }
        System.out.println("MaximumContiguousSubsequenceSum is "+max);
        System.out.println("Relevant Subsequence is " + sol);
    }

    public static void main(String[] args) {

        MaximumContiguousSubsequenceSum mcss = new MaximumContiguousSubsequenceSum();
        mcss.a = new int[]{3,4,5,6};
        mcss.calculate();

        mcss.a = new int[]{-2,-3,4,-1,-2,1,5,-3};
        mcss.calculate();


        mcss.a = new int[]{-2,-3,-1};
        mcss.calculate();


    }

}
