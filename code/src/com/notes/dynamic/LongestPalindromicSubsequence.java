package com.notes.dynamic;

/**
 * TODO: Read O(n) algo too
 */
public class LongestPalindromicSubsequence {

    // input array
    String[] a;

    int[][] lp;

    String[][] sols;
    //Approach

    /**
     * lp(i,j) = length of longest palindromic subsequence between a[i] and a[j]
     * sols(i,j) = longest palindromic subsequence between a[i] and a[j]
     * lp(i,j)  = 1 if i=j
     *          = 1 if (j=i+1 and a[i] != a[j])
     *          = 2 if (j=i+1 and a[i] = a[j])
     *          = lp(i+1,j-1) +2 if a(i) = a(j)
     *          = max {lp(i+1,j),lp(i,j-1)}
     * <p/>
     * We need to calculate lp(0,a.length-1)
     */
    void calculateLPS() {
        lp = new int[a.length][a.length];
        sols = new String[a.length][a.length];

        for (int i = 0; i < a.length; i++) { // solution for substring of length 1
            lp[i][i] = 1;
            sols[i][i] = a[i];
        }
        // build the table for all substring length >=2 till a.length
        int subStrLen = 2;
        for (subStrLen = 2; subStrLen <= a.length; subStrLen++) {
            // when substring length is subStrLen, i can take value from 0 to a.length-subStrLen+1
            for (int i = 0; i < a.length - subStrLen + 1; i++) {
                // corresponding j will be from i+subStrLen -1
                int j = i + subStrLen - 1;
                if (a[i].equals(a[j]) && subStrLen == 2) { // if substring of length 2 and both characters are same
                    lp[i][j] = 2; // palindrome length will be 2
                    sols[i][j] = a[i] + a[j]; // palindrome will be the substring itself
                } else if (a[i].equals(a[j])) { // if both characters are same
                    lp[i][j] = lp[i + 1][j - 1] + 2; // palindrome length is sub problem + start and end character
                    sols[i][j] = a[i] + sols[i + 1][j - 1] + a[j];
                } else {
                    int t1 = lp[i][j - 1]; // else solve these 2 sub problems
                    int t2 = lp[i + 1][j];
                    if (t1 > t2) { // pick the larger one
                        lp[i][j] = t1;
                        sols[i][j] = sols[i][j - 1];
                    } else {
                        lp[i][j] = t2;
                        sols[i][j] = sols[i + 1][j];
                    }
                }
            }
        }
        System.out.println("Length of longest palindromic subsequence " + lp[0][a.length - 1]);
        System.out.println("Longest palindromic subsequence is " + sols[0][a.length - 1]);
    }


    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();

        lps.a = "A".split(" ");
        lps.calculateLPS();

        lps.a = "A A".split(" ");
        lps.calculateLPS();

        lps.a = "A B".split(" ");
        lps.calculateLPS();
        lps.a = "A B A".split(" ");
        lps.calculateLPS();

        lps.a = "X A Y B Z B A".split(" ");
        lps.calculateLPS();
    }
}
