package fuelInjection;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SolutionTree {

    private static class Node {
        public String value;
        public final List<Node> children;

        public Node(String value) {
            this.value = value;
            children = new ArrayList<>();
        }

        public void addChild(Node node) {
            children.add(node);
        }

        public boolean isMultipleOfTwo() {
            String x = new String(value);
            int last = Integer.parseInt(x.substring(x.length() - 1));
            if (last < 2 && x.length() > 1)
                last = Integer.parseInt(x.substring(x.length() - 2));
            return last % 2 == 0;
        }
    }

    public static int solution(String x) {
        Node root = new Node(x);
        int counter = 0;
        List<Node> level = new ArrayList<>();
        level.add(root);

        Set<String> seen = new HashSet<>();

        while (level.size() > 0) {
            List<Node> nextNodes = new ArrayList<>();
            for (Node node : level) {
                // System.out.print(node.value + " --> ");
                if (seen.contains(node.value)) {
                    continue;
                }
                seen.add(node.value);
                if (node.value.equals("1")) {
                    return counter;
                }
                if (node.isMultipleOfTwo()) {
                    node.addChild(new Node(divide(node.value)));
                } else {
                    Node leftChildNode = new Node(subtractOne(node.value));
                    Node rightChildNode = new Node(addOne(node.value));
                    node.addChild(leftChildNode);
                    node.addChild(rightChildNode);
                }
                for (Node child : node.children) {
                    nextNodes.add(child);
                }
            }
            level = nextNodes;
            counter++;
        }

        return counter;
    }

    private static String subtractOne(String x) {
        BigInteger one = new BigInteger("1");
        BigInteger n = new BigInteger(x);
        return n.subtract(one).toString(10);
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
        if (remainder != 0) {
            sb.setCharAt(0, (char) (remainder + '0'));
        }

        return sb.toString();
    }

    public static String divide(String x) {
        int remainder = 0;
        StringBuilder newX = new StringBuilder();

        int i = 0;
        while (i < x.length()) {
            int endIndex = i + 9;
            if (endIndex >= x.length()) {
                endIndex = x.length();
            }

            StringBuilder sb = new StringBuilder(x.substring(i, endIndex));
            if (remainder != 0) {
                int firstVal = (remainder * 10) + Integer.parseInt(String.valueOf(sb.charAt(0)));
                if (firstVal >= 10) {
                    remainder = firstVal % 2;
                    newX.append(firstVal / 2);
                    i++;
                    continue;
                } else {
                    sb.setCharAt(0, (char) (firstVal + '0'));
                }
            }
            if (Integer.parseInt(String.valueOf(sb.charAt(0))) < 2 && i != 0 && i != x.length() - 1)
                newX.append("0");
            int num = Integer.parseInt(sb.toString().trim());
            remainder = num % 2;
            newX.append("" + num / 2);

            i += 9;
        }

        return newX.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("\n" + SolutionTree.solution("4"));
        System.out.println("\n" + SolutionTree.solution("15"));
        System.out.println("\n" + SolutionTree.solution("12345"));
        System.out.println("\n" + SolutionTree.solution("123456789098"));
        System.out.println("\n" + SolutionTree.solution("12345678909898765678987659876543456789876543"));
    }


}