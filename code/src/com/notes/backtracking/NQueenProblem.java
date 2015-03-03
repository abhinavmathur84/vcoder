package com.notes.backtracking;

public class NQueenProblem {
	
	
    int n;
    int[] state;
	
	boolean place(int row, int col) {
		if (isLeaf(row)) {
			if (isValid(row, col)) {
				state[row] = col;
				print(state);
				return true;
			}
			return false;
		} else {
			if (isValid(row, col)) {
				state[row] = col;
				for (int i = 0; i < n; i++) {
					if (place(row + 1, i)) {
						return true;
					}
				}
			} 
			return false;
		}
	}
	
	 public void print(int[] x) {
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
	 
	boolean isLeaf(int row) {
		return row == n-1;
	}
	
	 boolean isValid(int row,int col) {
	        for(int i=0;i<row;i++) {
	            if(state[i] == col) return false; // same column attack
	            if(Math.abs(i-row) == Math.abs(state[i]-col)) return false; // diagonal attack
	        }
	        return true;
	    }
	
public  static void main(String[] args)
{
	int[] a = new int[8];
	NQueenProblem q = new NQueenProblem();
	q.state = a;
	q.n = a.length;
	//q.place(0, 0);
	for(int i=0;i<a.length;i++)
		q.place(0, i);
			
}

}
