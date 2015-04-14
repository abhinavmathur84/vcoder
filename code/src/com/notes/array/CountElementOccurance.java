package com.notes.array;

import java.util.Stack;

// Count the number of a times a element occurs in a sorted array

public class CountElementOccurance {

	int[] A;
	

	
	int first(int start,int end,int e) {
	    int n= A.length;
	    if(start > n || end  < 0 || start > end) return -1;
		int mid = (start+end)/2;
		if(A[mid] == e) {
			// we found a match
			if(mid >0) {
				if(A[mid-1] < e) {
					return mid;
				}else {
					return first(start,mid-1,e);
				}
			}else {
				return mid;
			}
		} else if( e < A[mid]) {
			return first(start,mid-1,e);
		} else {
			return first(mid+1,end,e);
		}
				
	}
	
	int last(int start,int end,int e) {
		int n= A.length;
		if(start > n || end  < 0 || start > end) return -1;
		int mid = (start+end)/2;
		if(A[mid] == e) {
			if(mid < n-1) {
				if(A[mid+1] > e) {
					return mid;
				} else {
					return last(mid+1,end,e);
				}
			} else {
				return mid;
			}
		} else if(e<A[mid]) {
			return last(start,mid-1,e);
		} else {
			return last(mid+1,end,e);
		}
	}
	
	
	

	public static void main(String[] args) {
		int[] A = {2,4,4,4,4,4,5,6,7,8};
		CountElementOccurance f = new CountElementOccurance();
		f.A = A;
		int first = f.first(0, A.length, 4);
		int end = f.last(0, A.length, 4);
		int k = end - first +1;
		System.out.println(first+","+end+","+k);
	}
}
