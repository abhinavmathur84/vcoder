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
    
    void permuteInPlace(int i) {
    
    }
    
    
   //
   /**
    * 
    * 
    * 
	* abcd                                         c=0
	* bacd
	* bcad
	* bcda  swap=1, till you reach length, index=0,c=1
	* cbda
	* cdba
	* cdab                                        c=2
	* dcab
	* dacb
	* dabc                                        c=3
	* adbc
	* abdc
	* abcd                                        c=4, if start string index=1,swap=1
	* acbd                                        
    * acdb
    * adcb
    * adbc
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    */
    
    
   
   
   
   public static void main(String[] args) {
	   StringPermutation sp = new StringPermutation();
	   sp.str="abc";
	   sp.permute(sp.str, "");
	   System.out.println("---------------------------");
	   sp.i=1;
	   sp.permute("abcd", "");
   }




}
