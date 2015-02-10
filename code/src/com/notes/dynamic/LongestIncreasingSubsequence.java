package com.notes.dynamic;

public class LongestIncreasingSubsequence {

    // the array
    int[] a;

    //lisLength[i]= Length of longest increasing subsequence ending at a[i].
    int[] lisLength;

    // sols[i] = longest increasing subsequence ending at a[i];
    String[] sols;

    //Approach
    /**
     * Initialize
     * lisLength[0] = 1
     * sols[0] ="a[0]";
     * lisLength[i] = max{lisLength[j]}+1 for all 0 < j < i and a[i] > a[j]
     *              =  1 otherwise
     * sols[i] =  sols[j] + a[i] where sols[j] is correspondingly chosen
     *         =  a[i] otherwise
     * <p/>
     * We are required to compute max of lisLength and corresponding sols
     */
    void calculateLIS() {
        lisLength = new int[a.length];
        sols = new String[a.length];

        lisLength[0] = 1; // lis for array of length 1 ending at a[0].
        sols[0] = a[0] + "";

        // required data we need to compute
        int maxLisLength = Integer.MIN_VALUE;
        String lis = "";
        // build the solution bottom up
        for (int i = 1; i < a.length; i++) {
            int max = Integer.MIN_VALUE;
            String sol = "";
            // loop over all the previous elements
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) { // checking the increasing subsequence property
                    if (max < lisLength[j]) { // check if current max is less than lis ending at a[j]
                        max = lisLength[j]; // if true then update the max value
                        sol = sols[j]; // update the solution
                    }
                }
            }
           // no increasing subsequence found which end in a[i]
            if (max == Integer.MIN_VALUE) {
                lisLength[i] = 1;
                sols[i] = a[i] + "";
            } else {
                // now update lisLength[i]
                lisLength[i] = max + 1;
                sols[i] = sol + "," + a[i];
            }
            // update the global max which we need
            if (maxLisLength < lisLength[i]) {
                maxLisLength = lisLength[i];
                lis = sols[i];
            }
        }

        System.out.println("Length of longest increasing subsequence " + maxLisLength);
        System.out.println("Longest increasing subsequence is \n" + lis);
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

        lis.a = new int[]{2, 3, 4, 8, 12};
        lis.calculateLIS();

        lis.a = new int[]{2, 7, 3, 4, 9, 8, 12};
        lis.calculateLIS();

        lis.a = new int[] {10,22,9,33,21,50,41,60,80};
        lis.calculateLIS();
    }

}
