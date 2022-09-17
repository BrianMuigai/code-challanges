package fuelInjection;

import java.math.BigInteger;

public class PyJav {

    public static int solution(String x) {
        BigInteger zero = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger three = new BigInteger("3");

        int i = 0;
        BigInteger n = new BigInteger(x);
        while (n.compareTo(one) > 0) {
            if (n.and(one).compareTo(zero) == 0) {
                n = n.shiftRight(1);
            } else if (n.and(three).compareTo(one) == 0 || n.compareTo(three) == 0) {
                n = n.subtract(one);
            } else {
                n.add(one);
            }
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println("\n" + SolutionTree.solution("4"));
        System.out.println("\n" + SolutionTree.solution("15"));
        System.out.println("\n" + SolutionTree.solution("12345"));
        System.out.println("\n" + SolutionTree.solution("123456789098"));
        System.out.println("\n" + SolutionTree.solution("12345678909898765678987659876543456789876543"));
    }
    
}
