package com.notes.dynamic;

import java.util.*;
/**
 * Partition the multiset "S" of positive integers into two subsets S1, S2
 * such that the difference between the sum of elements in S1 and the sum
 * of elements in S2 is minimized.
 * For example if A={1,5,4,9,6,3} the we can take S1= {1,4,6,3} and S2={5,9}
 * then sum(S1)-sum(S2)=0
 *
 * NOTE: THIS IS BRUTE FORCE. LOOK FOR OPTIMAL SOLUTION
 */
public class BalancedPartisionOfArray {


    int[] A;

    class SetSum {
        private List<Integer> set;
        int sum;

        SetSum() {
            set = new ArrayList<Integer>();
            sum = 0;
        }

        public void print(){
            System.out.print("{");
            for(int i=0;i<set.size();i++) {
                if(i!=set.size()-1)
                    System.out.print(set.get(i)+",");
                else
                    System.out.print(set.get(i));
            }
            System.out.print("}");
      }
    }

    class SetPair {
        SetSum s1;
        SetSum s2;

        SetPair(SetSum s1,SetSum s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }

    // sets.get(i) contains the possible division when array is of length i
    List<List<SetPair>> sets = new ArrayList<List<SetPair>>();

    // sols[i] is the solution for array of length i
    Map<Integer,SetPair> sols = new HashMap<Integer,SetPair>();
    //Approach
    /**
     * sets.get(0) = {a[0],phi}
     * sets.get(1) = {a[0]a[1],phi},{a[0],a[1]}
     * sets.get(2) = {a[0]a[1]a[2],phi}{a[0]a[1],a[2]} {a[0]a[2],a[1]} {a[0],a[1]a[2]}
     *
     * Assume we have calculated sets.get(i);
     * now when we have i+1;
     *   List<SetPair> l = new ArrayList<SetPair>();
     *   for(each s as setPair in sets.get(i)){
     *      S1= append( s.S1,a[i+1}) // append new element once in S1
     *      S2= append (s.S2,a[i+1]) // append new element once in S2
     *      l.add(new SetPair(S1,S2))// append new pair
     *      }
     *   sets.add(l,i+1); add this to i+1 positions
     *
     *
     * Build the solution bottom up by increasing j from 0 to n-1
     *
     */

    public void partitionArray() {
        List<SetPair> base = new ArrayList<SetPair>();
        SetSum s = new SetSum();
        s.set.add(A[0]);
        s.sum = A[0];
        SetPair p = new SetPair(s,new SetSum());
        base.add(p);
        sets.add(base);
        sols.put(0, p);
        int n = A.length;

        for(int i=1;i<n;i++) {
            // get the previous list of pairs
            List<SetPair> existingPairs = sets.get(i-1);
            // create a new list to store pairs for this level
            List<SetPair> nextLevel = new ArrayList<SetPair>();
            Iterator<SetPair> itr = existingPairs.iterator();
            while(itr.hasNext()) { // iterate over previous pairs
                SetPair ep = itr.next(); // pick a pair
                SetSum s1 = ep.s1;
                SetSum s2 = ep.s2;
                SetSum p1 = append(s1,A[i]); // add the element to first set
                SetSum p2 = append(s2,A[i]); // add the element to second set
                // make a new pair with element added to first set keeping second set same
                SetPair pair1 = new SetPair(p1,s2);
                // make a new pair with element added to second set keeping first set same
                SetPair pair2 = new SetPair(s1,p2);
                if(nextLevel.size() == 0) { // if no pairs have been added
                    // compare and put the pair as solution
                    if(compare(pair1, pair2)) {
                        sols.put(i, pair1);
                    } else {
                        sols.put(i,pair2);
                    }
                } else {
                    // compare with current solution and put it
                     SetPair currentSol = sols.get(i);
                      if(compare(pair1, pair2)){
                          if(compare(pair1, currentSol)){
                              sols.put(i, pair1);
                          }
                      }else {
                          if(compare(pair2, currentSol)){
                              sols.put(i,pair2);
                          }
                      }
                }
                // add both the pairs in the current list
                nextLevel.add(pair1);
                nextLevel.add(pair2);
            }
            sets.add(i,nextLevel);
        }

        SetPair ans = sols.get(n - 1);
        System.out.println("----------------");
        ans.s1.print();
        System.out.println();
        ans.s2.print();
        System.out.println();
        System.out.println("Difference = "+Math.abs(ans.s1.sum-ans.s2.sum));

    }

    public boolean compare(SetPair p1,SetPair p2) {
        if(Math.abs((p1.s1.sum-p1.s2.sum))< Math.abs(p2.s1.sum-p2.s2.sum)) {
            return true;
        }
        return false;
    }


    SetSum append(SetSum s,int k) {
           SetSum ret = new SetSum();
           ret.set.addAll(s.set);
           ret.sum = s.sum;
           ret.set.add(k);
           ret.sum = ret.sum+k;
           return ret;
    }

    private void printSet(SetSum S) {
        for(int i=0;i<S.set.size();i++) {
            System.out.print(S.set.get(i) + ",");
        }
    }



    public void partitionArrayRecursive() {
        int sum1; // sum of first subset
        int sum2; // sum of second subset
       // now aim is to minimize |sum1-sum2|
        // diff(i) -> gives minimum difference till A[i],with sum1 and sum2
        // either add the next element in sum1 or in sum2
        // diff(i+1) = min {diff(i,sum1+a[i],sum2) diff(i,sum1,sum2+a[i])}
        // when i =n diff(n) = sum1-sum2 ;// required solution
        // when i =0 diff(0)= a[0]
        System.out.println("Recursive Difference = "+ diff(0,0,0));
    }


    int diff(int index,int sum1,int sum2) {
        if(index == A.length) {
           return Math.abs(sum1-sum2);
        }else {
            int t1 = diff(index+1,sum1+A[index],sum2);// add next element in sum1
            int t2 = diff(index+1,sum1,sum2+A[index]);// add next element in sum2
            if(t1<t2){
               return t1;
            }else {
                return t2;
            }

        }
    }

    public static void main(String[] args) {
        BalancedPartisionOfArray bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{1,5,4,9,6,3};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();
        System.out.println();

        bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{1,7,4,11};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();
        System.out.println();

        bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{5,6,1};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();

        bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{5};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();

        bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{5,6};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();

        bpa = new BalancedPartisionOfArray();
        bpa.A = new int[]{3,1,1,2,2,1};
        bpa.partitionArray();
        bpa.partitionArrayRecursive();


    }

}
