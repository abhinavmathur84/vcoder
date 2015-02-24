package com.notes.dynamic;

/**
 * Problem Statement:
 * Given coins of denomination v1=1,v2,v3,v4,...,vn in ascending order find minimum number of coins required to make
 * an amount P
 */
public class CoinChange {

    // array to store possible denominations
    int[] availableCoins;

    // sum whose change we need to find
    int targetSum;

    // integer array to store the minumum number of coins needed to make sum i;
    // numNeeded[i] = minimum number of coins needs to make sum i;s
    int[] numNeeded;

    // integer array to store the coins needed for sum i;
    // sols[i] = list of coins which can be used to reach sum i with min coins.
    String[] sols;

    //Approach

    /**
     * chose the min number needed to make sum with pth coin not chosen and then add 1
     * <p/>
     * numNeeded[i] = min{numNeeded[i-available[p]]} + 1
     * <p/>
     * sols[i] = {sols[i-available[p]],available[p]} for selected numNeeded.
     * <p/>
     * now iterate to build this bottom up.
     */
    void makeChange() {

        numNeeded = new int[targetSum + 1];
        sols = new String[targetSum + 1];

        //loop to build change from 1 to targetSum
        for (int i = 1; i <= targetSum; i++) {
            // initialize the min variable which will store the min coins for sum i
            int min = Integer.MAX_VALUE;
            // loop over all available coins
            for (int j = 0; j < availableCoins.length; j++) {
                // proceed only when this coin can be used to reach i;
                if (availableCoins[j] <= i) {
                    //temp is the number of coins needed to reach i-availableCoins[j]
                    int temp = numNeeded[i - availableCoins[j]];
                    if (temp < min) {
                        // if temp is less than previous min, update the previous min
                        min = temp;
                        // update the numNeeded, by taking this coin and new min
                        numNeeded[i] = min + 1;
                        // update the sols[i] by adding this coin.s
                        sols[i] = sols[i - availableCoins[j]] + "," + availableCoins[j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        CoinChange cc = new CoinChange();

        cc.availableCoins = new int[]{1, 2, 3};
        cc.targetSum = 5;
        cc.makeChange();

        System.out.println("TargetSum: " + cc.targetSum);
        System.out.println("Number of coins needed :" + cc.numNeeded[cc.targetSum]);
        System.out.println("Coins which will be used are :" + cc.sols[cc.targetSum].trim());

        cc.availableCoins = new int[]{1, 2, 3, 5};
        cc.targetSum = 5;
        cc.makeChange();
        System.out.println("TargetSum: " + cc.targetSum);
        System.out.println("Number of coins needed :" + cc.numNeeded[cc.targetSum]);
        System.out.println("Coins which will be used are :" + cc.sols[cc.targetSum].trim());

    }


}
