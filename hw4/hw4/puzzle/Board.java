package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] tiles;
    private final int N;
    private int[] solution;
    private final int BLANK = 0;
    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j.
     Precondition: tiles is an N-by-N array containing the N^2
                   integers 0 through (N^2 - 1) where 0 represents
                   blank. */
    public Board(int[][] tiles) {
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                this.tiles[r][c] = tiles[r][c];
            }
        }
        solution = new int[N*N];
        for (int i = 0; i < N*N - 1; ++i) {
            solution[i] = i + 1;
        }
        solution[N*N - 1] = BLANK;
    }

    /** Returns value of tile at row i, column j (or 0 if blank).
     * Precondition: i & j are in [0, N - 1]. */
    public int tileAt(int i, int j) {
        validate(i);
        validate(j);
        return tiles[i][j];
    }

    /** Returns the board size N. */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board. *
     * @source Josh Hug's code from the course website.
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate described below. */
    public int hamming() {
        int count = 0;  // count the number of numbers
                        // in the wrong positions.
        for (int r = 0; r < size(); ++r) {
            for (int c = 0; c < size(); ++c) {
                if (tileAt(r, c) != solution[to1D(r, c)]) {
                    count++;
                }
            }
        }
        return count / 2;
    }

    /** Manhattan estimate described below. */
    public int manhattan() {
        int answer = 0;
        for (int c = 0; c < size(); c++) {
            for (int r = 0; r < size(); r++) {
                int x = tileAt(r, c) == 0 ? N * N -1: tileAt(r, c) - 1;
                int r2 = x / N;
                int c2 = x - N * r2;

                answer += Math.abs(r - r2) + Math.abs(c - c2);
            }
        }
        return answer / 2;
    }

    /** Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope.*/
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
     position as y's. */
    public boolean equals(Object y) {
        Board obj = (Board) y;
        if (obj.size() != size()) {
            return false;
        }
        for (int r = 0; r < size(); r++) {
            for (int c = 0; c < size(); c++) {
                if (this.tileAt(r, c) != obj.tileAt(r, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private void validate(int i) {
        if (i < 0 || i >= N) {
            throw new IndexOutOfBoundsException(i + " is out-of-bounds.");
        }
    }

    private int to1D(int r, int c) {
        return r * N + c;
    }
}
