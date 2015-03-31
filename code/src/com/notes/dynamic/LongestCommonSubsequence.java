package com.notes.dynamic;

public class LongestCommonSubsequence {

	
	String A;
	String B;
		
	//Approach
	/**
	 * 
	 * Start with 0 th char (ith char)
	 *    if they are equal return the solution for 1st (i+1th and j+1 th char) and increment current length by 1
	 *    if they are not equal, try by increasing length of A by 1 and keeping length of B as same
	 *                           try by increasing length of B by 1 and keeping length of A as same
	 *    return which ever is maximum
	 *    
	 *    when we have reached at end of string return whatever length we have found till now
	 *    
	 *    
	 *  max(abc,abc,0,0,0)
	 * 		max(bc,bc,0,0,1)
	 * 
	 */
	Sol maxRecursive(String s1,String s2,int i,int j,int m,String subseqence) {
		if(s1.length() == 0 || s2.length() == 0) {
			return new Sol(m,subseqence);
		}
		// so either
		if(s1.charAt(i) == s2.charAt(j)) { // if current char are same
			 // chose both s1[i] and s2[j] and increase length by 1
			Sol s = maxRecursive(s1.substring(i+1, s1.length()),s2.substring(j+1, s2.length()),i,i,m+1,subseqence+s1.charAt(i));
			return s;
		}
		else {
			Sol sol1  =  maxRecursive(s1.substring(i+1,s1.length()),s2,i,j,m,subseqence); // check by next char of s1
			Sol sol2  = maxRecursive(s1,s2.substring(j+1,s2.length()),i,j,m,subseqence); // check by next char of s2
			if(sol1.i>sol2.i) {
				return  sol1;
			}else {
				return sol2;
			}
		}
	}
	
	class Sol {
		int i;
		String s;
		Sol(int i,String s) {
			this.i =i;
			this.s = s;
		}
	}
	
	//Approach
	/**
	 * let LCS[i,j] be solutions for A[1..i] and B[1..j]
	 * now LCS[i+1,j+1] = LCS[i,j]+1 if(A[i+1]=B[j+1])
	 *                   Max(LCS[i+1,j],LCS[i,j+1]) other wise
	 * We need to calculate L[m,n]
	 * L[0][0] = max(L[0,1],L[1,0])
	 * 
	 * L[0][i] = 1 if any B[i] = A[0] 
	 *         = 0 otherwise
	 * L[i][0] = 1  if any A[i] = B[0]
	 *         = 0  otherwise
	 *  
	 */
	void calculate() {
		int m = A.length();
		int n = B.length();
		int[][] LCS = new int[m+1][n+1];
		
		for(int i=0;i<n;i++){
			if(B.charAt(i) == A.charAt(0)) {
				LCS[0][i] = 1;
			}
		}
		for(int j=0;j<m;j++) {
			if(A.charAt(j) == B.charAt(0)) {
				LCS[j][0] = 1;
			}
		}
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(A.charAt(i) == B.charAt(j)) {
					LCS[i+1][j+1] = LCS[i][j]+1;
				} else {
					int t1 = LCS[i][j+1];
					int t2 = LCS[i+1][j];
					LCS[i+1][j+1] = Math.max(t1, t2);
				}
			}
		}
		System.out.println(LCS[m-1][n-1]);
	}
	
	
	
	
	public static void main(String[] args) {
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		String s1 = "abceeeeeeeedggg";
		String s2 = "abcssssssssssssssssssssssd";
		System.out.println(lcs.maxRecursive(s1, s2, 0,0,0,"").s);
		lcs.A = s1;
		lcs.B = s2;
		lcs.calculate();
		String s3 = "abcssssssssssssssssssssssdfg";
		System.out.println(lcs.maxRecursive(s1, s3, 0,0,0,"").s);
		lcs.A = s1;
		lcs.B = s3;
		lcs.calculate();
	}
	
	
}
