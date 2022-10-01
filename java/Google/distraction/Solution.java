package distraction;

import java.util.Arrays;

public class Solution {

    public static int solution(int[] list) {
        int maxMatching = maxBPM(createMatrix(list));
        // System.out.println("Max:> " + maxMatching);
        return list.length - maxMatching;
    }

    static boolean[][] createMatrix(int[] list) {
        boolean[][] matrix = new boolean[list.length][list.length];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                matrix[i][j] = isInfinite(list[i], list[j]);
            }
        }
        return matrix;
    }

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    static boolean isInfinite(int a, int b) {
        int sum = Math.floorDiv(a + b, gcd(a, b));
        return !((sum & (sum - 1)) == 0);
    }

    // Returns maximum number
    // of matching from M to N
    static int maxBPM(boolean bpGraph[][]) {
        int N = bpGraph.length;
        int M = N;
        // An array to keep track of the
        // wrestler assigned to each other.
        // The value of matchR[i] is the
        // wrestler number assigned to wrestler i,
        // the value -1 indicates nobody is assigned.
        int matchR[] = new int[N];

        // Initially all wrestlers are available
        for (int i = 0; i < N; ++i)
            matchR[i] = -1;

        // Count of wrestlers assigned to each other
        int result = 0;
        for (int u = 0; u < M; u++) {
            // Mark all wrestlers as not seen
            // for next wrestler.
            boolean seen[] = new boolean[N];
            for (int i = 0; i < N; ++i)
                seen[i] = false;

            // Find if the wrestler 'u' can get a match
            if (bpm(bpGraph, u, seen, matchR))
                result++;
        }
        return result;
    }

    static boolean bpm(boolean bpGraph[][], int u,
            boolean seen[], int matchR[]) {
        int N = bpGraph.length;
        // Try every wrestler one by one
        for (int v = 0; v < N; v++) {
            // If wrestler u is can be matched with
            // wrestler v and v is not visited
            if (bpGraph[u][v] && !seen[v]) {

                // Mark v as visited
                seen[v] = true;

                // If wrestler 'v' is not assigned to
                // an opponent OR previously
                // assigned opponent for wrestler v (which
                // is matchR[v]) has an alternate wrestler available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get wrestler 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                        seen, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // System.out.println("\n" + Solution.solution(new int[] { 1, 7, 3, 21, 13, 19
        // }));
        /*
         * false, true,
         */
        System.out.println("\n" + Solution.solution(new int[] { 1, 2, 200000 }));
        // System.out.println("\n" + Solution.solution(new int[] { 1, 1, 1, 2 }));
        for (boolean[] row : Solution.createMatrix(new int[] { 1, 2, 200000 })) {
            System.out.println(Arrays.toString(row));
        }
    }
}
