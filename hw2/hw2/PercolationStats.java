/** This class is used to aid the calculations of
 *  threshold in the percolation experiment.
 *  For more info see: sp18.datastructur.es/materials/hw/hw2
 *
 * @author Adnan H. Mohamed
 */
package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private int N;   // size of the N-by-N grid.
    private int T;   // number of experiments
    private double[] thresholds;

    /* Returns the fraction of sites that are opened
       when the system percolates.
     */
    private double get_threshold(PercolationFactory pf) {
        Percolation p = pf.make(N);
        while (!p.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            p.open(row, col);
        }

        return (double)(p.numberOfOpenSites()) / (N * N);

    }

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be positive!");
        }
        this.N = N;
        this.T = T;
        thresholds = new double[T];

        for (int i = 0; i < T; ++i) {
            thresholds[i] = get_threshold(pf);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double t : thresholds) {
            sum += t;
        }
        return sum / T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double u = mean();
        double numerator = 0;
        for (double t : thresholds) {
            numerator += Math.pow(t - u, 2);
        }
        return numerator / (T - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double sigma = Math.sqrt(stddev());
        return mean() - (1.96 * sigma/(Math.sqrt(T)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double sigma = Math.sqrt(stddev());
        return mean() + (1.96 * sigma/(Math.sqrt(T)));
    }
}

