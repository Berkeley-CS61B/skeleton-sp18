package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int HEIGHT = 50;
    private static final int WIDTH = 50;

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** Makes an empty world of dimensions WIDTH and HEIGHT. */
    private static void emptyWorld(TETile[][] world, int width, int height) {
        for (int w = 0; w < width; ++w) {
            for (int h = 0; h < height; ++h) {
                world[w][h] = Tileset.NOTHING;
            }
        }
    }

    /** Calculates the number of cells to be filled
     *  to create a hexagon.
     */
    static int[] cells(int n) {
        int[] arr = new int[n * 2];
        int x = n;
        for (int i = 0; i < n; ++i) {
            arr[i] = x;
            x += 2;  // add one to right and left.
        }
        x -= 2;
        for (int i = n; i < arr.length; ++i) {
            arr[i] = x;
            x -= 2;  // add one to right and left.
        }

        return arr;
    }

    /** Returns the positions of the starting entries
     * for the hexagon.
     */
    static Position[] positions(int s, Position init) {
        Position[] pos_arr = new Position[s * 2];
        int i = 0;
        int w = init.x;
        int h = init.y;
        while (i < s) {
            pos_arr[i] = new HexWorld.Position(w, h);
            w--;
            h++;
            i++;
        }
        ++w;
        while (i < pos_arr.length) {
            pos_arr[i] = new HexWorld.Position(w, h);
            w++;
            h++;
            i++;
        }

        return pos_arr;
    }

    public static void addHexagon(TETile[][] world, Position p, int s) {
        int lines = s;
        int[] entries = cells(s);
        Position[] pos_arr = positions(s, p);
        emptyWorld(world, WIDTH, HEIGHT);
        for (int i = 0; i < entries.length; ++i) {
            int x = pos_arr[i].x;
            int y = pos_arr[i].y;
            for (int j = 0; j < entries[i]; ++j) {
                world[x + j][y] = Tileset.WALL;
            }
        }

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        ter.renderFrame(world);
    }

    public static void main(String[] args) {
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        addHexagon(world, new Position(25, 25), 10);
    }
}
