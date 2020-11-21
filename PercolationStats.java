/* *****************************************************************************
 *  Name:              Aliona Kozushkina
 *  Coursera User ID:
 *  Last modified:     11/21/2020
 ****************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.List;

public class PercolationStats {

    private int n;
    private int trials;
    private int totalOpenSites;
    private List<Integer> openSitesList;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.n = n;
        this.trials = trials;
        totalOpenSites = 0;
        this.openSitesList = new ArrayList<>(trials);

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("arguments must be greater than 0");

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            totalOpenSites += percolation.numberOfOpenSites();
            openSitesList.add(percolation.numberOfOpenSites());
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return ((double) totalOpenSites) / (n * n * (double) trials);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double res = 0;
        double mean = mean();

        for (int openSites : openSitesList) {
            res += Math.pow(((double) openSites / (n * n)) - mean, 2) / ((double) trials - 1);
        }
        return Math.sqrt(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    // takes two command-line arguments n and T, performs T independent
    // computational experiments on an n-by-n grid, and prints the sample mean,
    // sample standard deviation, and the 95% confidence interval
    // for the percolation threshold
    public static void main(String[] args) {
        String inputStr = StdIn.readLine();
        String[] inputStrSplit = inputStr.split(" ");
        int[] input = new int[] {
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
