//Wildan H (5311421008)
//modul 4
//nomor 1

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
public class AdjacencyList {
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

    public AdjacencyList() {
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
        AdjacencyList graph = new AdjacencyList();
 
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        // Menambahkan edge-edge sesuai dengan graf yang Anda berikan
        graph.addEdge(n1, n2);
        graph.addEdge(n2, n1);
        graph.addEdge(n2, n3);
        graph.addEdge(n3, n4);
        graph.addEdge(n3, n2);
        graph.addEdge(n4, n3);
        graph.addEdge(n4, n5);
        graph.addEdge(n4, n6);
        graph.addEdge(n5, n4);
        graph.addEdge(n5, n6);
        graph.addEdge(n5, n7);
        graph.addEdge(n6, n4);
        graph.addEdge(n6, n5);
        graph.addEdge(n6, n7);
        graph.addEdge(n6, n8);
        graph.addEdge(n7, n5);
        graph.addEdge(n7, n6);
        graph.addEdge(n7, n8);
        graph.addEdge(n8, n6);
        graph.addEdge(n8, n7);

        // Menjalankan algoritma BFS dari node n3
        graph.bfs(n3);

        // Menemukan node 8
        Node currentNode = n8;
        System.out.print("\nPath to Node 8: ");
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.predecessor;
        }
 
    }
}
//1. Tentukan bagaimana algoritma BFS di atas dapat menentukan node ke 8, 6, dan 7.
//2. Ubahlah method static void main sehingga bentuk tree seperti Gambar 4.4 dapat dibentuk. 
//Kemudian tentukan bagaimana algoritma BFS dapat menemukan node 5. 
//3. Ubahlah method static void main sehingga bentuk tree seperti Gambar 4.5 dapat dibentuk. 
//Kemudian tentukan bagaimana algoritma BFS dapat menemukan node 9.
//4. Ubahlah kode program di atas sehingga bentuk tree seperti Gambar 6 dapat dibentuk. Kemudian 
//   tentukan bagaimana algoritma BFS dapat menemukan node C

// Jawaban Nomor 1
// Algoritma Breadth-First Search (BFS) dalam kode yang diberikan bekerja
//dengan mengunjungi simpul-simpul dalam grafik secara meluas dan menggunakan
//antrian untuk mengingat simpul berikutnya yang akan dijadikan awal pencarian
//ketika suatu titik mati terjadi selama iterasi. Berikut adalah cara algoritma ini menemukan simpul-simpul 8, 6, dan 7:

//1. BFS dimulai dari simpul 3 sesuai dengan baris `graph.bfs(n3);` dalam metode utama.
//2. Pertama-tama, algoritma mengunjungi semua tetangga dari simpul 3, yaitu simpul 2 dan 4.
//3. Kemudian, untuk masing-masing dari simpul ini, algoritma mengunjungi tetangga yang belum pernah dikunjungi.
//Untuk simpul 2, simpul 3 sudah dikunjungi dan simpul 1 tidak memiliki tetangga lain, jadi algoritma beralih ke simpul 4.
//4. Simpul 4 memiliki tetangga 3, 5, dan 6. Simpul 3 sudah dikunjungi, jadi algoritma mengunjungi simpul 5 dan 6. Inilah tempat simpul 6 ditemukan.
//5. Untuk simpul 5, algoritma mengunjungi tetangga yang belum pernah dikunjungi, yaitu simpul 7 dan 6. Simpul 6 sudah dikunjungi,
//jadi algoritma mengunjungi simpul 7. Inilah tempat simpul 7 ditemukan.
//6. Untuk simpul 6, algoritma mengunjungi tetangga yang belum pernah dikunjungi, yaitu simpul 4, 5, 7, dan 8.
//Simpul 4, 5, dan 7 sudah dikunjungi, jadi algoritma mengunjungi simpul 8. Inilah tempat simpul 8 ditemukan.

//Sehingga urutan kunjungan adalah: Simpul **3** -> Simpul **2** -> Simpul **4** -> Simpul **5** (di mana Simpul **6** ditemukan) -> Simpul **7** (di mana Simpul **7** ditemukan) -> Simpul **8** (di mana Simpul **8** ditemukan). Algoritma ini berlanjut hingga semua simpul telah dikunjungi.

//Harap dicatat bahwa urutan sebenarnya dapat bervariasi tergantung pada implementasi algoritma BFS dan struktur data yang digunakan untuk antrian. Namun, penjelasan ini seharusnya memberi Anda gambaran umum tentang bagaimana BFS dapat menemukan simpul-simpul ini.

//[Running] cd "c:\Users\toshiba\Documents\semester 5\AI\" && javac AdjacencyList.java && java AdjacencyList
//(3,d=0) (4,d=1) (2,d=1) (5,d=2) (6,d=2) (1,d=2) (7,d=3) (8,d=3) 
//Path to Node 8: 8 6 4 3 
//[Done] exited with code=0 in 1.817 seconds
