package distraction;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static Set<String> infiniteSet = new HashSet<>();

    public static int solution(int[] list) {
        int watchers = 0;
        Set<String> engagedWatchers = new HashSet<>();
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                String pair = i + "," + j;
                if (i != j && !engagedWatchers.contains(pair)) {
                    int num1 = list[i];
                    int num2 = list[j];

                    if (isInfinite(new int[] { num1, num2 }, new HashSet<>())) {
                        engagedWatchers.add(pair);
                        break;
                    }
                }

                if (j == list.length - 1) {
                    watchers++;
                }

            }
        }
        return watchers;
    }

    private static boolean isInfinite(int[] pair, Set<String> pairs) {
        int num1 = pair[0], num2 = pair[1];
        if (num1 == num2)
            return false;

        String pairString = num1 + "," + num2;
        if (pairs.contains(pairString)) {
            infiniteSet.add(pairString);
            return true;
        } else if (infiniteSet.contains(pairString)) {
            return true;
        }

        pairs.add(pairString);

        int stake = num1 < num2 ? num1 * 2 : num2 * 2;
        if (num1 < num2) {
            num2 -= num1;
            num1 = stake;
        } else {
            num1 -= num2;
            num2 = stake;
        }
        return isInfinite(new int[] { num1, num2 }, pairs);
    }

    public static void main(String[] args) {
        System.out.println("\n" + Solution.solution(new int[] { 1, 7, 3, 21, 13, 19 }));
        System.out.println("\n" + Solution.solution(new int[] { 1, 1, 1, 2 })); // return 2
    }
}
