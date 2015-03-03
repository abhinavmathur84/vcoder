package com.notes.test;

/**
 * Given S1...Sn as subjects
 * find min slots to schedule
 */
public class ScheduleExam {

    int nSubject;
    //sS[i][j] = 1 if ith and jth subject has common students
    int[][] sS;

    // slots required to schedule i subjects
    int[] sols;


    //Approach
    /**
     * sols[i] = sols[i-1] if for all k<i sS[i][k] =0; so no common student for any of the existing subjects
     *                        and new student
     *        =  sols[i-1]+z  for(all the ss[i][k]!=0 z++)
     *
     * sols[0] = 0 for 0 subjects no slots are required
     * sols[1] = 1 for 1 subject 1 slot is required
     *
     */

    void findSlots() {
        sols = new int[nSubject+1];
        sols[0] =0;
        sols[1] = 1;

        for(int i=1;i<=nSubject;i++) {
            if(sS[i][i-1] == 1 ){
                sols[i] = sols[i-1]+1;
            } else {
                sols[i] = sols[i-1];
            }
        }

    }
}
