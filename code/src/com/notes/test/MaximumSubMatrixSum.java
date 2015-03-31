package com.notes.test;

/**
 * Given a 2D array, find the maximum sum subarray in it
 */
public class MaximumSubMatrixSum {

	int[][] A;
	int r;
	int c;
	
	//Approach
	/**
	 * f(i,j,sum) {
	 * 
	 * 	 if(i > row || j > col) 
	 * 		return sum;		// this is max sum we have found
	 * 	 int t1 = f(i+1,j,sum+A[i][j]) // sum if we include A[i][j] and move in same row
	 *   int t3 = f(i,j+1,sum+A[i][j]) // sum if we include A[j][j] and move in same col
	 * 		
	 *   // if we dont include A[i][j]
	 *   t2 = Math.max(f(i+1,j,sum) // move in row
	 *        f(i,j+1,sum) // move in col
	 *        f(i+1,j+1,sum)) // move in diag           	
	 * 	
	 *    return Math.max(t1,t2,t3);
	 * 
	 * }
	 * 
	 * call with f(0,0,0);
	 * 
	 * 
	 * 
	 * 
	 */
	
	int f(int i,int j,int sum) {
		if(i>=r || j>=c) {
			return sum;
		} else {
			
			int t1 = f(i+1,j,sum+A[i][j]);
			int t2 = f(i,j+1,sum+A[i][j]);
			int diagMove = f(i+1, j+1,sum);
			int rowMove = f(i+1,j,sum);
			int colMove = f(i,j+1,sum);
			int t3 = Math.max(diagMove,Math.max(rowMove,colMove));
			int max = Math.max(t1,Math.max(t2, t3));
			
			return max;
		}
	}
	
	public static void main(String[] args) {
		 int[][] m = {{1, 2, -1, -4, -20},
                 {-8, -3, 4, 2, 1},
                 {3, 8, 10, 1, 3},
                 {-4, -1, 1, 7, -6}
                };
		 MaximumSubMatrixSum mss = new MaximumSubMatrixSum();
		 mss.A = m;
		 mss.r = 4;
		 mss.c = 5;
		 System.out.println(mss.f(0, 0, 0));
	}
}
