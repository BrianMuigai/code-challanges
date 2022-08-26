package graph;

import java.util.List;
import java.util.Map;

public class Graph {

    class Node {
        String value;
        Node[] neighbours;

        public Node(Node[] neighbours) {
            this.neighbours = neighbours;
        }

        public Node(String value, Node[] neighbours) {
            this.value = value;
            this.neighbours = neighbours;
        }
    }
    
    public static Node[] breathFirstSearch(Node[] nodes, Node start) {
        //queue

    }

    public static Node[] depthFirstSearch(Node[] nodes, Node start) {
        //stacks
    }
}
