package com.notes.test;

import java.util.ArrayList;
import java.util.List;

public class A {

	List<String[]> arrays;
	int count;

	void f(int depth, String str) {
		int n = arrays.size() - 1;
		if (depth == n) {
			String[] a = arrays.get(n);
			for (int i = 0; i < a.length; i++) {
				count = count + 1;
				System.out.println(count + ". " + str + " " + a[i]);
			}
		} else {
			String[] a = arrays.get(depth);
			for (int i = 0; i < a.length; i++) {
				f(depth + 1, str + " " + a[i]);
			}
		}
	}

	public static void main(String[] args) {
		A a = new A();
		String[] a1 = new String[] { "you", "we" };
		String[] a2 = new String[] { "have", "are" };
		String[] a3 = new String[] { "sleep", "eat", "drink" };
		List<String[]> l = new ArrayList<String[]>();
		l.add(a1);
		l.add(a2);
		l.add(a3);
		a.arrays = l;
		a.f(0, "");

	}
	
}
