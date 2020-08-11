class Solution {
    
    class Node {
        LinkedList<Node> incoming = new LinkedList<>();
        LinkedList<Node> outgoing = new LinkedList<>();
        int index;
        
        Node(int index) {
            this.index = index;
        }
    }

    private int counter = 0;
    
    public int minReorder(int n, int[][] connections) {
        Node root = this.getHead(n, connections);
        this.calculateDistance(root, new boolean[n], true);
        return this.counter;
    }
    
    private Node getHead(int n, int[][] connections) {
        HashMap<Integer, Node> nodes = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            nodes.put(i, new Node(i));
        }
        
        for (int[] connection: connections) {
            Node start = nodes.get(connection[0]);
            Node end = nodes.get(connection[1]);
            
            start.outgoing.add(end);
            end.incoming.add(start);
        }
        return nodes.get(0);
    }
    
    // It can definitely be handled better using BFS, but here DFS is used...
    private void calculateDistance(Node n, boolean[] visited, boolean incoming) {
        if (visited[n.index]) {
            return;
        }
        visited[n.index] = true;
        
        if (incoming && n.index > 0) {          // Don't consider the root node
            this.counter++;
        }
        
        for (Node out: n.outgoing) {
            calculateDistance(out, visited, true);
        }
        for (Node in: n.incoming) {
            calculateDistance(in, visited, false);
        }
    }
}