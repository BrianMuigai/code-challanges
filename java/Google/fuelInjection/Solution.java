package fuelInjection;
/**
 * Commander Lambda has asked for your help to refine the automatic quantum
 * antimatter fuel injection system for the LAMBCHOP doomsday device. It's a
 * great chance for you to get a closer look at the LAMBCHOP -- and maybe sneak
 * in a bit of sabotage while you're at it -- so you took the job gladly.
 * 
 * Quantum antimatter fuel comes in small pellets, which is convenient since the
 * many moving parts of the LAMBCHOP each need to be fed fuel one pellet at a
 * time. However, minions dump pellets in bulk into the fuel intake. You need to
 * figure out the most efficient way to sort and shift the pellets down to a
 * single pellet at a time.
 * 
 * The fuel control mechanisms have three operations:
 * 
 * 1) Add one fuel pellet
 * 2) Remove one fuel pellet
 * 3) Divide the entire group of fuel pellets by 2 (due to the destructive
 * energy released when a quantum antimatter pellet is cut in half, the safety
 * controls will only allow this to happen if there is an even number of
 * pellets)
 * 
 * Write a function called solution(n) which takes a positive integer as a
 * string and returns the minimum number of operations needed to transform the
 * number of pellets to 1. The fuel intake control panel can only display a
 * number up to 309 digits long, so there won't ever be more pellets than you
 * can express in that many digits.
 * 
 * For example:
 * solution(4) returns 2: 4 -> 2 -> 1
 * solution(15) returns 5: 15 -> 16 -> 8 -> 4 -> 2 -> 1
 * 
 */
public class Solution {

    public static int solution(String x) {
        // System.out.print(x + " --> ");

        if (x.length() == 0 || x.equals("1"))
            return 0;

        int counter = 0;
        String newX = "";
        if (!isDivisible(x)) {
            newX = addOne(x);
            counter++;
            counter += solution(newX);
        } else {
            newX = divide(x);
            counter++;
            counter += solution(newX);
        }

        return counter;
    }

    private static boolean isDivisible(String x) {
        int last = Integer.parseInt(x.substring(x.length() - 1));
        if (last < 2)
            last = Integer.parseInt(x.substring(x.length() - 2));
        return last % 2 == 0;
    }

    public static String addOne(String x) {
        StringBuilder sb = new StringBuilder(x);
        int remainder = 0;

        for (int i = x.length() - 1; i >= 0; i--) {
            int num = Integer.parseInt(String.valueOf(x.charAt(i)));
            if (i == x.length() - 1)
                num += 1;
            else
                num += remainder;
            remainder = 0;
            if (num < 10) {
                sb.setCharAt(i, (char) (num + '0'));
                break;
            } else {
                String tmp = String.valueOf(num);
                remainder = Integer.parseInt(String.valueOf(tmp.charAt(0)));
                sb.setCharAt(i, tmp.charAt(1));
            }
        }

        return sb.toString();
    }

    public static String divide(String x) {
        int remainder = 0;
        StringBuilder newX = new StringBuilder();

        for (int i = 0; i < x.length(); i += 8) {
            int endIndex = i + 8;
            if (endIndex >= x.length()) {
                endIndex = x.length();
            }
            StringBuilder sb = new StringBuilder(x.substring(i, endIndex));
            if (remainder != 0) {
                int firstVal = (remainder * 10) + Integer.parseInt(String.valueOf(sb.charAt(0)));
                if (firstVal >= 10) {
                    // todo
                } else {
                    sb.setCharAt(0, (char) (firstVal + '0'));
                }
            }
            System.out.println("newX: " + newX);
            if (Integer.parseInt(String.valueOf(sb.charAt(0))) < 2 && i != 0)
                newX.append("0");
            int num = Integer.parseInt(sb.toString().trim());
            remainder = num % 2;
            newX.append("" + num / 2);
        }
        return newX.toString();
    }

    public static void main(String[] args) {
        // System.out.println("\n"+Solution.solution("4"));
        // System.out.println("\n"+Solution.solution("15"));
        // System.out.println("\n" + Solution.solution("12345"));
        System.out.println("\n" + Solution.solution("123456789098"));
    }

}