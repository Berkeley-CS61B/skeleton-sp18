/** This class deals with the problem of finding
 *  wether 2 (or more) vertices are connected; which
 *  is a problem that rises in many contexts especially
 *  networking.
 *
 * @author Adnan H. Mohamed
 *
 *   ### Class Invariant ###
 *   1- _parent[i] == the index of the parent of the ith element.
 *
 *   2- abs(_parents[find(v1)]) is the size of the set v1 belongs to.
 */
import java.util.HashSet;
import java.util.Set;

public class UnionFind {

    /** contains the parent for each vertex. */
    private int[] _parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        _parents = new int[n];
        for (int i = 0; i < n; ++i) {
            _parents[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= _parents.length || vertex < 0) {
            throw new IllegalArgumentException("DON'T BREAK THE RULES!\n");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        return -_parents[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return _parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);

        if (sizeOf(v2) < sizeOf(v1)) {
            /** find v1's root. */
            int p1 = find(v1);
            /** make the parent of v2's root
             be v1's root. */
            int p2 = find(v2);
            _parents[p1] -= sizeOf(p2);
            _parents[p2] = p1;
        } else {
            /** find v2's root. */
            int p2 = find(v2);
            /** make the parent of v1's root
             be v2's root. */
            int p1 = find(v1);
            _parents[p2] -= sizeOf(v1);
            _parents[p1] = p2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        Set<Integer> nodes = new HashSet<>();

        /** remember the nodes for Path-compression. */
        int n = vertex;  /* n for node. */
        for (int i = 0; _parents[n] >= 0; n = parent(n)) {
            nodes.add(n);
        }
        for (int node:nodes) {
            _parents[node] = n;
        }

        return n;
    }

}
