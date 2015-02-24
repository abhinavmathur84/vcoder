package com.notes.backtracking;

/**
 *  1) Is where I am a solution?
    2) No.  Ok, where can I go from here?  If I can go somewhere, choose a place to go.
    3) Go there.
    5) Was that a solution?  If yes, return true!
    5) If there are remaining places to go, choose one and goto #3.
    6) Out of places to go.  Return false.
 * 
 *
 */
public class SolveSudoKu {

    int[][] sudoku;

    int n;

    int val;

    public SolveSudoKu(int n, int[][] sud) {
        this.n = n;
        val = (int) Math.sqrt(n);
        sudoku = sud;

    }

    boolean isValid(int row, int col, int number) {

        // check if the number is present in this row
        for (int i = 0; i < n; i++) {
            if (sudoku[row][i] == number) return false;
        }

        // check if number is present in this col
        for (int i = 0; i < n; i++) {
            if (sudoku[i][col] == number) return false;
        }

        int boundingUpperRow = (row / val) * val;
        int boundingLowerRow = boundingUpperRow + val - 1;

        int boundingColLeft = (col / val) * val;
        int boundingColRight = boundingColLeft + val - 1;

        for (int i = boundingUpperRow; i <= boundingLowerRow; i++) {
            for (int j = boundingColLeft; j <= boundingColRight; j++) {
                if (sudoku[i][j] == number) return false;
            }
        }
        return true;
    }

    class Place {
        int row;

        int col;

        Place(int r, int c) {
            row = r;
            col = c;
        }
    }

    Place findNext() {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (sudoku[row][col] == -1) {
                    return new Place(row, col);
                }
            }
        }
        return null;
    }

    public boolean fill() {
        Place p = findNext();
        if (p == null) { // leaf node
            print();
            return true;
        } else {
            for (int num = 1; num <= n; num++) {
                if (isValid(p.row, p.col, num)) {
                    sudoku[p.row][p.col] = num; // mark this visited
                    if(fill()) {
                        return true;// go to next level
                    } else {
                        sudoku[p.row][p.col] =-1; // unmark it visited
                        continue;
                    }
                }
            }
            return false;
      }
    }

    void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] model = new int[9][9];
        int n = 9;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                model[i][j] = -1;
            }
        }

        model[0][0] = 9;
        model[0][4] = 2;
        model[0][6] = 7;
        model[0][7] = 5;

        model[1][0] = 6;
        model[1][4] = 5;
        model[1][7] = 4;

        model[2][1] = 2;
        model[2][3] = 4;
        model[2][7] = 1;

        model[3][0] = 2;
        model[3][2] = 8;

        model[4][1] = 7;
        model[4][3] = 5;
        model[4][5] = 9;
        model[4][7] = 6;

        model[5][6] = 4;
        model[5][8] = 1;

        model[6][1] = 1;
        model[6][5] = 5;
        model[6][7] = 8;

        model[7][1] = 9;
        model[7][4] = 7;
        model[7][8] = 4;

        model[8][1] = 8;
        model[8][2] = 2;
        model[8][4] = 4;
        model[8][8] = 6;

        SolveSudoKu solver = new SolveSudoKu(n, model);
        solver.print();

        solver.fill();

    }

}
