package com.notes.backtracking;

/**
 * Created by mathura on 3/2/2015.
 */
public class StringPermutation {

    // the input string
    String str;
    int i=1;
    char[] arr;
    void permute(String s,String path){
    	int length = s.length();
		if(s.length() ==0) {
			System.out.println(i+"\t"+path);
			i++;
		} else {
	    	for(int i=0;i<length;i++) {
				permute(s.substring(0,i)+s.substring(i+1,length), path+s.charAt(i));
	    	}
		}
   }
    
    //IN PALCE PERMUTATION
    void f(int k) {
		if(k==arr.length - 1) {
			print();
		}
		for(int i=k;i<arr.length;i++) {
			swap(k,i);
			f(k+1);
			swap(k,i);
		}
	}
    
    private void print() {
		for(int m : arr) {
			System.out.print(m+", ");
		}
		System.out.println("");
	}

	private void swap(int k, int i) {
		char temp = arr[k];
		arr[k] = arr[i];
		arr[i] = temp;
	}
    // END INPLACE PERMUTATION
    
   //

   
   
   
   public static void main(String[] args) {
	   StringPermutation sp = new StringPermutation();
	   sp.str="abc";
	   sp.permute(sp.str, "");
	   System.out.println("---------------------------");
	   sp.i=1;
	   sp.permute("abcd", "");
   }




}
