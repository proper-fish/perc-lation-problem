/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/20/2020
 ****************************************************************************/

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private int n;
    private int trials;
    private int totalOpenSites;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        totalOpenSites = 0;

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("arguments must be greater than 0");

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            totalOpenSites += percolation.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return ((double) totalOpenSites) / (n * n * (double) trials);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return 1;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return 1;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 1;
    }

    // takes two command-line arguments n and T, performs T independent
    // computational experiments on an n-by-n grid, and prints the sample mean,
    // sample standard deviation, and the 95% confidence interval
    // for the percolation threshold
    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(10, 10);
        System.out.printf("Mean threshold value: %s", stats.mean());
    }
}
