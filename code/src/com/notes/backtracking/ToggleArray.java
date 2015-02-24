package com.notes.backtracking;

/**
 * Created by mathura on 2/17/2015.
 */
public class ToggleArray {


    /**
     *   A[0] = 1  only 1 way to do this Toggle A[0]
     *   else A[0] = 0 , only 1 way No Op
     *
     *   [1 1   t(a(0,0))  [0 0   t(a(1,1))  [0 1    t(a(0,1))       [1 0      t(a(1,0) [0 0
     *    1 1]             0 1]              1 0]                    1 1]                0 0]
     *
     *    What looks like is start from i(0...n) and then j (0...n) if a[i][j]  is 1 perform a toggle
     *    check if the solution is reached
     *       if not then keep moving ahead by toggling other bits
     *
     *   [1 1 1     0 0 1    [0 1 0     [0 0 0   [0 0 1
     *    1 1 1     0 1 1     0 1 0      1 0 1    0 1 0
     *    1 1 1]    1 1 1]    1 1 1]     1 0 1]   1 0 0]
     *
     *
     *
     */

    int[][] a;

    void toggle(int row,int col) {
        if(row>0) {
          t(row-1,col);
        }
        if(row<a.length-1) {
            t(row+1,col);
        }
        if(col>0) {
            t(row,col-1);
        }
        if(col<a.length-1){
            t(row,col+1);
        }
        t(row,col);
    }

    void t(int i,int j) {
        if(a[i][j] == 1) {
            a[i][j]=0;
        } else if(a[i][j] == 0){
            a[i][j] = 1;
        }else {
            System.out.println("ERROR");
        }
    }

    boolean isSolution(){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++) {
                if(a[i][j] == 1) return false;
            }
        }
        return true;
    }

    void trySolution(int row,int col){
        for(int i=row;i<a.length;i++) {
            for(int j=col;j<a.length;j++) {
                   System.out.println("Toggling "+i+" , "+j);
                    toggle(i, j);
                    print();
                    if(isSolution()) {
                        System.out.println("SOLUTION FOUND");
                        return;
                    }
                    else {
                        if(i < a.length-1)
                            trySolution(i+1,col);
                        else if(j<a.length-1)
                            trySolution(i,col+1);
                    }
                
            }
        }

    }

    void print() {
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++) {
                System.out.print(a[i][j]+"\t");
            }
            System.out.println();
        }
    }

    void perform() {
       trySolution(0,0);
    }

    public static void main(String[] args) {
        ToggleArray ta = new ToggleArray();
        int[][] a = new int[][] {{1,1},{1,1}};
        ta.a = a;
        ta.perform();
    }

}
