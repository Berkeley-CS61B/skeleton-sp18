package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solver {

    private class SearchNode {
        private Integer priority;
        private int moves;
        private SearchNode prev;
        private WorldState state;

        SearchNode(WorldState state, SearchNode prev) {
            if (prev == null) {
                moves = 0;
            } else {
                moves = prev.moves + 1;
            }
            if (!estDistCache.containsKey(state)) {
                estDistCache.put(state, state.estimatedDistanceToGoal());
            }
            this.prev = prev;
            this.state = state;
            priority = moves + this.state.estimatedDistanceToGoal();
        }
    }


    private class SearchNodeComparator implements Comparator<SearchNode> {
        public int compare(SearchNode l, SearchNode r) {
           return l.priority.compareTo(r.priority);
        }
    }

    private Stack<WorldState> shortestPath;
    private Map<WorldState, Integer> estDistCache = new HashMap<>();

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists. */
    public Solver(WorldState initial) {
        MinPQ<SearchNode> PQ = new MinPQ<>(new SearchNodeComparator());
        SearchNode node = new SearchNode(initial, null);

        while (!node.state.isGoal()) {
            for (WorldState s : node.state.neighbors()) {
                if (node.prev == null || !s.equals(node.prev.state)) {
                    PQ.insert(new SearchNode(s, node));
                }
            }
            node = PQ.delMin();
        }
        shortestPath = new Stack<>();
        for (SearchNode n = node; n != null; n = n.prev) {
            shortestPath.push(n.state);
        }
    }

    /** Returns the minimum number of moves
     * to solve the puzzle starting at the
     * initial WorldState. */
    public int moves() {
        return shortestPath.size() - 1;
    }

    /** Returns a sequence of WorldStates from
     * the initial WorldState to the solution. */
     public Iterable<WorldState> solution() {
         return shortestPath;
     }
}
