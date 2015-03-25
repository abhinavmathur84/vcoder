package com.notes.test;

/**
 * Calculate x^n in log n time 
 * @author abhmathu
 *
 */
public class PowerXToN {

    int x;
    int n;
   
    public PowerXToN(int x,int n) {
        this.x =x;
        this.n = n;
    }
    
    int calculate(int num) {
        if(num ==0 ) return 1;
        else {
            int temp = calculate(num/2);
            if(num%2 == 0){
                return temp*temp;
            } else {
                return temp*temp*x;
            }
        }
        
    }
 
    /**
     * @param args
     */
    public static void main(String[] args) {
       
       PowerXToN p = new PowerXToN(2,5);
       System.out.println(p.calculate(5));
       

    }
    
    

}
