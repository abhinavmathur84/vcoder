package com.notes.backtracking;

/**
 *  1) Is where I am a solution?
    2) No.  Ok, where can I go from here?  If I can go somewhere, choose a place to go.
    3) Go there.
    5) Was that a solution?  If yes, return true!
    5) If there are remaining places to go, choose one and goto #3.
    6) Out of places to go.  Return false.
 * 
 *
 */
public class NQueen {

    int n;
    int[] state;
    int UNASSIGNED = -1;
    
    public NQueen(int size) {
        n = size;
        state = new int[size];
    }
    
    boolean isValid(int row,int col) {
        for(int i=0;i<row;i++) {
            if(state[i] == col) return false; // same column attack
            if(Math.abs(i-row) == Math.abs(state[i]-col)) return false; // diagonal attack
        }
        return true;
    }
    
   
    static int solcount=0;
    public void printQueens(int[] x) {
        solcount++;
        int N = x.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (x[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean placeGeneric(int row) {
        if(row == n) {
            //leaf Node 
            printQueens(state);
            return true;
        } else {
            for(int i=0;i<n;i++) {
                if(isValid(row,i)) {
                    state[row] =i;
                    if(placeGeneric(row+1)) {
                        return true;
                    } else {
                        state[row] = UNASSIGNED;
                        continue;
                    }
                }
            }
            return false;
        }
    }
    
    public void place(int row) {
        for (int i = 0; i < n; i++) {
            if (isValid(row, i)) {
                state[row] = i; // mark this as visited
                if(row == n-1) { // leaf node
                    printQueens(state);
                } else {
                    place(row + 1); // go to next level
                }
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        NQueen q = new NQueen(8);
        q.place(0);
         System.out.println(solcount);
    }

}
