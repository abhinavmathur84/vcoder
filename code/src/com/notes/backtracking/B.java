package com.notes.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//Find all numbers which does not have same consecutive digits
//n1- of same type
//n2- of same type etc
public class B {

	int cs = 0;

	int f(Map<Integer, Integer> ds, int chosen, int remainingDigits, int count,
			String s) {
		if (remainingDigits == 0) {
			count = count + 1;
			cs = cs + 1;
			System.out.println(cs + ".  " + s);
			return count;
		} else {
			List<Integer> l = findEligble(ds, chosen);
			int z = 0;
			for (int i = 0; i < l.size(); i++) {
				Integer k = l.get(i);
				int rem = ds.get(k);
				if (rem > 0) {
					ds.put(k, rem - 1);
					z = z + f(ds, k, remainingDigits - 1, count, s + k);
					ds.put(k, rem );
				}
			}
			return z;
		}

	}

	List<Integer> findEligble(Map<Integer, Integer> ds, int c) {
		List<Integer> ret = new ArrayList<Integer>();
		Iterator<Integer> ire = ds.keySet().iterator();
		while (ire.hasNext()) {
			Integer next = ire.next();
			if (next != c && ds.get(next) > 0) {
				ret.add(next);
			}
		}
		return ret;

	}

	public static void main(String[] args) {
		Map<Integer, Integer> dataSet = new HashMap<Integer, Integer>();
		dataSet.put(1, 1);
		dataSet.put(2, 1);
		dataSet.put(3, 1);
		dataSet.put(4, 2);
		B b = new B();
		int c = 0;
		for (int i = 1; i <= 4; i++) {
			int cur = dataSet.get(i);
			if (cur > 0) {
				dataSet.put(i, cur - 1);
				c = c + b.f(dataSet, i, 4, 0, i + "");
				dataSet.put(i, cur);
			}
		}
		System.out.println(c);
	}

}
