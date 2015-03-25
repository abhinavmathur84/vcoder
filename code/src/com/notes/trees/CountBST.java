package com.notes.trees;

public class CountBST {

    int number;
    CountBST(int nu) {
        number = nu;
    }
    
    int count(int n) {
        if(n == 1) return 1;
        if (n == 2) return 2;
        if(n == 3) return 5;
        else {
            int t =0;
            for(int k=1;k<n;k++) {
                t =t+ count(k)*count(n-k);
            }
            return t;
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       CountBST cBast = new CountBST(4);
       System.out.println(cBast.count(4));

    }

}
