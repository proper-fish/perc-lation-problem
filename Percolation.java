/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/19/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private boolean[][] grid;
    private WeightedQuickUnionUF ufArray;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        isInputValid(n);
        ufArray = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n + 1][n + 1];

        for (int row = 0; row < n + 1; row++) {
            for (int col = 0; col < n + 1; col++) {
                grid[row][col] = false;
            }
        }
    }

    // throws an exception if index is out of bounds
    private void isInputValid(int input) {
        if (input <= 0) throw new IllegalArgumentException("n should be 0 or larger");
    }

    private void isInputValid(int col, int row) {
        if (row < 1 || row > n) throw new IllegalArgumentException("row index out of bounds");
        if (col < 1 || col > n) throw new IllegalArgumentException("col index out of bounds");
    }

    // translates from 2D grid index to 1D UF data structure index
    private int xyTo1D(int x, int y) {
        return (n * (x - 1) + (y - 1));
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isInputValid(row, col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
        }

        if (row == 1) {
            ufArray.union(n * n, xyTo1D(row, col));   // n * n is a virtual top site
        }
        if (row == n) {
            ufArray.union(n * n + 1, xyTo1D(row, col));   // n * n + 1 is a virtual bottom site
        }

        if (row > 1 && grid[row - 1][col]) {
            ufArray.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        }
        if (row < n && grid[row + 1][col]) {
            ufArray.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        }
        if (col > 1 && grid[row][col - 1]) {
            ufArray.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        }
        if (col < n && grid[row][col + 1]) {
            ufArray.union(xyTo1D(row, col + 1), xyTo1D(row, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isInputValid(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isInputValid(row, col);
        return ufArray.find(xyTo1D(row, col)) == ufArray.find(n * n);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return ufArray.find(n * n + 1) == ufArray.find(n * n);
    }

    // prints the grid
    private void visualize(Percolation percolation) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                String res = (percolation.grid[i][j]) ? "⚪️" : "⚫️️";
                System.out.printf("%s ", res);
            }
            System.out.println();
        }
        System.out.println();
    }

    // test client
    public static void main(String[] args) {
        // Percolation percolation = new Percolation(5);
        // percolation.visualize(percolation);
    }
}
