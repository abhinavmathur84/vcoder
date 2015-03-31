package com.notes.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShortestSubStringContainingAllChars {

	
	Map<Character,Integer> map;
	String s;
	
	//Approach
	/**
	 * Have 2 pointers i and j, both initialized to 0
	 * Now start scanning the string
	 * if chatAt(j) is one of the char in set, remove it from set it from set
	 * and increment j++
	 * Now if at any point there are no more characters left, length = j-i and 
	 * check if this is smallest and update 
	 * make i=j and repeat same process
	 * 
	 */
	
	int scan(final int k) {
		int start = k;
		int end = k;
		boolean windowStarted = false;
		int n = s.length();
		Map<Character,Integer> clone = new HashMap<Character, Integer>(map);
		
		while(end<n && clone.size() > 0) {
			char c = s.charAt(end);
			if(clone.containsKey(c)) {
				windowStarted = true;
				clone.remove(c);
			} else {
				// not intresting char, just ignore
			}
			if(!windowStarted) { // if no interesting char found
				start++; // keep increasing i too
			}
			end++;
		}
		return end-start;
	}
	
	
	void calculate() {
		int minLen = Integer.MAX_VALUE;
		int start = 0;
		int end =0;
		for(int i=0;i<s.length();) {
			int l = scan(i);
			if(l<minLen) {
				start = i;
				end = i+l;
				minLen = l;
			}
			i = i+l;
		}
		System.out.println(minLen);
		System.out.println(start+","+end);
	}
	
	public static void main(String[] args) {
		ShortestSubStringContainingAllChars sc = new ShortestSubStringContainingAllChars();
		
		char s [] = {'k','m','k','a','a','a','b','k','k','k','c','b','d','b','a'};
		String str = new String(s);
		sc.s =str;
		char f [] = {'a','b','c','d'};
		sc.map = new HashMap<Character, Integer>();
		for(int i=0;i<f.length;i++) {
			sc.map.put(f[i],1);
		}
		
		sc.calculate();
	}
}
