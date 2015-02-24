package com.notes.dynamic;




public class BalancedSubsetDP {

	int diff(int i, int lsum) {
		if(i==a.length) {
			return Math.abs(sum(a) - 2*lsum);
		}
		return Math.min(diff(i + 1, lsum + a[i]), diff(i + 1, lsum));
	}
	//5,6,1
	
	int dp() {
		int l = a.length;
		int T = sum(a);
		int c[][] = new int[l][T+1];
	    for(int i=0;i<l;i++) {
	    	for(int j=0;j<T+1;j++) {
	    		c[i][j] = Integer.MAX_VALUE;
	    	}
	    }
		for(int k=0;k<T+1;k++) {
			c[l-1][k] = Math.min(Math.abs(T-2*k), Math.abs(T-2*(k+a[l-1])) );
		}
		for(int i=l-2;i>=0;i--) {
			for(int j=0;j<T+1-a[i];j++) {
				c[i][j] =Math.min(c[i+1][j+a[i]], c[i+1][j]);
			}
		}
		return c[0][0];
	}
	
	
	public BalancedSubsetDP(int ... a) {
		this.a = a;
	}

	int a[] ;
	
	public static void main(String[] args) {
		{
			BalancedSubsetDP balancedSet = new BalancedSubsetDP(5, 6, 1);
			System.out.println("Recursion : " + balancedSet.diff(0, 0));
			System.out.println("DP : " + balancedSet.dp());
			System.out.println("--");
		}
		{
			BalancedSubsetDP balancedSet = new BalancedSubsetDP(1, 7, 4, 11);
			System.out.println("Recursion : " + balancedSet.diff(0, 0));
			System.out.println("DP : " + balancedSet.dp());
			System.out.println("--");
		}
		{
			BalancedSubsetDP balancedSet = new BalancedSubsetDP(1, 5, 4, 9, 6, 3);
			System.out.println("Recursion : " + balancedSet.diff(0, 0));
			System.out.println("DP : " + balancedSet.dp());
			System.out.println("--");
		}
		{
			BalancedSubsetDP balancedSet = new BalancedSubsetDP(3,1,1,2,2,1);
			System.out.println("Recursion : " + balancedSet.diff(0, 0));
			System.out.println("DP : " + balancedSet.dp());
			System.out.println("--");
		}
		{
			BalancedSubsetDP balancedSet = new BalancedSubsetDP(1,7,4,11);
			System.out.println("Recursion : " + balancedSet.diff(0, 0));
			System.out.println("DP : " + balancedSet.dp());
			System.out.println("--");
		}
	}
	
	public static int sum(int a []) {
		int sum = 0;
		for(int i : a) {
			sum = sum + i;
		}
		return sum;
	}

}
