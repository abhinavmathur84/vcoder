package com.notes.test;



import java.util.LinkedList;
import java.util.Queue;

public class F {

	public static void main(String[] args) {
		System.out.println(new F().min());
	}
	
	
	char s [] = {'k','m','k','a','a','a','b','k','k','k','c','b','d','b','a'};
	char f [] = {'a','b','c','d'}; // String to be found
	int m [] = new int[255]; // Keeps The number of occurrence of particular character till now

	int min() {
		Queue<E> q = new LinkedList<E>(); // max length of this list will be longest substring containing all the characters
		int t = 0;  // Stores how many character have been found till now
		int r = Integer.MAX_VALUE ; // Answer:stores length of shortest substring containing all characters of s
		for(int i=0;i<s.length;i++) {
			if(m[s[i]]>=0) {
				if(m[s[i]] == 0) {
					t ++;
				}
				q.add(new E(s[i], i));
				m[s[i]]+=1;
			}
			while(t == f.length) {
				E e =  q.poll(); // removes head
				if(e != null) {
					r = i-e.x + 1 < r ? i-e.x +1 : r;
					m[e.c]-=1;
					if(m[e.c] == 0) {
						t --; // decrease t only when the same character has no more occurrence in the orig array till now
					}
				}
			}
		}
		return r;
	}
    
	/**
	 * Stores position of the character c
	 */
    class E {
		public E(char c, int x) {
			this.c = c; 
			this.x = x;
		}
		char c;
    	int x;
    } 
    
    /**
     * Initializing map for default values
     */
	public F() {
		for(int i=0;i<m.length; i++) {
			m[i] = -1;
		}
		for(char i : f) {
			m[i]=0;
		}
	}
}

