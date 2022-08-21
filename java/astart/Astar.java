package astart;
import java.util.*;

public class Astar {

    class Cell {
        int[] parent = new int[2];
        double f, g, h;

        Cell() {
            f = -1;
            g = -1;
            h = -1;
            parent[0] = -1;
            parent[1] = -1;    
        }
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // private boolean isValid(List<List<Integer>> grid, Pair pair) {
        
    // }
    
}
