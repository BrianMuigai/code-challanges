package expandingNebula;

public class Solution {
    private static class Cell {
        public int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int solution(boolean[][] g) {
        for (int row = 0; row < g.length - 1; row++) {
            for (int col = 0; col < g[row].length - 1; col++) {
                System.out.print(hasGas(new Cell(row, col), g) + " ");
            }
            System.out.println();
        }
        return 0;
    }

    private static boolean hasGas(Cell cell, boolean[][] g) {
        Cell[] neighbours = new Cell[4];
        neighbours[0] = new Cell(cell.row + 1, cell.col);
        neighbours[1] = new Cell(cell.row, cell.col + 1);
        neighbours[2] = new Cell(cell.row + 1, cell.col + 1);
        neighbours[3] = cell;

        int count = 0;
        for (Cell target : neighbours) {
            if (g[target.row][target.col]) {
                count++;
            }
        }

        return count == 1;
    }

    public static void main(String[] args) {
        Solution.solution(new boolean[][] {
                { false, true, false, false },
                { false, false, true, false },
                { false, false, false, true },
                { true, false, false, false }
        });
    }
}