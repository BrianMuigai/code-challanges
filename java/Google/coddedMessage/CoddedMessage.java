package coddedMessage;

import java.util.Arrays;

public class CoddedMessage {

    public static int solution(int[] l) {
        if (l == null || l.length == 0)
            return 0;
        int n = l.length;
        Arrays.sort(l);
        reverse(l);
        int coded = listToInt(l);
        if (coded % 3 == 0)
            return coded;
        else if (coded % 3 == 1) {
            for (int i = n - 1; i >= 0; i--) {
                if (l[i] % 3 == 1)
                    return listToInt(pluck(l, i));
            }
            int[] unwanted = findUnwanted(l, 2);
            return unwanted.length == 0 ? 0 : listToInt(pluck(l, unwanted[0], unwanted[1]));
        } else {
            for (int i = n - 1; i >= 0; i--) {
                if (l[i] % 3 == 2)
                    return listToInt(pluck(l, i));
            }
            int[] unwanted = findUnwanted(l, 1);
            return unwanted.length == 0 ? 0 : listToInt(pluck(l, unwanted[0], unwanted[1]));
        }
    }

    private static void reverse(int[] l) {
        int r = l.length - 1;
        for (int i = 0; i < r; i++) {
            swap(l, i, r);
            r--;
        }
    }

    private static int listToInt(int[] l) {
        int total = 0;
        for (Integer i : l) {
            total = 10 * total + i;
        }
        return total;
    }

    private static void swap(int[] l, int i, int j) {
        int tmp = l[i];
        l[i] = l[j];
        l[j] = tmp;
    }

    private static int[] pluck(int[] l, int index) {
        int n = l.length;
        int[] newL = new int[n - 1];
        int tracer = 0;
        for (int i = 0; i < n; i++) {
            if (i != index) {
                newL[tracer] = l[i];
                tracer++;
            }
        }
        return newL;
    }

    private static int[] pluck(int[] l, int a, int b) {
        int n = l.length;
        int[] newL = new int[n - 2];
        int tracer = 0;
        for (int i = 0; i < n; i++) {
            if (i != a && i != b) {
                newL[tracer] = l[i];
                tracer++;
            }
        }
        return newL;
    }

    private static int[] findUnwanted(int[] l, int remainder) {
        int i = l.length - 1;
        while (i >= 0) {
            if (l[i] % 3 == remainder)
                break;
            i -= 1;
        }
            
        int j = i - 1;
        while (j >= 0) {
            if (l[j] % 3 == remainder)
                break;
            j -= 1;
        }
           
        return new int[] { i, j };
    }

    public static void main(String[] args) {
        // int code = CoddedMessage.solution(new int[] { 3, 1, 4, 1 });
        // System.out.println("Code: " + code);
        // code = CoddedMessage.solution(new int[] { 3, 1, 4, 1, 5, 9 });
        // System.out.println("Code: " + code);
        
        // code = CoddedMessage.solution(new int[] {});
        // System.out.println("Code: " + code);

        int code = CoddedMessage.solution(new int[] { 5, 5 });
        System.out.println("Code: " + code);

        // code = CoddedMessage.solution(new int[] {9});
        // System.out.println("Code: " + code);

        // code = CoddedMessage.solution(new int[] { 1,5 });
        // System.out.println("Code: " + code);


        // code = CoddedMessage.solution(new int[] { 4,5 });
        // System.out.println("Code: " + code);
    } 
    
}
