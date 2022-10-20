package escapePods;

import java.util.Arrays;

/**
 * You've blown up the LAMBCHOP doomsday device and relieved the bunnies of
 * their work duries -- and now you need to escape from the space station as
 * quickly and as orderly as possible! The bunnies have all gathered in various
 * locations throughout the station, and need to make their way towards the
 * seemingly endless amount of escape pods positioned in other parts of the
 * station. You need to get the numerous bunnies through the various rooms to
 * the escape pods. Unfortunately, the corridors between the rooms can only fit
 * so many bunnies at a time. What's more, many of the corridors were resized to
 * accommodate the LAMBCHOP, so they vary in how many bunnies can move through
 * them at a time.
 * 
 * Given the starting room numbers of the groups of bunnies, the room numbers of
 * the escape pods, and how many bunnies can fit through at a time in each
 * direction of every corridor in between, figure out how many bunnies can
 * safely make it to the escape pods at a time at peak.
 * 
 * Write a function solution(entrances, exits, path) that takes an array of
 * integers denoting where the groups of gathered bunnies are, an array of
 * integers denoting where the escape pods are located, and an array of an array
 * of integers of the corridors, returning the total number of bunnies that can
 * get through at each time step as an int. The entrances and exits are disjoint
 * and thus will never overlap. The path element path[A][B] = C describes that
 * the corridor going from A to B can fit C bunnies at each time step. There are
 * at most 50 rooms connected by the corridors and at most 2000000 bunnies that
 * will fit at a time.
 * 
 * For example, if you have:
 * entrances = [0, 1]
 * exits = [4, 5]
 * path = [
 * [0, 0, 4, 6, 0, 0], # Room 0: Bunnies
 * [0, 0, 5, 2, 0, 0], # Room 1: Bunnies
 * [0, 0, 0, 0, 4, 4], # Room 2: Intermediate room
 * [0, 0, 0, 0, 6, 6], # Room 3: Intermediate room
 * [0, 0, 0, 0, 0, 0], # Room 4: Escape pods
 * [0, 0, 0, 0, 0, 0], # Room 5: Escape pods
 * ]
 * 
 * Then in each time step, the following might happen:
 * 0 sends 4/4 bunnies to 2 and 6/6 bunnies to 3
 * 1 sends 4/5 bunnies to 2 and 2/2 bunnies to 3
 * 2 sends 4/4 bunnies to 4 and 4/4 bunnies to 5
 * 3 sends 4/6 bunnies to 4 and 4/6 bunnies to 5
 * 
 * So, in total, 16 bunnies could make it to the escape pods at 4 and 5 at each
 * time step. (Note that in this example, room 3 could have sent any variation
 * of 8 bunnies to 4 and 5, such as 2/6 and 6/6, but the final solution remains
 * the same.)
 * 
 * Another example
 * 
 * entrances = [0]
 * exits = [3]
 * path = [
 * [0, 7, 0, 0], # Room 0: Bunnies
 * [0, 0, 6, 0], # Room 1: Intermediate room
 * [0, 0, 0, 8], # Room 2: Intermediate room
 * [9, 0, 0, 0], # Room 3: Escape pods
 * ]
 * 
 * 0 sends 7/7 bunnies to 1
 * 1 sends 6/6 bunnies to 2
 * 2 sends 6/8 bunnies to 3
 * 
 * Languages
 * =========
 * 
 * To provide a Java solution, edit Solution.java
 * To provide a Python solution, edit solution.py
 * 
 * Test cases
 * ==========
 * Your code should pass the following test cases.
 * Note that it may also be run against hidden test cases not shown here.
 * 
 * -- Java cases --
 * Input:
 * Solution.solution({0, 1}, {4, 5}, {{0, 0, 4, 6, 0, 0}, {0, 0, 5, 2, 0, 0},
 * {0, 0, 0, 0, 4, 4}, {0, 0, 0, 0, 6, 6}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0,
 * 0}})
 * Output:
 * 16
 * 
 * Input:
 * Solution.solution({0}, {3}, {{0, 7, 0, 0}, {0, 0, 6, 0}, {0, 0, 0, 8}, {9, 0,
 * 0, 0}})
 * Output:
 * 6
 */

public class Solution {
    public static void solution(int[] entrances, int[] exits, int[][] path) {
        int[] corridorTraffic = new int[path.length];
        int counter = 0;
        for (int i = 0; i < path.length; i++) {
            boolean isExit = isExit(exits, i);
            if (isEntrance(entrances, i)) {
                System.out.println("# Room " + i + ": Bunnies");
            } else if (isExit) {
                System.out.println("# Room " + i + ": Escape pods");
            } else {
                System.out.println("# Room " + i + ": Intermediate room");
            }
            if (!isExit) {
                String logs = "";
                for (int j = 0; j < path[i].length; j++) {
                    if (path[i][j] > 0) {
                        int available = path[i][j];
                        int current = available;
                        if (j > 0 && corridorTraffic[i] > 0) {
                            current = corridorTraffic[i];
                        }
                        if (available > current) {
                            available = current;
                        }
                        int allowed = corridorTraffic[j];
                        if (allowed == 0) {
                            allowed = available;
                        }
                        int sent = 0;
                        if (allowed > 0) {
                            if (available > allowed) {
                                sent = allowed;
                            } else {
                                sent = available;
                            }
                        } else {
                            sent = available;
                        }
                        corridorTraffic[j] = corridorTraffic[j] + sent;
                        if (isExit(exits, j)) {
                            counter += sent;
                        }
                        if (logs.isEmpty()) {
                            logs = "sends " + sent + "/" + path[i][j] + " bunnies to " + j;
                        } else {
                            logs += " and " + sent + "/" + path[i][j] + " bunnies to " + j;
                        }
                    }

                }
                System.out.println("\t" + logs);
            }
        }
        System.out.println(Arrays.toString(corridorTraffic) + " Escaped: " + counter);
    }

    private static boolean isEntrance(int[] entrances, int corridor) {
        for (int i : entrances) {
            if (corridor == i)
                return true;
        }
        return false;
    }

    private static boolean isExit(int[] exits, int corridor) {
        for (int i : exits) {
            if (corridor == i)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution.solution(
                new int[] { 0, 1 },
                new int[] { 4, 5 },
                new int[][] { { 0, 0, 4, 6, 0, 0 }, { 0, 0, 5, 2, 0, 0 }, { 0, 0, 0, 0, 4, 4 }, { 0, 0, 0, 0, 6, 6 },
                        { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } });
        System.out.println("*********************************************");
        Solution.solution(new int[] { 0 }, new int[] { 3 },
                new int[][] { { 0, 7, 0, 0 }, { 0, 0, 6, 0 }, { 0, 0, 0, 8 }, { 9, 0,
                        0, 0 } });
    }
}
