package com.notes.backtracking;

/**
 * Given an undirected graph and a number m, determine if the graph can be
 * colored with at most m colors such that no two adjacent vertices of the graph
 * are colored with same color. Here coloring of a graph means assignment of
 * colors to all vertices.
 * 
 * Input: 1) A 2D array graph[V][V] where V is the number of vertices in graph
 * and graph[V][V] is adjacency matrix representation of the graph. A value
 * graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j]
 * is 0. 2) An integer m which is maximum number of colors that can be used.
 * 
 * Output: An array color[V] that should have numbers from 1 to m. color[i]
 * should represent the color assigned to the ith vertex. The code should also
 * return false if the graph cannot be colored with m colors.
 * 
 * @author mathura
 *
 */
public class ColoringProblem {

	int[][] graph;
	int V;
	int m;

	int[] color;

	// Approach
	/**
	 * Assume we color a vertex, check if there exists a color for each of
	 * connecting vertex, then return true; else retrun false;
	 * 
	 * so we need canColor(v,c) which return true if v can be colored with color
	 * c Now have a loop which checks for first vertex for all colors
	 * 
	 * 
	 */

	
	boolean canColor(int vertex, int col) {
		int[] adjustantVertx = getAdjustantVertex(vertex);
		// if any of the adjusant vertext has color col return false;
		for (int i = 0; i < adjustantVertx.length; i++) {
			if (adjustantVertx[i] == 1) { // if there is an edge
				if (color[i] == col) { // check the color of ith vertex
					return false; // if same return false
				}
			}
		}
		// if no such vertex found, we can color vertex with col
		// so return true
		return true;
	}

	// The main DFS code
	boolean solve(int vertex) {
		if (vertex == V - 1) { // last vertex
			for (int i = 0; i < m; i++) { // given list of colors
				if (canColor(vertex, i)) { // we check for each color if it can be used
					color[vertex] = i;
					return true; // if we find one, return true;
				}
			}
			// else no color found return false;
			return false;
		} else {
			for (int i = 0; i < m; i++) { // for each color
				boolean canColor = canColor(vertex, i);
				System.out.println("Can color for vertex "+vertex +" with color "+i+" is "+canColor);
				if (canColor) {// check if it can be used
					color[vertex] = i;
					if (solve(vertex + 1)) { // check if child is solvable
						return true;
					}
				}
			}
		}
		// if no such color found
		return false;
	}

	int[] getAdjustantVertex(int vertex) {
		return graph[vertex];
	}
	
	//v1-> v2,v3,v4
	//v2-> v1,v3
	//v3-> v1,v2,v4
	//v4-> v1,v3

	public static void main(String[] args) {
		int[][] graph = { { 0, 1, 1, 1 }, { 1, 0, 1, 0 }, { 1, 1, 0, 1 },
				{ 1, 0, 1, 0 }, };
		int m = 3; // Number of colors
		ColoringProblem c = new ColoringProblem();
		c.color = new int[] {-1,-1,-1,-1};
		c.graph = graph;
		c.m = m;
		c.V = graph[0].length;

		
		if (c.solve(0)) {
			System.out.println("true");
			for (int i = 0; i < c.color.length; i++) {
				System.out.print(c.color[i]+1 + "\t");
			}
		} else {
			System.out.println("false");
		}

	}

}
