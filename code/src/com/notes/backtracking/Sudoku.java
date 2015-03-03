package com.notes.backtracking;


public class Sudoku {
	int[][] sudoku;
	int n =9;
	int val =3;

	class Place{
		int r;
		int c;
		Place(int r,int c){
			this.r = r;
			this.c = c;
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
	
    //Approach
	/*
	 * boolean solve(Node n) {
	    if n is a leaf node {
	        if the leaf is a goal node, return true
	        else return false
	    } else {
	        for each child c of n {
	            if solve(c) succeeds, return true
	        }
	        return false
	    }
	}
	 
	If any child of n is solvable, then n is solvable.
	If no child of n is solvable, then n is not solvable.
	Here Node is a combination of row,col ie place
	solve(place) = isValid(place);
	So to solve a place, we first check if among all possible values which value can be put there
	Once we have a possible value, we see if the next place, ie its child can be solved with this
	if yes then ok, else we erase the value
	
	The main method keeps calling this for first Place with all possible n
	*/
	
	boolean place(Place p,int v) {
		if(p == null) return true;
		int row = p.r;
		int col = p.c;
		if(isLeaf(p)) {
			if(isValid(row, col, v)) {
				sudoku[row][col] = v;
				print();
				return true;
			}
			return false;
		} else {
			// the next empty space is the child for this row,col
			if(isValid(row, col, v)) {
				sudoku[row][col] = v;
				p = findNext();
				for(int i=1;i<=n;i++) {
					if(place(p, i)) {
						return true; // it can be solved
					}
				}
				sudoku[row][col]=-1;
				return false;
			}
			return false;
		}
	}

	
	
	boolean isLastRow(int row) {
		return row == n-1;
	}
	
	boolean isLastCol(int col){
		return col == n-1;
	}
	 
	 void print() {
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                System.out.print(sudoku[i][j] + " ");
	            }
	            System.out.println();
	        }
	    }

	
	
	public boolean isLeaf(Place p) {
		return findNext() == null;
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

	        Sudoku solver = new Sudoku();
	        solver.n =9;
	        solver.sudoku = model;
	        solver.print();
	        Place p1 = solver.findNext();
	        for(int i=0;i<=n;i++) {
	        	solver.place(p1,i);
	        }
	
	   }

}
