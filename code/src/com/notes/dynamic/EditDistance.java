package com.notes.dynamic;

public class EditDistance {

    // the x string
    String x;

    // the y strings
    String y;

    // integer array to store the minimum distance between x[1..i] and y[1..j]
    // editDist[i][j] = minimum distance between x[1..i] and y[1..sj]
    int[][] editDist;
    String[][] sols;

    //Approach
    /**
     * editDist[i][j] =  editDist[i-1][j-1]+1 if x[i]= y[j]
     *                   min { editDist[i][j-1] + 1 convert x[1..i] into y[1..j-1] and insert y[j]
     *                         editDist[i-1][j] + 1 convert x[1..i-1] into y[1..j] and delete x[j]
     *                         editDist[i-1][j-1]+1 convert x[1..i-1] into y[1..j-1] and replace x[i] with y[j]
     *                       }
     * Do following initializations
     * editDist[i][0] = i; // for all i as we just need to delete x[i];
     * editDist[0][j] = j; // for all j as we just need to insert y[j];
     * editDist[0][0] = 0  // to convert string of length 0 to 0.
     * <p/>
     * Required solution will be in editDistance[x.length][y.length];
     */
    void calculateEditDist() {
        editDist = new int[x.length() + 1][y.length() + 1];
        sols = new String[x.length() + 1][y.length() + 1];

        // edit distance between 2 string of length 0 is 0.
        editDist[0][0] = 0;
        sols[0][0] = "";
        //editDist[i][0] = i; for all i as we just need to delete x[i];
        initForXintoLengthZero();
        //editDist[0][j] = j; // for all j as we just need to insert y[j];
        intoForLenthZeroIntoY();
        // loop to build the solution from bottom up
        for (int i = 1; i <= x.length(); i++) { // length of string x from 1 to x.length()
            for (int j = 1; j <= y.length(); j++) {// length of string y from 1 to y.length()
                if (x.charAt(i - 1) == y.charAt(j - 1)) { // if current char is same
                    editDist[i][j] = editDist[i - 1][j - 1];  // number of operations are same as that of sub problem
                    sols[i][j] = sols[i - 1][j - 1]; // solution is same as that of sub problem
                } else {
                    // number of operations required for converting string of length i into string of j-1 length and insert y[j]
                    int insert = editDist[i][j - 1] + 1;
                    // number of operations required for converting string of length i-1 into string of j length and delete x[i]
                    int delete = editDist[i - 1][j] + 1;
                    // number of operations required for converting string of length i-1 into string of j-1 length and replace x[i] with y[j]
                    int replace = editDist[i - 1][j - 1] + 1;
                    int min = Math.min(insert, Math.min(delete, replace));
                    editDist[i][j] = min;
                    if (min == insert) {
                        sols[i][j] = sols[i][j - 1] + "Insert " + y.charAt(j - 1) + "\n";
                    } else if (min == delete) {
                        sols[i][j] = sols[i - 1][j] + "Delete " + x.charAt(i - 1) + "\n";
                    } else if (min == replace) {
                        sols[i][j] = sols[i - 1][j - 1] + "Replace " + x.charAt(i - 1) + " with " + y.charAt(j - 1) + "\n";
                    }
                }
            }
        }
        System.out.println("Edit Distance between " + x + " and " + y + " is " + editDist[x.length()][y.length()]);
        System.out.println("Steps are \n" + sols[x.length()][y.length()]);
    }

    private void intoForLenthZeroIntoY() {
        for (int j = 1; j <= y.length(); j++) {
            // edit distance to convert a string of length 0 to string of length j is j.
            editDist[0][j] = j;
            String ops = "";
            for (int k = 0; k < j; k++) {
                // we need to perform j insert operations.
                ops = ops + "Insert " + y.charAt(k) + "\n";
            }
            sols[0][j] = ops;
        }
    }

    private void initForXintoLengthZero() {
        for (int i = 1; i <= x.length(); i++) {
            // edit distance to convert a string of length i to string of length 0 is i.
            editDist[i][0] = i;
            String ops = "";
            for (int k = 0; k < i; k++) {
                // we need to perform i delete operations on x.
                ops = ops + "Delete " + x.charAt(k) + "\n";
            }
            sols[i][0] = ops;
        }
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        ed.x = "abc";
        ed.y = "abc";
        ed.calculateEditDist();

        ed.x = "abc";
        ed.y = "";
        ed.calculateEditDist();

        ed.x = "";
        ed.y = "abc";
        ed.calculateEditDist();

        ed.x = "abd";
        ed.y = "abc";
        ed.calculateEditDist();

        ed.x = "zeil";
        ed.y = "trials";
        ed.calculateEditDist();

    }
}
