package NathansTest;
//you can also use imports,for example:

import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    private int numGroups;

    public int solution(String[] T, String[] R) {
        numGroups = 0;
        T = this.removeTaskNames(T);
        TestCase[] testCases = this.generateTestCases(T, R);
        // int numberOfGroups = Arrays.stream(testCases).sorted(Comparator.comparing(d -> d.group))
        //         .collect(Collectors.toList()).get(testCases.length - 1).group;
        int totalPassed = 0;
        for (int i = 1; i < numGroups; i++) {
            int finalI = i;
            if (Arrays.stream(testCases).noneMatch(t -> t.group == finalI && !t.result.equals("OK"))) {
                totalPassed++;
            }
        }
        return totalPassed * 100 / numGroups;
    }

    private String[] removeTaskNames(String[] T) {
        String taskName = "";
        //get task name
        for (int i = 0; i < T[0].length(); i++) {
            if (T[0].charAt(i) == T[1].charAt(i) && !Character.isDigit(T[0].charAt(i))) {
                taskName += T[0].charAt(i);
            } else {
                break;
            }
        }
        // substring task name out (remove the task name)
        for (int i = 0; i < T.length; i++) {
            T[i] = T[i].substring(taskName.length());
        }
        return T;
    }

    private TestCase[] generateTestCases(String[] T, String[] R) {
        TestCase[] testCases = new TestCase[T.length];
        Set<Integer> groups = new HashSet<>();
        for (int i = 0; i < T.length; i++) {
            testCases[i] = new TestCase();
            if (T[i].length() > 1 && !Character.isDigit(T[i].charAt(T[i].length() - 1))) {
                testCases[i].extension = T[i].charAt(T[i].length() - 1);
                testCases[i].group = Integer.parseInt(T[i].substring(0, T[i].length() - 1));
            } else {
                testCases[i].group = Integer.parseInt(T[i]);
            }
            testCases[i].result = R[i];
            groups.add(testCases[i].group);
        }
        numGroups = groups.size();
        return testCases;
    }

    class TestCase {
        public int group;
        char extension;
        String result;
    }

    public static void main(String[] args) {
        Solution nts = new Solution();
        String[] T = {
                "codility1",
                "codility3",
                "codility2",
                "codility4b",
                "codility4a"
        };
        String[] R = {
                "Wrong answer",
                "OK",
                "OK",
                "Runtime error",
                "OK"
        };

        System.out.println("Result: " + nts.solution(T, R));
    }
}
