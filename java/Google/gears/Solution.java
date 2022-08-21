package gears;

import java.util.Arrays;

public class Solution {
    public static int[] solution(int[] pegs) {
        int n = pegs.length;
        int[] inv = new int[]{-1,-1};
        if (n < 2) return inv;
        /*
         * when n = odd:
         * r0=2*[(p1-p0)-(p2-p1)+(p3-p2)-(p4-p3)+...]
         * n = even:
         * r0=(2/3)*[(p1-p0)-(p2-p1)+(p3-p2)-...]
         * where the plus and minus signs alternate between the distances
         */
        int distances = 0;
        for (int i = 0; i < n - 1; i++) {
            int p = pegs[i + 1] - pegs[i];
            if (i == 0 || i % 2 == 0) {
                distances += p;
            } else {
                distances -= p;
            }
        }
        int a = distances *2;
        int b = n%2==0 ? 3 : 1;

        // reduce
        if(a%b==0) {
            a /= b;
            b = 1;
        }
       
        double prevR = ((float)a) / ((float)b);
        for(int i = 0; i < pegs.length - 2; i++) {
            int p = pegs[i+1] - pegs[i];
            if(prevR < 0 || prevR > (p-1)) return inv;
            prevR = p - prevR;
        }
        return new int[] {a,b};
    }
    

    public static void main(String[] args) {
        int[] res = Solution.solution(new  int[]{4,30,50});
        System.out.println(Arrays.toString(res));
        res = Solution.solution(new  int[]{1, 50});
        System.out.println(Arrays.toString(res));
        res = Solution.solution(new  int[]{4,17,50});
        System.out.println(Arrays.toString(res));
    }
}