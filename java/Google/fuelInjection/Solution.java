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
        if (x.length() == 0)
            return 0;

        int counter = 0;
        String newX = "";

        for (int i = 0; i < x.length(); i++) {
            System.out.print(x + " --> ");
            int num = Integer.parseInt(String.valueOf(x.charAt(i)));
            if (num < 2) {
                num = Integer.parseInt(x.substring(i, i + 2));
                i++;
            }
            if (num % 2 == 0) {
                newX += String.valueOf(num / 2);
            } else {
                newX = addOne(x);
                counter++;
                break;
            }
        }
        counter++;
        if (newX.length() != 1 || Integer.parseInt(newX) != 1) {
            counter += solution(newX);
        }
        return counter;
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


    public static void main(String[] args) {

        System.out.println("\n"+Solution.solution("4"));
        System.out.println("\n"+Solution.solution("15"));
    }

}