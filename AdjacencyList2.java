//Wildan H (5311421008)
//modul 4
//nomor 2

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Graph represented by an adjacency list.
 *
 * Reference: Introduction to Algorithms - CLRS.
 *
 * @author
 * @since: 26/02/2011
 */
public class AdjacencyList2 {
    public enum NodeColour { WHITE, GRAY, BLACK }

    public static class Node {
        int data;
        int distance;
        Node predecessor;
        NodeColour colour;

        public Node(int data) {
            this.data = data;
        }

        public String toString() {
            return "(" + data + ",d=" + distance + ")";
        }
    }

    Map<Node, List<Node>> nodes;

    public AdjacencyList2() {
        nodes = new HashMap<Node, List<Node>>();
    }

    public void addEdge(Node n1, Node n2) {
        if (nodes.containsKey(n1)) {
            nodes.get(n1).add(n2);
        } else {
            ArrayList<Node> list = new ArrayList<Node>();
            list.add(n2);
            nodes.put(n1, list);
        }
    }

    public void bfs(Node s) {
        Set<Node> keys = nodes.keySet();
        for (Node u : keys) {
            if (u != s) {
                u.colour = NodeColour.WHITE;
                u.distance = Integer.MAX_VALUE;
                u.predecessor = null;
            }
        }
        s.colour = NodeColour.GRAY;
        s.distance = 0;
        s.predecessor = null;
        Queue<Node> q = new ArrayDeque<Node>();
        q.add(s);
        while (!q.isEmpty()) {
            Node u = q.remove();
            List<Node> adj_u = nodes.get(u);
            if (adj_u != null) {
                for (Node v : adj_u) {
                    if(v.colour == NodeColour.WHITE) {
                        v.colour = NodeColour.GRAY;
                        v.distance = u.distance + 1;
                        v.predecessor = u;
                        q.add(v);
                    }
                }
            }
            u.colour = NodeColour.BLACK;
            System.out.print(u + " ");
        }
    }

    public static void main(String[] args) {
        AdjacencyList2 graph = new AdjacencyList2();
     
        Node n1 = new Node(0);
        Node n2 = new Node(1);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        Node n5 = new Node(4);
        Node n6 = new Node(5);
        Node n7 = new Node(6);
       
    
        // Menambahkan edge-edge sesuai dengan gambar 4.4
        graph.addEdge(n1, n2); 
        graph.addEdge(n1, n3); 
        graph.addEdge(n2, n1); 
        graph.addEdge(n2, n4); 
        graph.addEdge(n2, n5); 
        graph.addEdge(n3, n6); 
        graph.addEdge(n3, n6); 
        graph.addEdge(n3, n7); 
        graph.addEdge(n4, n2); 
        graph.addEdge(n4, n5);
        graph.addEdge(n5, n4); 
        graph.addEdge(n5, n2);
        graph.addEdge(n6, n3); 
        graph.addEdge(n6, n7);
        graph.addEdge(n6, n2); 
        graph.addEdge(n7, n3);
        graph.addEdge(n7, n6); 
        graph.addEdge(n7, n2);
    
        // Menjalankan algoritma BFS dari node n1
        graph.bfs(n1);
    
        // Menemukan node 5
        Node currentNode = n5;
        System.out.print("\nPath to Node 5: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.predecessor;
        }
    }
    
    
}

// [Running] cd "c:\Users\toshiba\Documents\semester 5\AI\" && javac AdjacencyList2.java && java AdjacencyList2
// (0,d=0) (1,d=1) (2,d=1) (3,d=2) (4,d=2) (5,d=2) (6,d=2) 
// Path to Node 5: 4 1 0 
// [Done] exited with code=0 in 1.75 seconds