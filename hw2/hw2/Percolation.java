/** Implementation of the data type used to
 *  model a percolation system.
 *  For more info see: sp18.datastructur.es/materials/hw/hw2
 *
 * @author Adnan H. Mohamed
 */

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashMap;
import java.util.HashSet;

public class Percolation {

    /**  ### CLASS INVARIANT ###
     *   1- grid - represents the N-by-N grid.
     *      The values in grid are: 0, 1, 2.
     *       0: BLOCKED (not open).
     *       1: OPEN (but NOT full)
     *       2: OPEN AND FULL.
     *
     *   2- N is the size of the grid.
     *
     *   3- open_count: number of open sites.
     */

    private int[][] grid;
    private int N;
    private int open_count;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF full_cells;

    /////////   HELPER FUNCTIONS  ///////////
    /** Returns the UNIQUE integer for the
     *  cell at position (r, c).
     *  the (0, 0) cell is 0, (0, 1) is 1 and so forth. */
    private int to_1D(int r, int c) {
        return r * N + c;
    }

    /** Returns the keys for cells which are
     *  open and adjacent to the given cell.
     *
     *  Precondition: 0 <= r < N && 0 <= c < N
     */
    private int[] open_cells(int r, int c) {
        java.util.Set<Integer> cells = new HashSet<>();
        if (c + 1 < N && isOpen(r, c + 1)) {
            cells.add(to_1D(r, c + 1));
        }
        if (r + 1 < N && isOpen(r + 1, c)) {
            cells.add(to_1D(r + 1, c));
        }
        if (r - 1 >= 0 && isOpen(r - 1, c)) {
            cells.add(to_1D(r - 1, c));
        }
        if (c - 1 >= 0 && isOpen(r, c - 1)) {
            cells.add(to_1D(r, c - 1));
        }

        int[] arr = new int[cells.size()];
        int i = 0;
        for (int num: cells) {
            arr[i] = num;
            i++;
        }
        return arr;
    }

    /** Throws IndexOutOfBoundsException if r or c are out-of range. */
    private void validate(int r, int c) {
        if (r < 0 || c < 0 || r >= N || c >= N) {
            throw new IndexOutOfBoundsException("r or c are out-of-bounds!\n");
        }
    }

    /** Returns true iff the cell should be full. */
    private boolean qualifies(int r, int c) {
        if(r == 0)
            return true;
        for (int i = 0; i < N; ++i) {
            int key = to_1D(0, i);
            int key2 = to_1D(r, c);
            if (uf.find(key) == uf.find(key2)) {
                return true;
            }
        }
        return false;
    }
    //////////////////////////////////////////

    /** create N-by-N grid, with all sites initially blocked
     *
     * @param N  the sides length of the grid.
     */
    public Percolation(int N)
    {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive!");
        }
        this.N = N;
        grid = new int[N][N];
        for (int i = 0; i < N; ++i) {
            grid[i] = new int[N];
        }
        open_count = 0;
        uf = new WeightedQuickUnionUF(N * N);
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            for (int key: open_cells(row, col)) {
                uf.union(to_1D(row, col), key);
            }
            open_count += 1;
        }
    }

    /** Returns true iff site (row, col) is open. */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] == 1;
    }

    /** Returns true iff the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        validate(row, col);
        for (int i = 0; i < N; ++i) {
            int key = to_1D(0, i);
            int key2 = to_1D(row, col);
            if (uf.find(key) == uf.find(key2)
                && isOpen(0, i)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the number of open sites. */
    public int numberOfOpenSites() {
        return open_count;
    }

    /** Returns true iff the system percolate. */
    public boolean percolates() {
        for (int c = 0; c < N; ++c) {
            if (isFull(N - 1, c)) {
                return true;
            }
        }
        return false;
    }

    /** required for autograder. (optionally used for testing). */
    public static void main(String[] args) {
        Percolation pf = new Percolation(10);
        pf.open(1, 2);
        pf.open(3, 4);
        pf.open(3, 3);
        org.junit.Assert.assertEquals(3, pf.numberOfOpenSites());
    }
}
