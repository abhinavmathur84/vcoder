package com.notes.string;

// find the smallest number which can be formed by removing n chars from a given string.
// you cannot reorder the string
public class SmallestNumber {

	int min = Integer.MAX_VALUE;

	void findSmallest(int n, String s, int index) {
		if (index >= s.length()) {
			return;
		}
		if (n == 0) {
			int i = Integer.parseInt(s);
			min = Math.min(min, i);
		} else {
			// remove char at index
			String s2 = s.substring(0, index)
					+ s.substring(index + 1, s.length());
			findSmallest(n - 1, s2, index);
			// keep char at index
			findSmallest(n, s, index + 1);
		}
	}
	
	
	public static void main(String[] args) {
		SmallestNumber f = new SmallestNumber();
		f.findSmallest(3, "4325043", 0);
		System.out.println(f.min);
		
		f.min = Integer.MAX_VALUE;
		f.findSmallest(5, "765028321", 0);
		System.out.println(f.min);
		

		f.min = Integer.MAX_VALUE;
		f.findSmallest(2, "121198", 0);
		System.out.println(f.min);
		
		
	}

}
