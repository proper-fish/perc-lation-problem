/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/21/2020
 ****************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONSTANT = 1.96;
    private final int trials;
    private final double[] openFractionArray;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trials = trials;
        this.openFractionArray = new double[trials];

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("arguments must be greater than 0");

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            openFractionArray[i] = (double) (percolation.numberOfOpenSites()) / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openFractionArray);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openFractionArray);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONSTANT * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONSTANT * stddev()) / Math.sqrt(trials));
    }

    // takes two command-line arguments n and T, performs T independent
    // computational experiments on an n-by-n grid, and prints the sample mean,
    // sample standard deviation, and the 95% confidence interval
    // for the percolation threshold
    public static void main(String[] args) {
        String inputStr = StdIn.readLine();
        if (inputStr.equals(""))
            throw new IllegalArgumentException("arguments not inputted");

        String[] inputStrSplit = inputStr.split(" ");
        int[] input = {
                Integer.parseInt(inputStrSplit[0]),
                Integer.parseInt(inputStrSplit[1])
        };

        int n = input[0];
        int trials = input[1];
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.printf("Mean threshold value: %s\n", stats.mean());
        System.out.printf("Standard deviation value: %s\n", stats.stddev());
        System.out.printf("95%% confidence interval: [%s, %s]\n", stats.confidenceLo(),
                          stats.confidenceHi());
    }
}
