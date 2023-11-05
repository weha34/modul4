//Wildan H (5311421008)
//modul 4
//nomor 4

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
public class AdjacencyList4 {
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

    public AdjacencyList4() {
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
        AdjacencyList4 graph = new AdjacencyList4();
     
        Node n1 = new Node(6);
        Node n2 = new Node(2);
        Node n3 = new Node(7);
        Node n4 = new Node(1);
        Node n5 = new Node(4);
        Node n6 = new Node(9);
        Node n7 = new Node(3);
        Node n8 = new Node(5);
        Node n9 = new Node(8);
       
    
        // Menambahkan edge-edge sesuai dengan gambar 4.4
        graph.addEdge(n1, n2); 
        graph.addEdge(n1, n3); 
        graph.addEdge(n2, n1); 
        graph.addEdge(n2, n4); 
        graph.addEdge(n2, n5); 
        graph.addEdge(n3, n1); 
        graph.addEdge(n3, n6); 
        graph.addEdge(n4, n2); 
        graph.addEdge(n5, n2); 
        graph.addEdge(n5, n7);
        graph.addEdge(n5, n8); 
        graph.addEdge(n6, n3);
        graph.addEdge(n6, n9); 
        graph.addEdge(n7, n5);
        graph.addEdge(n8, n5); 
        graph.addEdge(n9, n6);
       
        // Menjalankan algoritma BFS dari node n1
        graph.bfs(n1);
    
        // Menemukan node 3
        Node currentNode = n3;
        System.out.print("\nPath to Node 9: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.predecessor;
        }
    }
    
    
}

//[Running] cd "c:\Users\toshiba\Documents\semester 5\AI\" && javac AdjacencyList4.java && java AdjacencyList4
//(6,d=0) (2,d=1) (7,d=1) (1,d=2) (4,d=2) (9,d=2) (3,d=3) (5,d=3) (8,d=3) 
//Path to Node 9: 7 6 
//[Done] exited with code=0 in 2.083 seconds
