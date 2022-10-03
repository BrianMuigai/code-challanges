package distraction;

/*
 * You will set up simultaneous thumb wrestling matches. In each match, two guards will pair off to thumb wrestle. The guard with fewer bananas will bet all their bananas, and the other guard will match the bet. The winner will receive all of the bet bananas. You don't pair off guards with the same number of bananas (you will see why, shortly). You know enough guard psychology to know that the one who has more bananas always gets over-confident and loses. Once a match begins, the pair of guards will continue to thumb wrestle and exchange bananas, until both of them have the same number of bananas. Once that happens, both of them will lose interest and go back to guarding the prisoners, and you don't want THAT to happen!

For example, if the two guards that were paired started with 3 and 5 bananas, after the first round of thumb wrestling they will have 6 and 2 (the one with 3 bananas wins and gets 3 bananas from the loser). After the second round, they will have 4 and 4 (the one with 6 bananas loses 2 bananas). At that point they stop and get back to guarding.

How is all this useful to distract the guards? Notice that if the guards had started with 1 and 4 bananas, then they keep thumb wrestling! 1, 4 -> 2, 3 -> 4, 1 -> 3, 2 -> 1, 4 and so on.

Now your plan is clear. You must pair up the guards in such a way that the maximum number of guards go into an infinite thumb wrestling loop!
Write a function solution(banana_list) which, given a list of positive integers depicting the amount of bananas the each guard starts with, returns the fewest possible number of guards that will be left to watch the prisoners. Element i of the list will be the number of bananas that guard i (counting from 0) starts with.

The number of guards will be at least 1 and not more than 100, and the number of bananas each guard starts with will be a positive integer no more than 1073741823 (i.e. 2^30 -1). Some of them stockpile a LOT of bananas.
 */
//base https://www.geeksforgeeks.org/maximum-bipartite-matching/
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
            boolean seenLeft[] = new boolean[N];
            for (int i = 0; i < N; ++i) {
                seen[i] = false;
                seenLeft[i] = false;
            }

            // Find if the wrestler 'u' can get a match
            if (bpm(bpGraph, u, seen, seenLeft, matchR))
                result++;
        }
        return result;
    }

    static boolean bpm(boolean bpGraph[][], int u,
            boolean seen[], boolean seenLeft[], int matchR[]) {
        int N = bpGraph.length;
        // Try every wrestler one by one
        for (int v = 0; v < N; v++) {
            // If wrestler u is can be matched with
            // wrestler v and v is not visited
            if (bpGraph[u][v] && !seen[v] && !seenLeft[u]) {

                // Mark v as visited
                seen[v] = true;
                if (N % 2 != 0)
                    seen[u] = true;

                // If wrestler 'v' is not assigned to
                // an opponent OR previously
                // assigned opponent for wrestler v (which
                // is matchR[v]) has an alternate wrestler available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get wrestler 'v' again
                if (matchR[v] < 0 || bpm(bpGraph, matchR[v],
                        seen, seenLeft, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Solution.solution(new int[] { 1, 1 }));
        System.out.println("\n" + Solution.solution(new int[] { 1, 7, 3, 21, 13, 19
        }));
        System.out.println(Solution.solution(new int[] { 1, 2, 200000 }));
        System.out.println("\n" + Solution.solution(new int[] { 1, 1, 1, 2 }));
    }
}
