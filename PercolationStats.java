/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/5/2020
 **************************************************************************** */

public class PercolationStats {

    int n;
    int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        return 1;
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
    }
}
