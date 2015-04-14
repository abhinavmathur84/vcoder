package com.notes.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mathura on 2/23/2015.
 */
public class Rounding {

    public static void main(String[] args) {
        double d1 = 1.5401;
        double d2 = 1.54007;
        int sp =4;
        double t1 = round(d1,sp);
        double t2 = round(d2,sp);
        System.out.println(t1);
        System.out.println(t2);

    }

    /**
     * Round the given value to the specified number of decimal places. The
     * value is rounded using the {@link BigDecimal#ROUND_HALF_UP} method.
     *
     * @param x the value to round.
     * @param scale the number of digits to the right of the decimal point.
     * @return the rounded value.
     * @since 1.1
     */
    public static double round(double x, int scale) {
        return round(x, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Round the given value to the specified number of decimal places. The
     * value is rounded using the given method which is any method defined in
     * {@link BigDecimal}.
     *
     * @param x the value to round.
     * @param scale the number of digits to the right of the decimal point.
     * @param roundingMethod the rounding method as defined in
     *        {@link BigDecimal}.
     *        
     *        
     *        
     *   List<> arrays
     *   
     *   f(int depth,String str) {
     *   	int n = arrays.size()-1;
     *   	if(depth == n))
     *   	{
     *   		String[] a = arrays.get(n);
     *   		for(int i=0;i<a.length;i++){
     *   			System.out.println(str+" "+a[i]);
     *   		}
     *   	}else {
     *   		String[] a = arrays.get(depth);
     *          for(int i=0;i<a.length;i++) {
     *          	f(depth+1,str+" "+a[i]);
     *          }
     *   	}
     *   }
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
     * @return the rounded value.
     * @since 1.1
     */
    public static double round(double x, int scale, int roundingMethod) {
        try {
            return (new BigDecimal
                    (Double.toString(x))
                    .setScale(scale, roundingMethod))
                    .doubleValue();
        } catch (NumberFormatException ex) {
            if (Double.isInfinite(x)) {
                return x;
            } else {
                return Double.NaN;
            }
        }
    }


}

