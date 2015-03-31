package com.notes.test;

public class LongestPalindromicSubstring {

	String s;
	
	int[] P;
	int max =Integer.MIN_VALUE;
	int maxPalindrome = Integer.MIN_VALUE;
	
	String padString() {
		char[] arr = s.toCharArray();
		String ret="#";
		for(int i=0;i<arr.length;i++) {
			ret=ret+arr[i]+"#";
			
		}
		return ret;
	}
	

	
	void calcualte() {
		int n = s.length();
		P = new int[2*n+1];
		String ps = padString();
		
		// l= 2n+1
		int l = ps.length(); 
		
		//loop to consider all 2n-1 centers
		P[0] =0; // palindrome with first center will be 0
		P[l-1]=0;  // palindrome with last at center will be 0 
		
		// loop goes from P[1] to P[2n+1-1-1] ie P[2n-1]
		for(int i=1;i<l-1;i++) {
			fillP(i,ps);
			if(maxPalindrome < P[i] ) {
				maxPalindrome = P[i];
				max =i;
			}
		}
		
		int maxPLen = P[max];
		int start = max - (maxPLen/2);
		int end = max +(maxPLen/2);
		String p = ps.substring(start, end);
		System.out.println(p.replaceAll("#", ""));
	}
	
	/**
	 * 
	 * @param index The index at which the center is current
	 */
	void fillP(int index,String ps) {
		if(P[index]>0) return;
		int len = ps.length();
		int d=1;
		int count=0;
		// loop to fill the longest palindrome for given index
		for(;index-d>=0 && index+d<len;d++) {
			int rightIndex = index+d;
			int leftIndex = index-d;
			if(ps.charAt(rightIndex)!=ps.charAt(leftIndex)) { // we found a mismatch
				break;
			} else {
				count++;
			}
		}
		//System.out.println("Shadow for index "+index+" centered at  " + ps.charAt(index) +" is "+count);
		P[index]=2*count+1; // update its length

		for(int i=0;i<d;i++) {
			int curr = index-i;
			if(liesInShadow(index, d, curr)) { // lies in its shadow
				//System.out.println("Filling the index "+temp +" as P[i] at "  + index +" is "+P[index]);
				P[index+i] = P[index-i];
			}
		}
	}


	/**
	 * 
	 * @param index current index which we filled
	 * @param d  its shadow length
	 * @param curr  // index on left
	 * @return
	 */
	private boolean liesInShadow(int index, int d, int curr) {
		
		int pLen = P[curr];
		int pStart = curr- pLen/2;
		int pEnd = curr + pLen/2;
		int shadowStart = index -d;
		if(pStart > shadowStart && pEnd < index+d) return true;
		return false;
	}
	
	public static void main(String[] args) {
		
		LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
		/*lps.s = "abaaba";
		lps.calcualte();
		System.out.println();
		
		lps.s = "babcbabcbaccba";
		lps.calcualte();
		
		System.out.println("------------");*/
		lps = new LongestPalindromicSubstring();
		lps.s = "babcbabcbaccba";
		System.out.println(lps.padString());
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "abaaba";
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "abababa";
		
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "forgeeksskeegfor";
		
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "caba";
		
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "abacdfgdcaba";
		
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "abacdfgdcabba";
		
		lps.calcualte();
		
		lps = new LongestPalindromicSubstring();
		lps.s = "abacdedcaba";
		
		lps.calcualte();
	
	 
	   
	  
	}
	
	
	
}
