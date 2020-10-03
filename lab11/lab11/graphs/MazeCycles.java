package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] cameFrom;
    private boolean hasCycle = false;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        cameFrom = new int[maze.V()];
        int xStart = new Random().nextInt(maze.N()) + 1;
        int yStart = new Random().nextInt(maze.N()) + 1;
        int s = maze.xyTo1D(xStart, yStart);

        cameFrom[s] = s;
        dfs(maze, s);
        announce();
    }

    // Helper methods go here
    public void dfs(Maze m, int v) {
        marked[v] = true;
        for (int node : m.adj(v)) {
            if (!marked[node]) {
                //edgeTo[node] = v;
                cameFrom[node] = v;
                dfs(m, node);
            } else if (cameFrom[node] != v) {
                hasCycle = true;
                return;
            }
        }
    }
}

