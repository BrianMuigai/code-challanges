package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestPath {

    public static int solution(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        if (1 >= row + col- 2)
            return row + col - 2;

        String source = "0,0";
        String target = row - 1 + "," + (col - 1);

        int count = exploreBFS(grid, source, target);
        // nodes = edget + 1
        return count;
    }
    
    public static Map<String, List<String>> toGraph(int[][] grid) {
        Map<String, List<String>> graph = new HashMap<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                String pos = row + "," + col;
                List<String> neighbours = new ArrayList<>();
                if (row != 0)
                    neighbours.add((row - 1) + "," + col);
                if (row != grid.length - 1)
                    neighbours.add((row + 1) + "," + col);
                if (col != 0)
                    neighbours.add(row + "," + (col - 1));
                if (col != grid[0].length - 1)
                    neighbours.add(row + "," + (col + 1));
                graph.put(pos, neighbours);
            }
        }

        return graph;
    }
    
    private static int exploreBFS(int[][] grid, String source, String target) {
        Map<String, List<String>> graph = toGraph(grid);
        Set<String> visited = new HashSet<>();
        Deque<String[]> queue = new ArrayDeque<>();
        queue.add(new String[] { source, "0", "1" });
        visited.add(source);
        
        while (!queue.isEmpty()) {
            String[] curr = queue.poll();
            String node = curr[0];
            int distance = Integer.parseInt(curr[1]);
        
            if (node.equals(target))
                return distance + 1;

            for (String neighbour : graph.get(node)) {
                String[] pos = source.split(",");
                int row = Integer.parseInt(pos[0]);
                int col = Integer.parseInt(pos[1]);
                int breakableWalls = Integer.parseInt(curr[2]);

                if (!visited.contains(neighbour) && grid[row][col] == 0) {
                    visited.add(neighbour);
                    queue.add(new String[] { neighbour, "" + (distance + 1), ""+breakableWalls });
                } else if (breakableWalls > 0 && !visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(new String[] { neighbour, "" + (distance + 1), "" + (breakableWalls - 1) });
                }
            }
        }

        return -1;
    }

    public static int shortestPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int breackableWalls = 1;

        if ((rows + cols - 2) <= 1)
            return rows + cols - 2;
        
        //posible directions
        int[][] directions = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        Set<String> visited = new HashSet<>();
        //[row, col, distance, breackableWalls]
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { 0, 0, 0, breackableWalls });

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];
            int distance = curr[2];
            int walls = curr[3];

            if (row == rows - 1 && col == cols - 1)
                return distance;
            if (visited.contains(row + "," + col + ","+walls))
                continue;
            visited.add(row + "," + col + "," + walls);

            for (int[] move : directions) {
                int r1 = row + move[0];
                int c1 = col + move[1];

                if (r1 < 0 || r1 >= rows || c1 < 0 || c1 >= cols)
                    continue;
                if (grid[r1][c1] == 0 && !visited.contains(r1 + "," + c1 + "," + walls)) {
                    queue.add(new int[] { r1, c1, distance + 1, walls });
                    continue;
                }
                if (walls > 0 && !visited.contains(r1 + "," + c1 + "," + walls))
                    queue.add(new int[] { r1, c1, distance + 1, walls - 1 });

            }

        }
        
        return -1;

    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 1, 1, 0 },
                { 0, 0, 0, 1 },
                { 1, 1, 0, 0 },
                { 1, 1, 0, 0 }
        };
        int[][] grid2 = {
            {0, 0, 0, 0, 0, 0}, 
            {1, 1, 1, 1, 1, 0}, 
            {0, 0, 0, 0, 0, 0}, 
            {0, 1, 1, 1, 1, 1}, 
            {0, 1, 1, 1, 1, 1}, 
            {0, 0, 0, 0, 0, 0}
        };
            
        // System.out.println("Graph: " + ShortestPath.toGraph(grid));
        System.out.println("Shortest Path: " + ShortestPath.solution(grid));
        System.out.println("Shortest Path: " + ShortestPath.solution(grid2));
        // System.out.println("Shortest Path: " + ShortestPath.shortestPath(grid, 1));
        // System.out.println("Shortest Path: " + ShortestPath.shortestPath(grid2, 1));
    }
}
