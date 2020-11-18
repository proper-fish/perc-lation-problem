/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/5/2020
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
        ufArray = new WeightedQuickUnionUF(n * n - 1);
        grid = new boolean[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                grid[row][col] = false;
            }
        }
    }

    // throws an exception if index is out of bounds
    private void isInputValid(int input) {
        if (input < 0) throw new IllegalArgumentException("n should be 0 or larger");
    }

    private void isInputValid(int col, int row) {
        if (row < 0 || row >= n) throw new IllegalArgumentException("row index out of bounds");
        if (col < 0 || col >= n) throw new IllegalArgumentException("col index out of bounds");
    }

    // translates from 2D grid index to 1D UF data structure index
    private int xyTo1D(int x, int y) {
        return (n * x + y);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        isInputValid(row, col);
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
        }

        if (row > 0 && grid[row - 1][col]) {
            ufArray.union(xyTo1D(row - 1, col), xyTo1D(row, col));
        }
        if (row < 4 && grid[row + 1][col]) {
            ufArray.union(xyTo1D(row + 1, col), xyTo1D(row, col));
        }
        if (col > 0 && grid[row][col - 1]) {
            ufArray.union(xyTo1D(row, col - 1), xyTo1D(row, col));
        }
        if (col < 4 && grid[row][col + 1]) {
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
        boolean res = false;
        for (int i = 0; i < n; i++) {
            if (ufArray.find(xyTo1D(row, col)) == ufArray.find(xyTo1D(0, i))) {
                res = true;
            }
        }
        return res;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // prints the grid
    private void visualize(Percolation percolation) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String res = (percolation.grid[i][j]) ? "⚪️" : "⚫️️";
                System.out.printf("%s ", res);
            }
            System.out.println();
        }
        System.out.println();
    }

    // test client
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.visualize(percolation);
        percolation.open(2, 3);
        percolation.visualize(percolation);
        System.out.printf("The site is full: %s \n \n", percolation.isFull(2, 3));
        percolation.open(2, 2);
        percolation.visualize(percolation);
        System.out.printf("The site is full: %s \n \n", percolation.isFull(2, 2));
        percolation.open(1, 3);
        percolation.visualize(percolation);
        System.out.printf("The site is full: %s \n \n", percolation.isFull(1, 3));
        percolation.open(0, 3);
        percolation.visualize(percolation);
        System.out.printf("The site is full: %s \n \n", percolation.isFull(0, 3));
        System.out.printf("Number of open sites: %s \n \n", percolation.numberOfOpenSites());
    }
}
