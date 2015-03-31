package com.notes.array;

public class MaxDistance {

	int[] A;

	int[] findMin() {
		int[] ret = new int[A.length];
		ret[0] = A[0];
		for(int i=1;i<A.length;i++) {
			if(A[i]<ret[i-1]) {
				ret[i] = A[i];
			}else {
				ret[i] = ret[i-1];
			}
		}
		
		
		return ret;
	}
	
	int findDist(int[] min,int[] max) {
		int ret =0;
		int n= A.length;
		for(int i=0,j=0;i<n&&j<n;){
			if(min[i]<max[j]){
				ret = Math.max(ret, j-i);
				j++;
			} else {
				i++;
			}
		}
		return ret;
		
		
	}
	int[] findMax() {
		int[] ret = new int[A.length];
		ret[A.length-1] = A[A.length-1];
		for(int i=A.length-2;i>=0;i--) {
			if(A[i]>ret[i+1]) { // found a new max
				ret[i] = A[i];
			} else {
				ret[i] = ret[i+1];
			}
		}
		return ret;
	}

	
	public static void main(String[] args) {
		MaxDistance md = new MaxDistance();
		md.A = new int[]{1,2,3};
		System.out.println(md.findDist(md.findMin(), md.findMax()));
		
		md.A = new int[]{3,2,1};
		System.out.println(md.findDist(md.findMin(), md.findMax()));
		
		md.A = new int[]{1000,1,2,3};
		System.out.println(md.findDist(md.findMin(), md.findMax()));
		
		md.A = new int[]{1000,1,2,3,2000};
		System.out.println(md.findDist(md.findMin(), md.findMax()));
		
		md.A = new int[]{1,1,1,1};
		System.out.println(md.findDist(md.findMin(), md.findMax()));
	}
}
